import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ThreadLoader {
    /*
     14537
     */
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        //количество используемых регионов
        int regionCount = 199;
        //количество файлов для записи
        int files = 10;
        //количество регионов в одном файле
        int threadsCount = regionCount / files;// + regionCount % files;
        System.out.println("regionCount "+ regionCount);
        System.out.println("threadCount "+ threadsCount);
        for (int i = 0; i < files; i++) {
            int n = i;
            threads.add(new Thread(() -> {
                        try {
                            getNumbers(n, threadsCount);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    })
            );
        }
        for (Thread str : threads) {
            str.start();
        }
        for (Thread jn : threads) {
            jn.join();
        }

        System.out.println(System.currentTimeMillis() - start);

    }

    public static void getNumbers(int nameSuffix, int regionCounts) throws FileNotFoundException {
        //long start = System.currentTimeMillis();
        /*PrintWriter класс который подбирает оптимальнйы размер буфера*/
        PrintWriter writer = new PrintWriter("data/file_" + nameSuffix + ".txt");
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        // int regionCode = 199;
        for (int regionCode = 1; regionCode <= regionCounts; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {//1_728_000
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            //builder можно создавать прямо в этом цикле
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
       // return System.currentTimeMillis() - start;
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        //StringBuilder builder = new StringBuilder();
       // builder.append(numberStr);
        for (int i = 0; i < padSize; i++) {
            //builder.insert(0,'0');
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
        //return builder.toString();
    }
}
