package baseball2Youngyooon.controller;

import org.junit.jupiter.api.Test;

import baseball2Youngyooon.domain.Computer;

class ComputerTest {

	@Test
	void init_makeRandomNumber_test() {
		for (int i = 0; i < 1000; i++) {
			Computer.getInstance().init();
		}
	}
}
