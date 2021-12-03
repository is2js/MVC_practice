package calculatorby1regex.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	public static List<Integer> convertIntList(String[] splitedStringNumber) {
		return Arrays.stream(splitedStringNumber)
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
	}
}
