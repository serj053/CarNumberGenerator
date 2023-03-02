import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {


    private Map<String, Account> accounts = new HashMap<>();

    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account from = accounts.get(fromAccountNum);
        Account to = accounts.get(toAccountNum);
        to.setMoney(from.getMoney(amount));
        boolean testFraud = false;
        if (amount > 50_000) {
           testFraud = isFraud(from.getAccNumber(), to.getAccNumber(), amount);
        }
        if(testFraud){
            System.out.println("Платеж проверку не прошел.");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return 0;
    }

    public long getSumAllAccounts() {
        return 0;
    }

    public void setAccount(String str, Account account) {
        this.accounts.put(str, account);
    }

    public Map<String, Account> getAccount() {
        return accounts;
    }
}
