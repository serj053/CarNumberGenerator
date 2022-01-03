public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");

        Basket basketWithLimit = new Basket(3000);
        basketWithLimit.add("Choсolate", 200);
        basketWithLimit.print("Я купил вот что ");

        Basket basketWithWeight = new Basket();
        basketWithWeight.add("Potato", 50, 0, 200.4);

        System.out.println("Средняя цена товаров - " + Basket.getAveragePrice());

    }
}
