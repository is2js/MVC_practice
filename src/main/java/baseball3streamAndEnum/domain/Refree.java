package baseball3streamAndEnum.domain;

import baseball3streamAndEnum.utils.Util;

public class Refree {
	private static final Refree refree = new Refree();
	private Balls playerBalls;
	private Balls computerBalls;

	private Refree() {
	}

	public static Refree getInstance() {
		return refree;
	}

	public void init() {
		this.playerBalls = null;
		this.computerBalls = Balls.from(Util.randomNumberGenerator());
	}

	public void insert(Balls playerBalls) {
		this.playerBalls = playerBalls;
		// this.computerBalls = computerBalls;
	}

	public ResultTotal playGame() {
		return this.playerBalls.match(this.computerBalls);
	}

	public String analyzeResult(ResultTotal resultTotal) {
		// [개념]9: 이제 결과값List들을 [일급컬렉션]으로 만들어놨다. -> stream으로 찢으면서& 필요한 메서드들을 컬렉션안에 모아보자.
		// -> [[일단 단순list모은 일급컬렉션은.. 객체.메서드()]]만 호출해준다. -> 내부에서 this.stream().메서드 호출() 한번 더 하기 때문
		int strikeCount = resultTotal.getStrikeCount();
		System.out.println("스트라이크 갯수 : " + strikeCount);
		int ballCount = resultTotal.getBallCount();
		System.out.println("볼 갯수 : " + ballCount);

		if (strikeCount + ballCount == 0) {
			return "낫싱";
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (ballCount > 0) {
			stringBuilder.append(ballCount + "볼 ");
		}
		if (strikeCount > 0) {
			stringBuilder.append(strikeCount + "스트라이크");
		}
		return stringBuilder.toString().trim();

	}
}
