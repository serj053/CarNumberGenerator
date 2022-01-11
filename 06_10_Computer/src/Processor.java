public class Processor {
    private final double frequency;
    private final int numberCores;
    private final String manufacturer;
    private final double weight;

    public Processor(double frequency,int numberCores, String manufacturer, double weight ){
        this.frequency = frequency;
        this.numberCores = numberCores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }

    public double getFrequency(){
        return frequency;
    }

    public int getNumberCores(){
        return numberCores;
    }

    public String getManufacturer(){
        return manufacturer;
    }
}
