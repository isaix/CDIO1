package test;
import dal.PersistentStorage;
import ful.UserFunction;
import tui.TUI;

public class DBTester {
	PersistentStorage storage = new PersistentStorage("testFile");
	UserFunction function = new UserFunction(storage);
	TUI ui = new TUI(function);
	public static void main(String[] args) {
		DBTester test = new DBTester();
		test.run();
	}

	public void run() {
		while(true) {
			ui.showMenu();
		}

	}
}
