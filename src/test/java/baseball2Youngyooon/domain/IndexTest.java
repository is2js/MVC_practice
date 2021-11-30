package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IndexTest {
	@DisplayName("Index 입력 예외발생 검증")
	@ParameterizedTest
	@CsvSource({"1", "2", "3", "4"})
	@Test
	void checkValidTest(int index) {
		assertThatThrownBy(() -> new Index(index)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Index 입력 통과 검증")
	@ParameterizedTest
	@CsvSource({"0", "1", "3"})
	public void checkValidPassTest(int index) {
		Index index1 = new Index(index);
	}
}
