package baseball2Youngyooon.domain;

public class Ball {
	private Index index;
	private Number number;

	private Ball(int index, int number) {
		this.index = new Index(index);
		this.number = new Number(number);
	}

	static Ball of(int index, int number) {
		return new Ball(index, number);
	}

	Score compare(Ball ball) {
		if (isStrike(ball)) {
			return Score.STRIKE;
		}
		if (isBall(ball)) {
			return Score.BALL;
		}
		return Score.NOTHING;
	}

	private boolean isBall(Ball ball) {
		return !this.index.equals(ball.index) && this.number.equals(ball.number);
	}

	private boolean isStrike(Ball ball) {
		return this.index.equals(ball.index) && this.number.equals(ball.number);
	}

}
