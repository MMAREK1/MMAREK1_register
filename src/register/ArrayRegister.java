package register;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */

	public int getSize() {
		return persons.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		if (getCount() == 0) {
			persons[count] = person;
		} else {
			for (int i = 0; i < getCount(); i++) {
				if (person.getName().compareTo(getPerson(i).getName()) < 0) {
					for (int j = getCount(); j > i+1; j--) {
						if (j == getCount()) {
							persons[count] = getPerson(j - 1);
						} else {
							getPerson(j).setName(getPerson(j - 1).getName());
							getPerson(j).setPhoneNumber(getPerson(j - 1).getPhoneNumber());
						}
					}
					persons[i] = person;
					break;
				}
				if (i == getCount() - 1) {
					persons[count] = person;
				}
			}
		}
		count++;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < getCount(); i++) {
			if (name.equals(getPerson(i).getName())) {
				return getPerson(i);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < getCount(); i++) {
			if (phoneNumber.equals(getPerson(i).getPhoneNumber())) {
				return getPerson(i);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < getCount(); i++) {
			if (person.equals(getPerson(i))) {
				if (i != getCount() - 1) {
					for (int j = i + 1; j < getCount(); j++) {
						getPerson(j - 1).setName(getPerson(j).getName());
						getPerson(j - 1).setPhoneNumber(getPerson(j).getPhoneNumber());
					}
				}
				count--;
			}
		}
	}
}
