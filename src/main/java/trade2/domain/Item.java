package trade2.domain;

public class Item {
	String itemName;
	Money itemMoney;

	public String getItemName() {
		return itemName;
	}

	public Money getItemMoney() {
		return itemMoney;
	}

	public Item(String itemName, Money itemMoney) {
		this.itemName = itemName;
		this.itemMoney = itemMoney;
	}

}
