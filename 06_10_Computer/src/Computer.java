public class Computer {
    private final  Processor processor;
    private final Ram ram;
    private final Storage storage;
    private final Screen screen;
    private final Keyboard keyboard;
    private final String vendor;
    private final String name;

    public Computer(String vendor, String name, Processor processor, Ram ram,
                    Storage storage, Screen screen, Keyboard keyboard) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screen = screen;
        this.keyboard = keyboard;
        this.vendor = vendor;
        this.name = name;
    }

    public double computerWeight() {
        return processor.getWeight() + ram.getWeight() + storage.getWeight()
                + screen.getWeight() + keyboard.getWeight();
    }

    public void toStringComputer() {
        System.out.print("Процессор:  ");
        getProcessor();
        System.out.print("Оперативная память:  ");
        getRam();
        System.out.println("Накопитель информации:  ");
        getStorage();
        System.out.println("Экран:  ");
        getScreen();
        System.out.println("Клавиатура:  ");
        getKeyboard();
    }

    public void getProcessor() {
        System.out.println(processor.getFrequency() + " Ghz, " +
                " ядер - " + processor.getNumberCores() + ", " +
                "  производитель " + processor.getManufacturer());
    }

    public void getRam() {
        System.out.println("Тип " + ram.getType() + ", " +
                " емкость " + ram.getValue() + " Gb, " +
                " вес " + ram.getWeight() + " кг.");
    }

    public void getStorage() {
        System.out.println("Емкость " + storage.getMemoryCapacity() + " Gb, " +
                " тип " + storage.getType() + ", " +
                "  вес " + storage.getWeight() + " кг.");
    }

    public void getScreen() {
        System.out.println(screen.getDiagonal() + " Inc, " +
                " тип " + screen.getType() + ", " +
                " вес " + screen.getWeight() + " кг.");
    }



    public void getKeyboard() {
        System.out.println(((keyboard.getBacklight()) ? "с подсветкой," : "без подсветки,") + "" +
                "" + "  тип " + keyboard.getType() + ", " + "" +
                " вес " + keyboard.getWeight() + " кг.");
    }
}
