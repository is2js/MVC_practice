package baseball2Youngyooon.domain;

//1. 위치(index)를 가진 숫자(number)를 class객체로 만든다.
public class Ball {
	private int index;
	private int number;

	// 2. 생성자는 2변수 모두 받아서 똑같이 만들되, private하게 아무대서나 못만들게 한다.
	public Ball(int index, int number) {
		this.index = index;
		this.number = number;
	}

	// 3. 객체생성(생성자호출)을 static of 메서드로 생성후 해당객체 반환하게 하자.
	// -> 파라미터는 생성자랑 똑같이 다받으나, 생성을 생성자가 아닌 of스태틱메서드 호출로 호출시마다 생성
	// -> of 스태틱메서드는 해당객체ClassType을 반환하게 한다.
	static Ball of(int index, int number) {
		return new Ball(index, number);
	}

	// 4. 객체의 기능후 (정해진 범위내)결과값을 enum을 반환하게 메서드를 작성하자.
	// -> 핵심로직 메서드를 최소단위객체를 직접 받게 한다.
	// -> 최소단위객체.메서드로()로 쓰일 인스턴스메서드지만, 최소단위객체끼리 비교시는 파라미터로 해당 객체를 받게 만든다.
	Score compare(Ball ball) {
		if (this.index == ball.index && this.number == ball.number) {
			return Score.STRIKE;
		}
		if (this.index != ball.index && this.number == ball.number) {
			return Score.BALL;
		}
		return Score.NOTHING;
	}

}
