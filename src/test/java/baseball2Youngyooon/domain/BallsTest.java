package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BallsTest {

	@ParameterizedTest
	@CsvSource({"125,2", "321,1"})
	@Test
	void Balls_vs_Balls_테스트(String numbers, int expected) {
		Balls comBalls = Balls.of("123");
		Balls playerBalls = Balls.of(numbers);

		Result result = playerBalls.compare(comBalls);
		int actual = result.getStrikeSum();

		assertThat(actual).isEqualTo(expected);

	}
}
