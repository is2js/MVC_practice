package baseball.controller;

import baseball.view.InputView;
import baseball.view.OutputView;

public class Controller {
	public void run() {
		OutputView.printInstruction();
		String input = InputView.getInput();
		System.out.println(input);
	}
}
