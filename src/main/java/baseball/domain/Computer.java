package baseball.domain;

import static camp.nextstep.edu.missionutils.Randoms.*;

import java.util.ArrayList;
import java.util.List;

public class Computer {

	public static final int RANDOM_NUMBER_COUNT = 3;

	public List<Integer> createRandomNumbers() {
		ArrayList<Integer> numbers = new ArrayList<>();
		while (numbers.size() < RANDOM_NUMBER_COUNT) {
			int number = pickNumberInRange(1, 9);
			if (numbers.contains(number)) {
				continue;
			}
			numbers.add(number); // add하면서 자연스럽게 .size()도 카운트++; 됨.
		}
		return numbers;
	}
}
