package baseball2Youngyooon.domain;

import java.util.List;

public class Scores {
	private List<Score> scores;

	private Scores(List<Score> scores) {
		this.scores = scores;
	}

	public static Scores from(List<Score> scores) {
		return new Scores(scores);
	}

	public int getStrikeCount() {
		return (int)this.scores.stream()
			.filter(Score::isStrike)
			.count();
	}

	public int getBallCount() {
		return (int)this.scores.stream()
			.filter(Score::isBall)
			.count();
	}

	public boolean hasBall() {
		return this.scores.stream().anyMatch(Score::isBall);
	}

	public boolean hasStrike() {
		return this.scores.stream().anyMatch(Score::isStrike);
	}
}
