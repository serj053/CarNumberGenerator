import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestBank {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        /* Создание аккаунтов */
        for (int i = 0; i < 100; i++) {
            String accNumber = "408" + (int) (Math.random() * 100000);
            bank.setAccount(accNumber, new Account(bank, accNumber
                    , (int) (Math.random() * 100000)));
        }
        System.out.println(bank.getSumAllAccounts());

        /* подготовка и создание потоков */
        List<Thread> threadList = new ArrayList<>();
        Map<String, Account> accountsStore = bank.getAccounts();
        List<String> accountsSet = new ArrayList<>(accountsStore.keySet());

        int keys = bank.getAccounts().size();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            threadList.add(
                    new Thread(() -> {
                        int randonKey1 = random.nextInt(keys);
                        String from = accountsStore.get(accountsSet.get(randonKey1)).getAccNumber();
                        int randonKey2 = random.nextInt(keys);
                        String to = accountsStore.get(accountsSet.get(randonKey2)).getAccNumber();
                        try {
                            bank.transfer(from, to, (int) (Math.random() * 100_000));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    })
            );
        }
        threadList.forEach(Thread::start);

        boolean isAlive = true;
        while (isAlive) {
            isAlive = threadList.stream().anyMatch(Thread::isAlive);
            if(!isAlive){
                System.out.println(bank.getSumAllAccounts());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
