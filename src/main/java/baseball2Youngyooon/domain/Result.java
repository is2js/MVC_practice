package baseball2Youngyooon.domain;

import java.util.List;

public class Result {

	private List<Scores> result;

	private Result(List<Scores> result) {
		this.result = result;
	}

	public static Result from(List<Scores> result) {
		return new Result(result);
	}

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

	//1. 만약, EnumList(Scores, 볼1개 -기준볼3개 비교결과)의 List(Result, 볼3개 전체 비교결과)지만,
	// -> 결과값을 Enum관련이 아닌 String으로 report받고 싶을 때
	// -> 내부 list에서 stream으로 찢어져서 확인하는 것이니, 파라미터Xs
	public String report() {
		//2. Enum분기별 갯수를 추출해서 보고받을 예정이다. -> StringBuild에 나올때마다 append해준다.
		StringBuilder reportString = new StringBuilder();
		// 3. << 있으면 뽑아내는 로직 >> 을     no stream일때의 if count >= 0이 아닌
		// 1) hasXXX 메서드로 싸일, this(list) stream (). -> anyMatCh( 하카Class:: has메서드 ) 연쇄한다.
		// if (this.result.stream().anyMatch(Scores::hasBall)) {
		// 	reportString.append(getBallSum() + "볼" + " ");
		// }
		// -> stream으로 찢어서, 그쪽에서의 메서드를 사용해야하니, Result -> 찢은 Scores로 가서 다시 한번 hasXXX == stream().anyMatch를 정의해주자.
		// 2) 그러면 Scores -> 각 Enum(Score)으로 anyMatch() 해주는 과정에서 (ENUM(Score)::isXXX)메서드가 활용된다.
		// --> hasXXX == anyMatch(찢Class::hasXXX) --> anyMatch(찢Enum :: isENum)으로 연결된다.
		// 3) 이제  if문의 this.result.stream().anyMatch()도 hasXXX메서드로 바꿔준다.
		if (hasBall()) {
			reportString.append(this.getBallSum() + "볼" + " ");
		}
		// 4. strike도 똑같이, 결과값ENum리스트에 포함하고 있으면 가져온다
		if (hasStrike()) {
			reportString.append(this.getStrikeSum() + "스트라이크");
		}
		// 6. 낫씽은, ball도 안가지고 잇고, strike도 안가지고 있을 때다.
		if (!hasBall() && !hasStrike()) {
			reportString.append("낫싱");
		}

		// 5. 공백은 .trim()으로 제거해주는 센스
		return reportString.toString().trim();
	}

	private boolean hasStrike() {
		return this.result.stream().anyMatch(Scores::hasStrike);
	}

	private boolean hasBall() {
		return this.result.stream().anyMatch(Scores::hasBall);
	}
}
