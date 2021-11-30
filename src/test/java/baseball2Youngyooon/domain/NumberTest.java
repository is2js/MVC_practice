package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberTest {

	@ParameterizedTest
	@CsvSource({"0", "1", "9", "10"})
	@Test
	void Number예외발생_테스트(int number) {
		assertThatThrownBy(() -> new Number(number)).isInstanceOf(IllegalArgumentException.class);

	}
}
