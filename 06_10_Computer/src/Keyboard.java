public class Keyboard {
    private final String type;
    private final boolean isBacklight;
    private final double  weight;

    public Keyboard(String type, boolean isBacklight, double weight){
        this.type = type;
        this.isBacklight = isBacklight;
        this.weight = weight;
    }

    double getWeight(){
        return weight;
    }

    public String getType(){
        return type;
    }

    public boolean getBacklight(){
        return isBacklight;
    }
}
