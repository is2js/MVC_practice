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
		if (hasBall()) {
			reportString.append(this.getBallSum() + "볼" + " ");
		}
		if (hasStrike()) {
			reportString.append(this.getStrikeSum() + "스트라이크");
		}
		if (isNothing()) {
			reportString.append("낫싱");
		}

		return reportString.toString().trim();
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

	//1. isThreeStrike는 따로구현 for 게임종료의 조건이라서 public으로 따로 구현 -> 테스트에서 확인
	public boolean isThreeStrike() {
		return getStrikeSum() == 3;
	}
}
