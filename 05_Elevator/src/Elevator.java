public class Elevator {
    private int currentFloor;
    private int maxFloor;
    private int minFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
    }

    /*Метод возврощающий текущий этаж*/
    public int getCurrentFloor() {
        return currentFloor;
    }

    /*Метод перемещающий лифт на один этаж вниз*/
    public void moveDown() {
        currentFloor--;
    }

    /*Метод перемещающий лифт на один этаж вверх*/
    public void moveUp() {
        currentFloor++;
    }

    /*Метод перемещающий на заданный в параметре этаж*/
    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Параметры заданы неверно.");
            return;
        }
        if (floor > currentFloor) {
            while (currentFloor != floor) {
                moveUp();
                System.out.println("Текущий этаж " + getCurrentFloor());
            }
        } else if (floor < currentFloor) {
            while (currentFloor != floor) {
                moveDown();
                System.out.println("Текущий этаж " + getCurrentFloor());
            }
        } else {
            System.out.println("Текущий этаж " + getCurrentFloor());
        }

    }

}
