public class Account {
    private final Bank bank;
    private long money;
    private String accNumber;

    public Account(Bank bank, String accNumber, long money) {
        this.bank = bank;
        this.money = money;
        this.accNumber = accNumber;
        System.out.println("создан аккаунт " + this.accNumber);
    }

    public long getMoney() {
        return money;
    }

    public long getMoney(long getMoney) {
        if (money < getMoney) {
            System.out.println("Недостаточно средств. Отказано в переводе.");
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

    public synchronized void transferPush(String to, long money) throws InterruptedException {
        bank.transfer(this.accNumber, to, money);
    }

        public synchronized long getBalance() throws InterruptedException {
        return bank.getBalance(this.accNumber);
    }

}
