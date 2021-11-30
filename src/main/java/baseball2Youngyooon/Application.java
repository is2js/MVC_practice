package baseball2Youngyooon;

import baseball2Youngyooon.controller.Computer;
import baseball2Youngyooon.controller.Controller;

public class Application {
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.runGame();
		Computer.getInstance().init();


	}
}
