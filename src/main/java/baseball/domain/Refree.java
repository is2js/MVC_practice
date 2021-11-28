package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class Refree {

	public String compare(List<Integer> computerNumbers, List<Integer> playerNumbers) {
		int sameNumberCount = countSameNumber(computerNumbers, playerNumbers);
		if (sameNumberCount == 0) {
			return "낫싱";
		}
		int strikeCount = countStrike(computerNumbers, playerNumbers);
		int ballCount = sameNumberCount - strikeCount;

		StringBuffer result = new StringBuffer();
		if (ballCount >= 1) {
			result.append(ballCount + "볼 ");
		}
		return result.append(strikeCount + "스트라이크").toString();
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
		if (stringNumbers.length() !=3 ) {
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
				// System.err.println(e);
				throw new IllegalArgumentException("숫자를 입력해주세요.");
			}
		}
		return playerNumbers;
	}
}
