package register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register, Comparable {
	private List<Person> listRegisters = new ArrayList<Person>();
	private int count;

	public Iterator<Person> iterator() {
		return listRegisters.iterator();
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Person getPerson(int index) {
		return listRegisters.get(index);
	}

	@Override
	public void addPerson(Person person) {
		listRegisters.add(person);
		// Collections.sort(listRegisters);
	}

	@Override
	public Person findPersonByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePerson(Person person) {
		// TODO Auto-generated method stub

	}



	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
