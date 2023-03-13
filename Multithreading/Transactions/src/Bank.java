import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
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
        if (amount > 50_000) {
            if (isFraud(from.getAccNumber(), to.getAccNumber(), amount)) {
                System.out.println("Платеж от " + fromAccountNum + " к " + toAccountNum + " проверку не прошел. Отказано в переводе.");
            } else {
                to.setMoney(from.getMoney(amount));
                System.out.println("Платеж от " + fromAccountNum + " к " + toAccountNum + " прошел.");
            }
        } else {
            to.setMoney(from.getMoney(amount));
            System.out.println("Платеж от " +fromAccountNum + " к " + toAccountNum+ " прошел.");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum) throws InterruptedException {
        // System.out.println("In balance Bank "+ Thread.currentThread().getName());
        return accounts.get(accountNum).getMoney();
    }

    public synchronized long getSumAllAccounts() throws InterruptedException {
        long totalSum = 0;
        for (Map.Entry<String, Account> set : accounts.entrySet()) {
            totalSum += set.getValue().getMoney();
        }
        return totalSum;
    }

    public synchronized void setAccount(String str, Account account) throws InterruptedException {
        this.accounts.put(str, account);
//        notify();
//        wait();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public synchronized void leaveAll() {
        System.out.println("In leaveAll " + Thread.currentThread().getName());
        notifyAll();
    }
}

