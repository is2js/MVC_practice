package vendingMachineByMe;

import vendingMachineByMe.controller.Controller;

public class Application {
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.runMachine();

	}
}
