package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

	public static void main(String[] args) throws Exception {

		RegisterLoader registerLoader = new DatabaseRegisterLoader();
		ConsoleUI ui = new ConsoleUI(registerLoader.load());
		ui.run();
	}
}
