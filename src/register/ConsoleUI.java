package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * User interface of the application.
 */
@SuppressWarnings("serial")
public class ConsoleUI extends FileRegisterLoader implements Serializable {
	/** register.Register of persons. */
	Register register;
	RegisterLoader registerLoader = new DatabaseRegisterLoader();
	

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI(Register register) {
		this.register = register;

	}

	public void run() throws IOException, ClassNotFoundException {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				try {
					registerLoader.store(register);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * show user menu and wait for selection
	 * 
	 * @return Option.values()[selection - 1]
	 */
	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	private void printRegister() {
		for (int i = 0; i < register.getCount(); i++) {

			System.out.println(i + 1 + ". " + register.getPerson(i));

		}
	}

	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();
		if (register.findPersonByPhoneNumber(phoneNumber) != null) {
			System.out.println("You can't add person with same Name or Phone Number");
			return;
		}
		register.addPerson(new Person(name, phoneNumber));

	}

	/**
	 * change information by user's choice
	 */
	private void updateRegister() {
		System.out.println("Update by:\n1. Index\n2. Name\n3. Phone Number");
		int choice = Integer.parseInt(readLine());
		switch (choice) {
		case 1:
			System.out.println("Enter Index:");
			int index = Integer.parseInt(readLine());
			System.out.println("what do you want to change?\n1. Name\n2. Phone Number");
			int change = Integer.parseInt(readLine());
			if (change == 1) {
				System.out.println("Enter New Name:");
				register.getPerson(index - 1).setName(readLine());
			} else {
				System.out.println("Enter New Phone Number:");
				String phoneNumber = readLine();
				if (register.findPersonByPhoneNumber(phoneNumber) == null) {
					register.getPerson(index - 1).setPhoneNumber(phoneNumber);
				} else {
					System.out.println("You can't change number, because it's same as number of another user");
				}
			}
			break;
		case 2:
			System.out.println("Enter Name:");
			String name = readLine();
			for (int i = 0; i < register.getCount(); i++) {
				Person person = register.getPerson(i);
				if (name.equals(person.getName())) {
					System.out.println("Enter New Phone Number:");
					String phoneNumber = readLine();
					if (register.findPersonByPhoneNumber(phoneNumber) == null) {
						person.setPhoneNumber(phoneNumber);
					} else {
						System.out.println("You can't change number, because it's same as number of another user");
					}
					break;
				}
			}
			break;
		case 3:
			System.out.println("Enter Phone Number:");
			String phoneNumber = readLine();
			for (int i = 0; i < register.getCount(); i++) {
				Person person = register.getPerson(i);
				if (phoneNumber.equals(person.getPhoneNumber())) {
					System.out.println("Enter New Name:");
					person.setName(readLine());
					break;
				}
			}
			break;
		default:
			System.out.println("You insert incorrect choice");
			break;
		}
	}

	/**
	 * call specific method chosen by user for looking information
	 */
	private void findInRegister() {
		System.out.println("Find by:\n1. Name\n2. Phone Number");
		int find = Integer.parseInt(readLine());
		if (find == 1) {
			System.out.println("Enter Name:");
			System.out.println(register.findPersonByName(readLine()));
		} else {
			System.out.println("Enter Phone Number");
			System.out.println(register.findPersonByPhoneNumber(readLine()));
		}
	}

	/**
	 * call method for remove person from register by chosen index
	 */
	private void removeFromRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		register.removePerson(person);
	}

}
