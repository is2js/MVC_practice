package racingCar3devhudiForWrap.domain;

import racingCar3devhudiForWrap.utils.Util;

public class OneMovable implements Movable {
	@Override
	public boolean isMovable() {
		return Util.getRandomNumber() >= 4;
	}
}
