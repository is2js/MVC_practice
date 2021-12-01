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

	public String report() {
		StringBuilder reportString = new StringBuilder();
		//3. if분기마다 메소드화하기 -> String을 return해주면 그걸 append하면 됨
		// -> 분기시에 바깥변수가 들어간다면, 파라미터로 받지말고, 바깥변수가 필요로하는 요소만  return해주도록 수정한다.
		reportString.append(reportBall());
		reportString.append(reportStrike());
		reportString.append(reportNothing());
		return reportString.toString().trim();
	}

	private String reportNothing() {
		if (isNothing()) {
			return "낫싱";
		}
		return "";
	}

	private String reportStrike() {
		if (hasStrike()) {
			return this.getStrikeSum() + "스트라이크";
		}
		return "";
	}

	private String reportBall() {
		if (hasBall()) {
			return this.getBallSum() + "볼" + " ";
		}
		return "";
	}

	private boolean isNothing() {
		return !hasBall() && !hasStrike();
	}

	private boolean hasStrike() {
		return this.result.stream().anyMatch(Scores::hasStrike);
	}

	private boolean hasBall() {
		return this.result.stream().anyMatch(Scores::hasBall);
	}

	public boolean isThreeStrike() {
		return getStrikeSum() == 3;
	}

	public boolean is3Strike() {
		return this.getStrikeSum() == 3;
	}
}
