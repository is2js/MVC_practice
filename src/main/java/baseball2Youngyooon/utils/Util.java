package baseball2Youngyooon.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	public static List<Integer> ConvertStrToIntList(String stringBalls) {
		return Arrays.stream(stringBalls.split(""))
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
	}
}
