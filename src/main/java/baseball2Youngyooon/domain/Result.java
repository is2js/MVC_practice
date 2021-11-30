package baseball2Youngyooon.domain;

import java.util.List;

public class Result {

	private List<Scores> result;

	private Result(List<Scores> result) {
		this.result = result;
	}

	public static Result of(List<Scores> result) {
		return new Result(result);
	}

	//20. n:m결과로 나온 결과값List의 List다.
	// -> ~의 list 일급콜렉션은, 일단, 메서드로 stream().으로 찢어서 기능생성한다고 생각하자.
	// --> 그렇다면, stream으로 찢은 뒤, 나오는 결과값List (Scores)-> 거기에 정의해둔 함수를 이용해서 Scores::메소드 로 변환한다.
	// -> 각 결과값List마다 strike를 카운트, ball을 카운트하도록 해보자.
	// -> Enum부터 시작해서, 다 public으로 작성한다.
	// --> 이것도 내부에서 찢어서 세는 거라 파라미터 필요없다.
	// ---> list -> count로 인한 결과값이 int가 된다면, map이 아닌 mapToInt

	// --> my) 특정Enum의 갯수를 센다?  stream.  (filter + 하카::isXXX) + count
	// --> my) 결과값List의 List에서 특정Enum의 갯수 +의 총 갯수를 센다?
	public int getStrikeSum() {
		return result.stream()
			.mapToInt(Scores::getStrikeCount)
			.sum();
	}

	public int getBallSum() {
		return result.stream()
			.mapToInt(Scores::getBallCount)
			.sum();
	}

}
