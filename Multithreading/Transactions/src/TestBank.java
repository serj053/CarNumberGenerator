import java.util.Map;

public class TestBank {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        Account acc1 = new Account(bank, 100000, "Serj");
        Account acc2 = new Account(bank, 5000, "Djo");
        Account acc3 = new Account(bank, 12000, "Bob");
        Account acc4 = new Account(bank, 5000, "Harry");
        Account acc5 = new Account(bank, 12000, "Pele");
        bank.setAccount(acc1.getAccNumber(), acc1);
        bank.setAccount(acc2.getAccNumber(), acc2);
        bank.setAccount(acc3.getAccNumber(), acc3);
        bank.setAccount(acc4.getAccNumber(), acc4);
        bank.setAccount(acc5.getAccNumber(), acc5);

        for (Map.Entry<String, Account> set : bank.getAccount().entrySet()) {
            System.out.println(set.getKey() + "  " + set.getValue().getMoney());
        }
        System.out.println("-------------------------------------------------");
        acc1.getBank().transfer(acc1.getAccNumber(), acc2.getAccNumber(), 51000);

        for (Map.Entry<String, Account> set : bank.getAccount().entrySet()) {
            System.out.println(set.getKey() + "  " + set.getValue().getMoney());
        }

        for (int i = 0; i < 1000; i++) {
            acc1.getBank().transfer(acc1.getAccNumber(), acc2.getAccNumber(), -1);
        }

        System.out.println();
        for (Map.Entry<String, Account> set : bank.getAccount().entrySet()) {
            System.out.println(set.getKey() + "  " + set.getValue().getMoney());
        }
    }
}
