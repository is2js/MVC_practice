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

		//2. 새로운시도, input메서드에서 검증하기 (원래는 포장클래스-> 객체생성of,from 스새틱메서드에서)
		// InputView.getInput();
		OutputView.printInputNameInstruction();

		// 9. thr이후에도 작동하게 할려면 catch로 해당thr를 잡아줘야한다.
		// try {
		// 	List<String> names = InputView.getNames();
		// } catch (IllegalArgumentException e) {
		// 	// 10. thr났다면, 그 에러메세지 앞에 [ERROR]를 추가해서 출력하고
		// 	// --> 재귀처럼 한번더 getNames()를 호출해야한다. try ~ 를 호출해야한다.
		// 	// -> getNames에 try catch까지 다 넣는다.
		//
		// }

		//13. thr나면 catch에서 return 재귀되는 getNames() 재귀함수를 다시 불러보자.
		List<String> names = InputView.getNames();
		System.out.println(names);


		Integer count = InputView.getCount();

	}
}
