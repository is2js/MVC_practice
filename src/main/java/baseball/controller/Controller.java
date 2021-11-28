package baseball.controller;

import java.util.ArrayList;
import java.util.List;

import baseball.domain.Computer;
import baseball.domain.Refree;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Controller {

	public static final String THREE_STRIKE = "3스트라이크";
	private boolean restart_flag;
	List<Integer> computerNumbers = new ArrayList<>();
	private Computer computer = new Computer();

	public void run() {
		String result = "";
		restart_flag = true;
		computerNumbers = computer.createRandomNumbers();
		// computerNumbers = new ArrayList<>(Arrays.asList(1,2,3));

		while (restart_flag) {
			OutputView.printInstruction();
			Refree refree = new Refree();
			List<Integer> playerNumbers = refree.askNumbers(InputView.getInput());
			// System.out.println(playerNumbers + "를 입력하셨습니다.");

			result = refree.compare(computerNumbers, playerNumbers);
			System.out.println(result);

			if (result.equals(THREE_STRIKE)) {
				askRestart();
			}

		}
	}

	private void askRestart() {
		OutputView.printEndInstruction();
		String command = InputView.getInput();
		if (command.equals("1")) {
			computerNumbers = computer.createRandomNumbers();
			// restart_flag = true;
			return;
		}
		if (command.equals("2")) {
			restart_flag = false;
			return;
		}
		throw new IllegalArgumentException("1 혹은 2만 선택해주세요.");// 추가.

	}

}
