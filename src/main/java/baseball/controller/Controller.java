package baseball.controller;

import java.util.List;

import baseball.domain.Computer;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Controller {

	public static final String FINAL_ANSWER = "3스트라이크";

	public void run() {
		String result = "";
		while (!result.equals(FINAL_ANSWER)) {

			Computer computer = new Computer();
			List<Integer> computerNumbers = computer.createRandomNumbers();

			OutputView.printInstruction();
			String input = InputView.getInput();
			System.out.println(input);
		}
	}
}
