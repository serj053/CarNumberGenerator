import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args)  {

        File srcDir = new File("data/src");
        File[] listImage = srcDir.listFiles();
        int countOfThreads = 8;
        int compressionRatio = 4;
        File[][] filesArray = Distribution.getArrays(listImage, countOfThreads);
        int n = 0;
        for (File[] files : filesArray) {
            n++;
            new Thread(new Compression(files, compressionRatio, System.currentTimeMillis(), n)).start();
        }

    }
}
