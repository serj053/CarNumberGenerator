public class Storage {
    private final String type;
    private final double memoryCapacity;
    private final double weight;

    public Storage(String type, double memoryCapacity, double weight){
        this.type = type;
        this.memoryCapacity = memoryCapacity;
        this.weight = weight;
    }

    public double getWeight(){
        return weight;
    }

    public String getType(){
        return type;
    }

    public double getMemoryCapacity(){
        return memoryCapacity;
    }
}
