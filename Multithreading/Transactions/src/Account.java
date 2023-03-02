public class Account {
    private Bank bank;
    private long money;
    private String accNumber;

    public Account(Bank bank, long money, String accNumber) {
        this.bank = bank;
        this.money = money;
        this.accNumber = accNumber;
    }

    public long getMoney() {
        return money;
    }

    public long getMoney(long getMoney) {
        if (money < getMoney) {
            System.out.println("Недостаточно средств.");
            return 0;
        }
        money = money - getMoney;
        return getMoney;
    }

    public void setMoney(long money) {
        this.money = this.money + money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public Bank getBank() {
        return bank;
    }
}
