package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class TextFileRegisterLoader implements RegisterLoader {
	String fileName = "textRegister.bin";

	@Override
	public void store(Register register) throws FileNotFoundException, Exception {
		try (FileWriter out = new FileWriter(fileName)) {
			for (int i = 0; i < register.getCount(); i++) {
				out.write(register.getPerson(i).getName());
				out.write("\n");
				out.write(register.getPerson(i).getPhoneNumber());
				out.write("\n");
			}
		}
	}

	@Override
	public Register load() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(fileName);
		Register register = new ArrayRegister(20);
		if (f.exists()) {
			try (BufferedReader r = new BufferedReader(new FileReader(fileName));) {
				String name = null;
				String phoneNumber = null;
				String lineRead;
				while ((lineRead = r.readLine()) != null) {
					if (Pattern.matches("[a-zA-Z ]+", lineRead)) {
						name = lineRead;
						if((lineRead = r.readLine())!=null)
						{	
						if (Pattern.matches("[0-9]{10}", lineRead)) {
							phoneNumber = lineRead;
							register.addPerson(new Person(name, phoneNumber));
						} else {
							System.out.println("Person has wrong format - lost number");
							name=lineRead;
							if((lineRead = r.readLine())!=null)
							{
							if (Pattern.matches("[0-9]{10}", lineRead)) {
								phoneNumber = lineRead;
								register.addPerson(new Person(name, phoneNumber));
							} else {
								System.out.println("Person has wrong format - lost number");
							}
							}
							else
								break;
						}
						}
						else
							break;
					} else {
						System.out.println("Person has wrong format - lost name");
					}

				}
				r.close();
			}
		}
		return register;
	}

}
