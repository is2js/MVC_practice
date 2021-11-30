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
