package trade.domain;

public class Item {

  private String name;
  private Money money;

  public Item(String name, Money money) {
    this.name = name;
    this.money = money;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Money getMoney() {
    return money;
  }

  public void setMoney(Money money) {
    this.money = money;
  }

  // toString을 오버라이딩하면, print하고 싶은 욕구를 controller에게 문자열로 전달할 수 있다.
  @Override
  public String toString() {
    return "상품명: " + this.name + ", 금액: " + this.money.getAmount();
  }
}
