package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class Refree {

	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final String NOTHING = "낫싱";
	public static final String BALL = "볼";
	public static final String STRIKE = "스트라이크";
	public static final int LIMITED_LENGTH = 3;

	public String compare(List<Integer> computerNumbers, List<Integer> playerNumbers) {
		int sameNumberCount = countSameNumber(computerNumbers, playerNumbers);
		if (sameNumberCount == ZERO) {
			return NOTHING;
		}
		int strikeCount = countStrike(computerNumbers, playerNumbers);
		int ballCount = sameNumberCount - strikeCount;

		StringBuffer result = new StringBuffer();
		if (ballCount >= ONE) {
			result.append(ballCount + BALL + " ");
		}
		return result.append(strikeCount + STRIKE).toString();
	}

	private int countStrike(List<Integer> computerNumbers, List<Integer> playerNumbers) {
		int countStrike = 0;
		for (int i = 0; i < playerNumbers.size(); i++) {
			if (playerNumbers.get(i) == computerNumbers.get(i)) {
				countStrike++;
			}
		}
		return countStrike;
	}

	public int countSameNumber(List<Integer> computerNumbers, List<Integer> playerNumbers) {
		int sameNumberCount = 0;
		for (Integer playNumber : playerNumbers) {
			if (computerNumbers.contains(playNumber)) {
				sameNumberCount++;
			}
		}
		return sameNumberCount;
	}

	public boolean isStrike(int position, int number) {
		return false;
	}

	public List<Integer> askNumbers(String stringNumbers) {
		return validAndConvertNumbers(stringNumbers);
	}

	private List<Integer> validAndConvertNumbers(String stringNumbers) {
		if (stringNumbers.length() != LIMITED_LENGTH) {
			throw new IllegalArgumentException("3자를 입력해주세요.");
		}

		List<Integer> playerNumbers = validFormatAndConvertList(stringNumbers);

		if (isDuplicated(playerNumbers)) {
			throw new IllegalArgumentException("같은 수를 2번 이상 입력하셨습니다.");
		}
		return playerNumbers;
	}

	private boolean isDuplicated(List<Integer> playerNumbers) {
		if (playerNumbers.size() != playerNumbers.stream().distinct().count()) {
			return true;
		}
		return false;
	}

	private List<Integer> validFormatAndConvertList(String stringNumbers) {
		List<Integer> playerNumbers = new ArrayList<>();
		for (String stringNumber : stringNumbers.split("")) {
			try {
				Integer integerNumber = Integer.valueOf(stringNumber);
				playerNumbers.add(integerNumber);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("숫자를 입력해주세요.");
			}
		}
		return playerNumbers;
	}
}
