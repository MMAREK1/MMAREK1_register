package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRegisterLoader implements RegisterLoader {
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public FileRegisterLoader() {
	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#store(register.Register)
	 */
	@Override
	public void store(Register register) throws FileNotFoundException, IOException {
		String fileName = "register.bin";
		try (FileOutputStream out = new FileOutputStream(fileName);
				ObjectOutputStream so = new ObjectOutputStream(out);) {
			so.writeObject(register);
			so.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#load()
	 */
	@Override
	public Register load() throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "fileregister.bin";
		File f = new File(fileName);
		if (f.exists()) {
			try (FileInputStream in = new FileInputStream(fileName);
					ObjectInputStream si = new ObjectInputStream(in);) {
				Register register = (Register) si.readObject();
				si.close();
				return register;
			}
		}
		System.out.println("Register don't exist choose type of register:\n1. ArrayRegister\n2. ListRegister");
		int index = Integer.parseInt(readLine());
		switch (index) {
		case 1:
			return new ArrayRegister(20);
		case 2:
			return new ListRegister();
		default:
			return null;
		}

	}

}