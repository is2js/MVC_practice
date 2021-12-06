package racingCar3devhudiForWrap.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import racingCar3devhudiForWrap.domain.Car;
import racingCar3devhudiForWrap.domain.Cars;
import racingCar3devhudiForWrap.domain.Count;
import racingCar3devhudiForWrap.domain.Names;
import racingCar3devhudiForWrap.domain.RacingGame;
import racingCar3devhudiForWrap.view.InputView;
import racingCar3devhudiForWrap.view.OutputView;

public class Controller {
	public void runRacing() {
		RacingGame racingGame = RacingGame.getInstance();
		racingGame.init();

		OutputView.printInputNameInstruction();
		Names names = Names.from(InputView.getNames());

		OutputView.printInputCountInstruction();

		Cars cars = Cars.from(names);
		racingGame.insertCars(cars);
		//new5. 카운트의 값을 꺼내서, 그만큼 반복(IntStream.Range or iter)가 아니라
		//-> 카운트에게 -1씩 + 0되기전까지 n -> 1까지 반복하도록 시키는 countdown isXXXEnd() boolean메서드를 만든다.
		Count count = Count.of(InputView.getCount());
		while (count.isRacingEnd()) {
			racingGame.startGame(cars);
			OutputView.printEveryResult(racingGame.getResult());
		}
		// racingGameWithCount(racingGame, names, Count.of(InputView.getCount()));

		OutputView.printWinner(findWinnerNames(racingGame));

	}

	private String findWinnerNames(RacingGame racingGame) {
		List<Car> winners = racingGame.findWinners();
		return winners.stream()
			.map(Car::getName)
			.collect(Collectors.joining(","));
	}

	private void racingGameWithCount(RacingGame racingGame, Names names, Count count) {
		Cars cars = Cars.from(names);
		racingGame.insertCars(cars);

		IntStream.range(0, count.getCount())
			.forEach(i -> {
				racingGame.startGame(cars);
				OutputView.printEveryResult(racingGame.getResult());
			});
	}

}
