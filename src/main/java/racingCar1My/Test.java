package racingCar1My;

import racingCar1My.domain.Ship;

public class Test {
	public static void main(String[] args) {

		Ship 조재성 = Ship.of("조재성", 1);

		Ship 조재경 = Ship.of("조재경", 0);
		Ship 조아라 = Ship.of("조아라", 1);
		Ship 김석영 = Ship.of("김석영", 2);
		// System.out.println(김석영);
		System.out.println(조재성.compareTo(조재성));
		System.out.println(조재성.compareTo(조재경));
		System.out.println(조재성.compareTo(조아라));
		System.out.println(조재성.compareTo(김석영));

	}
}
