package racingCar1Youngyoon.controller;

import java.util.List;

import racingCar1Youngyoon.domain.RacingGame;
import racingCar1Youngyoon.view.InputView;
import racingCar1Youngyoon.view.OutputView;

public class Controller {
	public void runGame() {
		//TODO: 메인로직
		//1. 싱글톤 게임관리자 작성하기
		RacingGame racingGame = RacingGame.getInstance();
		racingGame.init();

		OutputView.printInputNameInstruction();
		//2. 새로운시도, input메서드에서 검증하기 (원래는 포장클래스-> 객체생성of,from 스새틱메서드에서)
		// InputView.getInput();
		List<String> names = InputView.getNames();
		// System.out.println(names);



	}
}
