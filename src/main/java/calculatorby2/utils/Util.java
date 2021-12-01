package calculatorby2.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
	public static List<Integer> extractOperands(String[] mathExressionArray) {
		List<Integer> operands = IntStream.range(0, mathExressionArray.length)
			.filter(i -> i % 2 == 0)
			.mapToObj(i -> mathExressionArray[i])
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
		return operands;
	}

	public static List<String> extractOperators(String[] mathExressionArray) {
		List<String> operands = IntStream.range(0, mathExressionArray.length)
			.filter(i -> i % 2 != 0)
			.mapToObj(i -> mathExressionArray[i])
			.collect(Collectors.toList());

		return operands;
	}

}
