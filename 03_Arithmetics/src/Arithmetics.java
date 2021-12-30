public class Arithmetics {
    private int number1;
    private int number2;

    public Arithmetics(int number1, int number2){
        this.number1 = number1;
        this.number2 = number2;
    }

    public int doSum(){
        return number1 + number2;
    }

    public int doMultiplication(){
        return number1 * number2;
    }

    public int getMax(){
        return (number1 > number2)? number1: number2;
    }

    public int getMin(){
        return (number1 > number2)? number2 : number1;
    }
}
