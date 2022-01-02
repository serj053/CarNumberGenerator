public class Dimensions {
    private final double height;
    private final double width;
    private final double length;

    public Dimensions(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double getVolume(){
        return height * width * length;
    }

    public double getHeight() {
        return height;
    }

    public Dimensions setHeight(double height) {
        return new Dimensions(height, width, length);
    }

    public double getWidth() {
        return width;
    }

    public Dimensions setWidth(double width) {
        return new Dimensions(height, width, length);
    }

    public double getLength() {
        return length;
    }

    public Dimensions setLength(double lenght) {
        return new Dimensions(height, width, length);
    }
}
