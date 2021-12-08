package baseball3streamAndEnum.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;

public class Util {

	public static List<String> randomNumberGenerator() {
		return IntStream.range(0, 3)
			.map(i -> Randoms.pickNumberInRange(1, 9))
			.mapToObj(String::valueOf)
			.collect(Collectors.toList());

	}
}
