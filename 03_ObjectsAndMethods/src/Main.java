public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");

        Basket basketWithLimit = new Basket(3000);
        basketWithLimit.add("Choсolate", 200);
        basketWithLimit.print("Я купил вот что ");

        Basket basketWithWeight = new Basket();
        basketWithWeight.add("Potato", 30, 200.1);
        basketWithWeight.print("Докупил ");

        //basketWithWeight.add("Potato", 60, 500.5);
        //basketWithWeight.print("Докупил ");

    }
}
