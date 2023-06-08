import java.io.FileOutputStream;

public class StringBuilderLoader {
    /*38423 ms*/
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("data/numbers.txt");
        StringBuilder builder = new StringBuilder();
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        // int regionCode = 199;
        for (int regionCode = 1 ;regionCode < 200; regionCode++) {
            for (int number = 1; number < 1000; number++) {

                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                            if(builder.length() > 1024){
                                writer.write(builder.toString().getBytes());
                                builder.delete(0, builder.length());
                                //builder = new StringBuilder();
                            }
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
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }
}
