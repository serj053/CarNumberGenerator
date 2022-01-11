public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor(3.7, 4, "Intel", 0.8);
        Ram ram = new Ram("DDR2", 4, 0.05);
        Storage storage = new Storage("SSD", 500, 0.1);
        Screen screen = new Screen(19, "IPS", 3);
        Keyboard keyboard = new Keyboard("HUNTER", false, 0.8);
        Computer computer = new Computer("Panasonic", "Dell", processor, ram,
                storage, screen, keyboard);

        /*Определение веса кмпьютера*/
        System.out.println("Вес компьютера - " +
        computer.computerWeight());

        /*Описание компьютера*/
        computer.toStringComputer();
    }
}

