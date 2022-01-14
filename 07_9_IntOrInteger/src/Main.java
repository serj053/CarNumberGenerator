public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());
        byte n = 1;
        for (int i = 0; i < 65636; i++) {
            char ch = (char) i;

            if (ch >= 'А' && ch <= 'я' || ch == 'ё' || ch == 'Ё') {
                System.out.print(ch + ", ");
                if (n % 10 == 0)
                    System.out.println();
                n++;
            }
            if(n > 66)
                break;
        }

    }
}
