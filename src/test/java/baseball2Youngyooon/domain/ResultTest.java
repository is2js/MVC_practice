package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {
	//7.
	// 1) 객체발생시 예외발생 및
	// 2) 객체List vs 객체List 비교후 -> 결과EnumList의List -> 갯수세기가 아니라
	// -> 결과값결과EnumList의List에서, 갯수를 바탕으로 존재하는 것들을 -> String으로 report받기

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
}
