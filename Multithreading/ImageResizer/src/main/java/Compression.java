import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Compression implements Runnable {
    private final File[] files;
    private final int compressionRatio;
    Long start;
    int n;

    public Compression(File[] files, int compressionRatio, Long start, int n) {
        this.files = files;
        this.compressionRatio = compressionRatio;
        this.start = start;
        this.n = n;
    }

    public void run() {
        for (File file : files) {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int width = image.getWidth();
            int height = image.getHeight();
            int newWidth = (int) Math.round(width / (double) compressionRatio);
            int newHeight = (int) Math.round(height / (double) compressionRatio);
            BufferedImage newImage = new BufferedImage(
                    newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    int rgb = image.getRGB(
                            x * compressionRatio, y * compressionRatio);
                    newImage.setRGB(x, y, rgb);
                }
            }
            try {
                ImageIO.write(newImage, "jpg"
                        , new File("data/dst/" + file.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Duration " + n + "  " + (System.currentTimeMillis() - start));
    }

}
