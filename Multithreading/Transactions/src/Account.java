public class Account {
    private Bank bank;
    private long money;
    private String accNumber;

    public Account(Bank bank, String accNumber, long money) throws InterruptedException {
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

    public void notifyAcc() throws InterruptedException {
        getBalance();
      //  notify();
    }

    public synchronized long getBalance() throws InterruptedException {
 //       System.out.println("In balance " + bank.getBalance(this.accNumber) + "  " + Thread.currentThread().getName());
//        notify();
//        wait();
        return bank.getBalance(this.accNumber);
    }

//
//    public synchronized void getSumAllBalance() throws InterruptedException {
//        System.out.println(bank.getSumAllAccounts());
//        notify();
//        wait();
//    }

}
