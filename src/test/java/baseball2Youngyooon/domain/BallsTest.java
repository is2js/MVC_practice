package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

	@ParameterizedTest
	@ValueSource(strings = {"12", "1a3", "112"})
	@Test
	void Balls생성_예외_테스트(String stringNumbers) {
		// 4. 예외테스트는 메소드 인자내에서 예외발생하도록 코드를 다 던져야함.
		assertThatThrownBy(()-> Balls.of(stringNumbers)).isInstanceOf(IllegalArgumentException.class);
	}
}
