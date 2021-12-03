package racingCar1My.controller;

import java.util.List;

import racingCar1My.domain.Cars;
import racingCar1My.domain.RacingGame;
import racingCar1My.view.InputView;
import racingCar1My.view.OutputView;

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
		OutputView.printInputCountInstruction();
		Integer count = InputView.getCount();
		System.out.println(count);

		//17. 입력받은 2개의 수, 관리자인 싱글톤객체 racingGame이 conut만큼 움직이게 한다?
		// -> 일단 입력받은 names로 Car를? Cars를 만들어야할 듯
		// Cars.from()
		Cars cars = Cars.from(names);
		//20. 이제 만들어잔 Cars, count가지고 놀아야한다..
		// -> racinggame이 만들어진 cars를 관리하도록, 관리볏누에 넣어주자.
		// (파라미터로 줘도 되긴할 것 같은데.. 싱글톤 관리자가 핵심로직 데이터를 관리하게 하자~!!!!!!!!!!!!!
		racingGame.setCars(cars);
		for (int i = 0; i < count; i++) {
			// racingGame.race(cars);
			racingGame.race();
			//22. 각 Car마다 랜덤수-> position증가 시켰다.
			// -> 이제 결과값을 저장하고, 그걸 출력해야함.
		}
		//23. racingGame에 저장된 cars 정보를 이용해서 각각을 프린트해보자.
		List<String> results = racingGame.getResults();
		OutputView.printResult(results);
		List<String> winnerNames = racingGame.findWinners();
		OutputView.printWinners(winnerNames);

	}
}
