package baseball.controller;

import java.util.List;

import baseball.domain.Computer;
import baseball.domain.Refree;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Controller {

	public static final String THREE_STRIKE = "3스트라이크";
	public static final String ON_GOING = "1";
	public static final String STOP = "2";
	public static final String REQUEST_RESTART_MESSAGE = "1 혹은 2를 입력해주세요";
	private String state;
	List<Integer> computerNumbers;
	List<Integer> playerNumbers;
	private Computer computer;
	private Refree refree;
	String result;

	public Controller() {
		computer = new Computer();
		state = ON_GOING;
		refree = new Refree();
		computerNumbers = computer.createRandomNumbers();
		// computerNumbers = new ArrayList<>(Arrays.asList(1, 2, 3));
	}

	public void runGame() {

		while (state.equals(ON_GOING)) {
			OutputView.printInstruction();
			playerNumbers = refree.askNumbers(InputView.getInput());
			result = refree.compare(computerNumbers, playerNumbers);
			OutputView.printResult(result);
			checkThreeStrike(result);
		}
	}

	private void checkThreeStrike(String result) {
		if (result.equals(THREE_STRIKE)) {
			askRestart();
		}
	}

	private void askRestart() {
		OutputView.printEndInstruction();
		state = InputView.getInput();
		checkRestartInputRange(state);
		createNewRandomNumber(state);
	}

	private void createNewRandomNumber(String state) {
		if (this.state.equals(ON_GOING)) {
			computerNumbers = computer.createRandomNumbers();
		}
	}

	private void checkRestartInputRange(String state) {
		if (!(this.state.equals(ON_GOING) || this.state.equals(STOP))) {
			throw new IllegalArgumentException(REQUEST_RESTART_MESSAGE);
		}
	}

}
