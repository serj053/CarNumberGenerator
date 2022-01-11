public class Screen {
    private final double diagonal;
    private final String type;  // (IPS, TN, VA),
    private final double weight;

    public Screen(double diagonal, String type, double weight){
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    double getWeight(){
        return weight;
    }

    public String getType(){
        return type;
    }

    public double getDiagonal(){
        return diagonal;
    }
}
