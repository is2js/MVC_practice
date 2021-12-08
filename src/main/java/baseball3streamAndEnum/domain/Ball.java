package baseball3streamAndEnum.domain;

import java.util.Objects;

public class Ball implements Comparable<Ball> {
	private final Value value;
	private final Index index;

	public Ball(String value, int i) {
		this.value = new Value(Integer.parseInt(value));
		this.index = new Index(i);
	}

	public static Ball of(String value, int i) {
		return new Ball(value, i);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ball ball = (Ball)o;
		return value.equals(ball.value) && index.equals(ball.index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, index);
	}

	@Override
	public int compareTo(Ball otherBall) {
		return Integer.compare(this.index.getIndex(), otherBall.getIndex());
	}

	public int getIndex() {
		return this.index.getIndex();
	}

	public void match(Balls computerBalls) {
	}

	public Result match(Ball computerBall) {
		//  [개념]2 -> 같은 객체끼리 ===의 비교가 예상된다면,
		// -> 넘어온 객체는 getter -> 인변 2개이상 && 같다.
		// --> equals를 오버라이딩해서 써라.
		// if (this.value == computerBall.getIndex() && )
		// if (this.equals(computerBall)) {

		//  [개념]12 -> 오호.. 같은 객체끼리의 비교일 때,
		// -> 들어오는 객체는 getter없이 private변수에 바로 접근 가능하네..
		// if (this.value.equals(computerBall.value)) {
		// if (this.value.equals(computerBall.value) && this.index.equals(computerBall.index)) {
		if (this.equals(computerBall)) {
			//  [개념3] 결과값이 상수1개면 ENum의 상수객체로 정의해보자.
			return Result.STRIKE;
		}
		//  [개념4] 최소단위객체의 변수도 인트(index, value)도 래핑한다면
		// ->  this.index == 같은객체.getIndex() &&
		// -> this.value == 같은객체.getValue()
		// --> 대신에, 각각도 equals를 오버라이딩해서 쓸 수 있다.
		// -> 참고로 최소단위객체를 래핑해서 equals하면 -> 모든 변수들 다 같음 index == && value == 를 equals로 대신 쓸 수 있다.
		// -> 하지만, compare > < 를 위해서는.. return  this.숫자 - o.getter() 시 게터가 필수다..
		if (this.value.equals(computerBall.value) && !this.index.equals(computerBall.index)) {
			return Result.BALL;
		}

		return Result.NOTHING;
	}

	public int getValue() {
		return this.value.getValue();
	}
}
