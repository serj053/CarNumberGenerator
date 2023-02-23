import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File files = new File("data/src");
        File[] file = files.listFiles();
        BufferedImage image = ImageIO.read(file[0]);
        image = Scalr.resize(image,
                Scalr.Method.ULTRA_QUALITY,
                Scalr.Mode.FIT_EXACT,
                200,300);
            ImageIO.write(image, "jpg"
                    , new File("data/dst/scalr.jpg"));
    }

}
