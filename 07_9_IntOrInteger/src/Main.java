public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());
        short n = 1;
        for (int i = 0; i < 65636; i++) {
            char ch = (char) i;

            if (ch == 'а' || ch == 'б' || ch == 'в' || ch == 'г' || ch == 'д' || ch == 'е' ||
                    ch == 'ё' || ch == 'ж' || ch == 'з' || ch == 'и' || ch == 'й' || ch == 'л' ||
                    ch == 'к' || ch == 'м' || ch == 'н' || ch == 'о' || ch == 'п' || ch == 'р' ||
                    ch == 'т' || ch == 'с' || ch == 'у' || ch == 'ф' || ch == 'ц' || ch == 'ч' ||
                    ch == 'щ' || ch == 'ш' || ch == 'ь' || ch == 'ъ' || ch == 'э' || ch == 'ю' ||
                    ch == 'я' || ch == 'А' || ch == 'Б' || ch == 'В' || ch == 'Г' || ch == 'Д' ||
                    ch == 'Е' || ch == 'Ё' || ch == 'Ж' || ch == 'З' || ch == 'И' || ch == 'Л' ||
                    ch == 'К' || ch == 'М' || ch == 'Н' || ch == 'О' || ch == 'П' || ch == 'Р' ||
                    ch == 'Т' || ch == 'С' || ch == 'У' || ch == 'Ф' || ch == 'Ц' || ch == 'Ч' ||
                    ch == 'Щ' || ch == 'Ш' || ch == 'Ь' || ch == 'Ы' || ch == 'Ъ' || ch == 'Э' ||
                    ch == 'Ю' || ch == 'Я'
            ) {
                System.out.print(ch + ", ");
                if(n % 10 == 0)
                    System.out.println();
                n++;
            }
        }

    }
}
