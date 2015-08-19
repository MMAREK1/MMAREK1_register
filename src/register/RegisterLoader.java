package register;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RegisterLoader {

	void store(Register register) throws FileNotFoundException, Exception;

	Register load() throws FileNotFoundException, IOException, ClassNotFoundException;

}