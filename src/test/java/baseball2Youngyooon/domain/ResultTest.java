package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

	@ParameterizedTest
	@CsvSource({"125,2스트라이크", "321,2볼 1스트라이크", "456,낫싱"})
	@Test
	void Result_report_테스트(String numbers, String expected) {
		Balls comBalls = Balls.from("123");// 고정
		Balls myBalls = Balls.from(numbers);
		Result result = myBalls.compare(comBalls);

		String report = result.report();

		assertThat(report).isEqualTo(expected);
	}

	//2.
	@ParameterizedTest
	@CsvSource({"123,true", "321,false", "456,false"})
	@Test
	void Result_isThreeStrike_테스트(String numbers, boolean expected) {
		Balls comBalls = Balls.from("123");// 고정
		Balls myBalls = Balls.from(numbers);
		Result result = myBalls.compare(comBalls);

		boolean threeStrike = result.isThreeStrike();

		assertThat(threeStrike).isEqualTo(expected);
	}
}
