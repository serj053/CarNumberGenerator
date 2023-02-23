import java.io.File;

public class Distribution {
    public static File[][] getArrays(File[] files, int countOfThreads) {
        int arraySize = files.length;
        int arraysInThread = arraySize / countOfThreads;
        int remains = arraySize % countOfThreads;
        File[][] filesArray = new File[countOfThreads][];
        for (int i = 0; i < countOfThreads; i++) {
            filesArray[i] = new File[arraysInThread + ((remains-- > 0) ? 1 : 0)];
        }
        for (int x = 0; x < arraySize - 1; ) {
            for (int y = 0; y < countOfThreads; y++) {
                for (int z = 0; z < filesArray[y].length; z++) {
                    filesArray[y][z] = files[x];
                    x = x + 1;
                    if (x == countOfThreads)
                        break;
                }
            }
        }
        return filesArray;
    }
}
