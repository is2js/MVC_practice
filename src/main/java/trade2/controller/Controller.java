package trade2.controller;

import trade2.domain.Money;
import trade2.domain.Item;
import trade2.view.InputView;
import trade2.view.OutputView;

public class Controller {

	public void run() {
		OutputView.printTitle();

		OutputView.printNameInstruction();
		String itemName = InputView.getInput();

		OutputView.printMoneyInstruction();
		String money = InputView.getInput();
		Money itemMoney = new Money(money);

		Item item = new Item(itemName, itemMoney);
		OutputView.printItem(item);
	}
}
