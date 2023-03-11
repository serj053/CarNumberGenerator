import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class TestBank {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        Account acc1 = new Account(bank, "acc1", 170_000);
        Account acc2 = new Account(bank, "acc2", 290_000);
        Account acc3 = new Account(bank, "acc3", 73_000);
        Account acc4 = new Account(bank, "acc4", 450_000);

        bank.setAccount(acc1.getAccNumber(), acc1);
        bank.setAccount(acc2.getAccNumber(), acc2);
        bank.setAccount(acc3.getAccNumber(), acc3);
        bank.setAccount(acc4.getAccNumber(), acc4);

        new Thread(()->{
            try {
                acc1.transferPush(acc2.getAccNumber(),55000);
                System.out.println(acc1.getBalance());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                acc1.transferPush(acc3.getAccNumber(),55000);
                System.out.println(acc1.getBalance());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();;


        new Thread(()->{
            try {
                acc1.transferPush(acc2.getAccNumber(),55000);
                System.out.println(acc1.getBalance());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                acc1.transferPush(acc3.getAccNumber(),55000);
                System.out.println(acc1.getBalance());
                System.out.println(bank.getSumAllAccounts());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();;


    }


}
