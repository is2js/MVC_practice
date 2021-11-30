package baseball2Youngyooon.domain;

public enum Score {
	STRIKE,
	BALL,
	NOTHING;

	//16. enum은 해당 상수가 맞는지 메서드로 작성 1) 해당결과값 맞는지 -> 2) 해당 결과값enum의 갯수 뽑기 위한 stream의 filter
	// -> 직전공부에서는 expected정답으로 enum을 입력못해서.. 파라미터로 expected를 맞춰갔음.
	// --> Score expected = Score.STRIKE;
	// --> playerBall.compare(computerBall).isEqualTo( expected )
	// -> 지금은 is해당Enum()메서드를 만들어서
	// --> Test시   결과값까지 계산 -> (isEqualTo 대신) is그Enum()?.isTrue()로 expected를 줄 것이다.
	// ---> Scores나 ScoresList인 Result등에서... stream .filter로 is~Enum()을 걸어서, 결과값 중 원하는 결과만 취하게 할 것이다.
	// Score : 각분기별 결과값 상수에 대해 -> isEnum? 이면 true를 반환하도록 메소드 작성
	// Scores: [stream -> score -> filter isEnum 원하는 결과가 맞니? ] -> 원하는 결과를 .count()
	// Result: [stream -> Scores -> 여러 결과값 중 원하는 것의 갯수] -> 원하는 결과들 갯수들을 총 .sum()
	// -> 요약 : 1:m비교시 쓸,결과값List에서, filter(Enum::isEnum)으로,  원하는 결과값 여부와 갯수를 판단가능
	//          n:m비교시 쓸, 결과값List의 List에서, 1차) mapToInt 각 결과값List별 원하는 결과값의 갯수로 변경 -> 2) 각 결과값갯수List를 총.suM()

	// -> 필요에 의해 작성하도록, Scores부터 가보자.

	//19. 필요에 의해 isEnum메소드를 작성하게 되었다.
	// -> 이것은 static메소드가 아니므로, Enum객체.xxx로 호출되어야한다.
	// -> Enum객체라 함은, Enum내용들이며, this == 해당ENum 을 주면 된다.
	// --> stream()을 타고있는 각 Enum(결과값)마다 객체처럼.isEnum메서드()를 호출할 예정임
	public boolean isStrike() {
		return this == STRIKE;
	}
	public boolean isBall() {
		return this == BALL;
	}
}
