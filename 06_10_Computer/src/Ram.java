public class Ram {
    private final String type;
    private final double volume;
    private final double weight;

    public Ram(String type, double volume, double weight){
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }

    public String getType(){
        return type;
    }

    public double getValue(){
        return volume;
    }
}
