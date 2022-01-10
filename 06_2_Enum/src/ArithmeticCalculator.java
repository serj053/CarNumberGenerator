public class ArithmeticCalculator {
    private final double var1;
    private final double var2;

    public ArithmeticCalculator(double var1, double var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public double operation(Operation operation) {
        switch (operation) {
            case ADD:
                return var1 + var2;
            case SUBTRACT:
                return var1 - var2;
            case MULTIPLY :
                return var1 * var2;
        }
        return 0;
    }
}
