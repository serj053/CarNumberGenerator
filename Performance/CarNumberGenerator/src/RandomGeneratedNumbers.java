import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class RandomGeneratedNumbers {
    /*19:40

   файлы    время       внутренний цикл
     1-     487521           300
     2 -    131630           200
     10-    107056           200
     50 -   84351            200
     100 -  86649, 80589     200
     200 -  81311            200
     500 -  105402           200
    * */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        long start = System.currentTimeMillis();
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        List<Thread> threads = new ArrayList<>();
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 500; i++) {
            int j = i;
            threads.add(
                    new Thread(() -> {
                        StringBuffer builder = new StringBuffer();
                        PrintWriter writer = null;
                        try {
                            writer = new PrintWriter("data/file" + j + ".txt");
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        for (; list.size() < 300_000;) {
                            builder.append(letters[(int) (Math.random() * 12)]);
                            builder.append(padNumber((int) (Math.random() * 1000), 3));
                            builder.append(letters[(int) (Math.random() * 12)]);
                            builder.append(letters[(int) (Math.random() * 12)]);
                            builder.append(padNumber((int) (Math.random() * 100), 2));
                            builder.append("\n");
                            list.add(builder.toString());
                            builder.delete(0, builder.length());
                        }
                        writer.write(list.toString());
                        writer.flush();
                        writer.close();
                    })
            );
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }


        System.out.println(System.currentTimeMillis() - start);
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }
}
