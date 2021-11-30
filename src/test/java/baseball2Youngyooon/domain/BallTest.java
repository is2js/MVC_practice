package baseball2Youngyooon.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BallTest {
	@Test
	void testStrike() {
		Ball computerBall = Ball.of(0, 1);
		Ball playerBall = Ball.of(0, 1);

		Score expected = Score.STRIKE;
		Score actual = playerBall.compare(computerBall);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testBall() {
		Ball computerBall = Ball.of(0, 1);
		Ball playerBall = Ball.of(1, 1);

		Score expected = Score.STRIKE;
		Score actual = playerBall.compare(computerBall);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testNothing() {
		Ball computerBall = Ball.of(0, 1);
		Ball playerBall = Ball.of(0, 2);

		Score expected = Score.STRIKE;
		Score actual = playerBall.compare(computerBall);

		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("스트라이크 여러 케이스 테스트")
	@ParameterizedTest
	@CsvSource({"0,1,0,1", "1,0,1,0"})
	public void testCompareAll(int computerIndex, int computerNumber, int playerIndex, int playerNumber) {
		Ball computerBall = Ball.of(computerIndex, computerNumber);
		Ball playerBall = Ball.of(playerIndex, playerNumber);

		Score expected = Score.STRIKE;
		Score actual = playerBall.compare(computerBall);

		assertThat(actual).isEqualTo(expected);
	}

	//
}
