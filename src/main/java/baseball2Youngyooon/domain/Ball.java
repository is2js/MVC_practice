package baseball2Youngyooon.domain;

public class Ball {
	private Index index;
	//1 . number도 입력제한 받아야해서 클래스로 포장한다.
	// private int number;
	private Number number;

	public Ball(int index, int number) {
		this.index = new Index(index);
		// 7. 이제 검증추가처리된 포장된원시타입으로 변형해서 받아주자.
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
		//6. 꺼내고 꺼내서 == 대신 포장원시객체.equals로  원시변수 안꺼내고 내부에서 처리
		// ex> this.number.number == ball.number.number
		// return !this.index.equals(ball.index) && this.number == ball.number;
		return !this.index.equals(ball.index) && this.number.equals(ball.number);
	}

	private boolean isStrike(Ball ball) {
		// return this.index.equals(ball.index) && this.number == ball.number;
		return this.index.equals(ball.index) && this.number.equals(ball.number);
	}

}
