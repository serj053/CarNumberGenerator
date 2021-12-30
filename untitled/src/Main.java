public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();

        printer.append("Сборник рецептов.");
        printer.append("Права человека в России", "Навальный", 550);
        printer.append("Экология", "Неизвестный");
        printer.print();
        printer.print();
        printer.append("Техническое обслуживание радирелейной станции", "Жук в муравейнике", 370);
        System.out.println("Листов в принтере для печати " + printer.getPendingPagesCount());
        System.out.println("Общее количество загруженных листов " + printer.getPrintingPages());
        printer.print();
        System.out.println("Листов в принтере " + printer.getPendingPagesCount());

    }

}
