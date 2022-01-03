public class Basket {

    public static void addTotalCount(int count) {
        totalCount += count;
    }

    public static void addTotalPriceAllBaskets(int price) {
        totalPriceAllBaskets += price;
    }

    //средняя цена по всем карзинам
    public static double getAveragePrice() {
        double average = 0;
        if (totalCount != 0)
            average = totalPriceAllBaskets / (double) totalCount;
        return average;
    }

    //количество товаров во всех карзинах
    private static int totalCount = 0;
    //общая сумма всех товаров всех карзин
    private static int totalPriceAllBaskets = 0;

    private String items;//имя товара
    private int count = 0;//количество единиц товара в карзине
    private int totalPrice = 0;//стоимость карзины
    private int limit;//предел суммы покупок
    private double totalWeight = 0;//суммарный вес товара

    public Basket() {
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + " " + items;
        this.totalPrice += totalPrice;
    }

    /*Добавлен метод*/
    public void add(String name, int price, int count, double weight) {
        if (add(name, price, count))
            totalWeight += weight;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public boolean add(String name, int price, int count) {

        boolean error = false;
        // если товар в каззине то не добавлять товар
        if (contains(name)) {//содержит ли строка слово
            error = true;
        }
        //если превышен лимит то не добавлять товар
        if (totalPrice + count * price >= limit) {
            error = true;//превышение лимита
        }
        //если одно из перечисленных выше условий верно то выйти из метода
        if (error) {
            System.out.println("Error occured :(");
            return false;
        }

        this.count += count;//количество в текущей карзине
        Basket.addTotalCount(this.count);//количество во всех карзинах
        totalPrice = totalPrice + count * price;
        Basket.addTotalPriceAllBaskets(this.totalPrice);//сумма всех товаров во всех карзинах
        items = items + "\n" + name + " - " +
                count + " шт. - " + price + "р. ";
        return true;
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {//содержит ли строка слово
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
