package baseball2Youngyooon.domain;

import java.util.List;

//10.
public class Scores {
	private List<Score> scores;

	private Scores(List<Score> scores) {
		this.scores = scores;
	}

	public static Scores of(List<Score> scores) {
		return new Scores(scores);
	}

	//17. 일급콜렉션, list를 싸는 class이며, 현재는 추가작업없이 of스태틱메소드로 생성하는 기능만 있으나
	// -> **결과값(Enum) List라면, 원하는 결과값(Enum)의 갯수를 샐 수 있어야한다.**
	// -> 그러려면, stream으로 들어오는 결과값마다 -> map[ Enum클래스(Score)::is각결과값T/F여부 ) ]를 적용후 .count()를 세야하므로
	// -> Scores의 원하는 결과값Count함수를 작성하면서 -> Score::isEnum도 같이 작성해보자.

	// --> EnumList 객체에서, 원하는 이넘의 갯수만 파악하는 메소드 작성해보기~!
	// ---> 내 안(this가 list)에서 세는 것이라.. 파라미터가 필요없다~ 조건절만 있음 된다?
	// ---> strike, ball마다 함수로 세는 건 반복되는 것 같긴한데...
	public int getStrikeCount() {
		return (int)this.scores.stream()
			.filter(Score::isStrike)
			.count();
		//18. 이렇게, 결과값Enum에 맞는지 isEnum 메소드를 작성하게 하고, 결과값List는 그것을 filter로 이용해서 갯수를 세게 한다.
		// -> 다 구하고 나면, count()는 Integer를 반환하므로 cast도 따로 해야하나보다.
	}
	public int getBallCount() {
		return (int)this.scores.stream()
			.filter(Score::isBall)
			.count();
	}
}
