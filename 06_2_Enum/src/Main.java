public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator(23.5, 56.7);
        System.out.println("Сложение " + calculator.operation(Operation.ADD));
        System.out.println("Вычитание " + calculator.operation(Operation.SUBTRACT));
        System.out.println("Умножение " + calculator.operation(Operation.MULTIPLY));
    }
}
