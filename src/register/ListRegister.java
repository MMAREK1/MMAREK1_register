package register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class ListRegister implements Register {
	private List<Person> listRegisters = new ArrayList<Person>();

	@Override
	public int getCount() {
		return listRegisters.size();
	}

	@Override
	public Person getPerson(int index) {
		return listRegisters.get(index);
	}

	@Override
	public void addPerson(Person person) {
		listRegisters.add(person);
		Collections.sort(listRegisters);
	}

	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < listRegisters.size(); i++) {
			if (name.equals(listRegisters.get(i).getName())) {
				return getPerson(i);
			}
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < listRegisters.size(); i++) {
			if (phoneNumber.equals(listRegisters.get(i).getPhoneNumber())) {
				return getPerson(i);
			}
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {
		listRegisters.remove(person);

	}

	@Override
	public int getSize() {
		return listRegisters.size();
	}

}
