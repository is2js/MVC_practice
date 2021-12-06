package racingCar2YoungyoonForStream.controller;

import java.util.stream.IntStream;

import racingCar2YoungyoonForStream.domain.Cars;
import racingCar2YoungyoonForStream.domain.Racing;
import racingCar2YoungyoonForStream.view.InputView;
import racingCar2YoungyoonForStream.view.OutputView;

public class Controller {
	public void runRacing() {
		//1.
		Racing racing = Racing.getInstance();
		racing.init();

		//2.
		OutputView.printInputNameInstruction();
		racing.insert(Cars.from(InputView.getNames()));
		OutputView.printInputCountInstruction();
		IntStream.range(0, InputView.getCount())
			.forEach(i -> {
				racing.game();
				OutputView.printRoundInfo(racing.getRoundInfo());
			});

		//3. 우승자 -> maxPosition 찾기 추출하기 가져오기
		OutputView.printWinner(racing.findNamesOfWinner());

		//4. 우승자 이름을 toString()에 정의된대로 바로 추출?
		String s = racing.getNamesofWinners();
		System.out.println(s);

	}
}
