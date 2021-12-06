package racingCar1My.controller;

import java.util.List;

import racingCar1My.domain.Cars;
import racingCar1My.domain.RacingGame;
import racingCar1My.view.InputView;
import racingCar1My.view.OutputView;

public class Controller {
	public void runGame() {

		//TODO: 메인로직
		//1. 싱글톤 게임관리자 작성 -> init()까지
		RacingGame racingGame = RacingGame.getInstance();
		racingGame.init();

		OutputView.printInputNameInstruction();
		List<String> names = InputView.getNames();

		OutputView.printInputCountInstruction();
		Integer count = InputView.getCount();

		Cars cars = Cars.from(names);
		// -> racinggame이 만들어진 cars를 관리하도록, 관리볏누에 넣어주자.
		// (파라미터로 줘도 되긴할 것 같은데.. 싱글톤 관리자가 핵심로직 데이터를 관리하게 하자~!!!!!!!!!!!!!
		// racingGame.setCars(cars);
		racingGame.insertCars(cars);
		for (int i = 0; i < count; i++) {
			// racingGame.race(cars);
			racingGame.race();
			//22. 각 Car마다 랜덤수-> position증가 시켰다.
			// -> 이제 결과값을 저장하고, 그걸 출력해야함.

			//new1 한꺼번에 출력이 아니라, 매번 출력해야한다면.. 반복마다 출력해야한다.

			OutputView.printThisRound(racingGame.getRound());
		}


		//23. racingGame에 저장된 cars 정보를 이용해서 각각을 프린트해보자.
		List<String> results = racingGame.getResults();
		OutputView.printResult(results);
		List<String> winnerNames = racingGame.findWinners();
		OutputView.printWinners(winnerNames);


	}
}
