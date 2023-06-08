import java.io.FileOutputStream;

public class ConcatinationLoader {
    /*1538675 ms*/
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        FileOutputStream writer = new FileOutputStream("data/numbers.txt");
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        int regionCode ;
        for (regionCode = 0; regionCode < 200; regionCode++) {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String carNumber = firstLetter + padNumber(number, 3) +
                                    secondLetter + thirdLetter + padNumber(regionCode, 2);
                            writer.write(carNumber.getBytes());
                            writer.write('\n');
                        }
                    }
                }
            }
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
