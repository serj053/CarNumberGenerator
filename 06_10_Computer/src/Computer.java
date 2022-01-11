public class Computer {
    private final String vendor;
    private final String name;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public double computerWeight(Processor processor, Ram ram, Storage storage,
                                 Screen screen, Keyboard keyboard) {


        return processor.getWeight() + ram.getWeight() + storage.getWeight()
                + screen.getWeight() + keyboard.getWeight();
    }

    public void toString(Processor processor, Ram ram, Storage storage,
                         Screen screen, Keyboard keyboard) {
        System.out.print("Процессор:  ");
        getProcessor(processor);
        System.out.print("Оперативная память:  ");
        getRam(ram);
        System.out.println("Накопитель информации:  ");
        getStorage(storage);
        System.out.println("Экран:  ");
        getScreen(screen);
        System.out.println("Клавиатура:  ");
        getKeyboard(keyboard);
    }

    public void setProcessor(double frequency, int numberCores, String manufacturer, double weight) {
        new Processor(frequency, numberCores, manufacturer, weight);
    }

    public void getProcessor(Processor processor) {
        System.out.println(processor.getFrequency() + " Ghz, " +
                " ядер - " + processor.getNumberCores() + ", " +
                "  производитель " + processor.getManufacturer());
    }

    public void setRam(String type, double volume, double weight) {
        new Ram(type, volume, weight);
    }

    public void getRam(Ram ram) {
        System.out.println("Тип " + ram.getType() + ", " +
                " емкость " + ram.getValue() + " Gb, " +
                " вес " + ram.getWeight() + " кг.");
    }

    public void setStorage(String type, double memoryCapacity, double weight) {
        new Storage(type, memoryCapacity, weight);
    }

    public void getStorage(Storage storage) {
        System.out.println("Емкость " + storage.getMemoryCapacity() + " Gb, " +
                " тип " + storage.getType() + ", " +
                "  вес " + storage.getWeight() + " кг.");
    }

    public void setScreen(double diagonal, String type, double weight) {
        new Screen(diagonal, type, weight);
    }

    public void getScreen(Screen screen) {
        System.out.println(screen.getDiagonal() + " Inc, " +
                " тип " + screen.getType() + ", " +
                " вес " + screen.getWeight() + " кг.");
    }

    public void setKeyboard(String type, boolean isBacklight, double weight) {
        new Keyboard(type, isBacklight, weight);
    }

    public void getKeyboard(Keyboard keyboard) {
        System.out.println(((keyboard.getBacklight()) ? "с подсветкой," : "без подсветки,") + "" +
                "" + "  тип " + keyboard.getType() + ", " + "" +
                " вес " + keyboard.getWeight() + " кг.");
    }
}
