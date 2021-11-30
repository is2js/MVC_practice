package baseball2Youngyooon.domain;

public class Ball {
	// 1.예외처리 등 추가작업이 필요한 원시변수(int) 클래스로 감싸서 객체로 만든다.
	// -> 이전(trade)에는 string money -> int money로 변환등의 추가작업이 필요해 class로 만들었었다.
	private Index index;
	private int number;

	public Ball(int index, int number) {
		//8. 생성자의 파라미터는 그대로 받게 하는데
		// -> 내부에서 new 포장된원시타입객체를 생성하게해서 넘겨준다.
		// -> index는 따로 of로 생성안하나보다...
		this.index = new Index(index);
		this.number = number;
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
		// 9. 포장된원시타입변수는 equals도 오버라이딩 해줬으니
		// -> 직접 꺼내서 비교하지말자. 원래는 this.index(Index).index or
		// -> 포장된원시타입은 한번더 거쳐서 들어가야하므로 엄청 복잡한데
		// -> 아예 직접안꺼내고 비교되도록 equals를 정의한 듯 싶다.
		// return this.index != ball.index && this.number == ball.number;
		return !this.index.equals(ball.index) && this.number == ball.number;
	}

	private boolean isStrike(Ball ball) {
		// return this.index == ball.index && this.number == ball.number;
		return this.index.equals(ball.index) && this.number == ball.number;
	}

}
