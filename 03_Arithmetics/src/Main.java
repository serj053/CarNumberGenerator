public class Main {
    public static void main(String[] args) {
        Arithmetics arithmetics = new Arithmetics(23, 5);
        System.out.println("23 x 5 = " + arithmetics.doMultiplication());
        System.out.println("23 + 5 = " + arithmetics.doSum());
        System.out.println("max(23 ? 5) = " + arithmetics.getMax());
        System.out.println("min(23 ? 5) = " + arithmetics.getMin());
    }
}
