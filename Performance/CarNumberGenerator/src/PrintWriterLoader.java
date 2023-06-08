import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PrintWriterLoader {
    /*
      33311 ms
     */
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        //FileOutputStream writer = new FileOutputStream("data/numbers.txt");
        /*PrintWriter класс который подбирает оптимальнйы размер буфера*/
        PrintWriter writer = new PrintWriter("data/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        // int regionCode = 199;
        for (int regionCode = 1; regionCode < 200; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1_000; number++) {

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
            //размер буфера подбирается автоматически
            writer.write(builder.toString());
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }
}
