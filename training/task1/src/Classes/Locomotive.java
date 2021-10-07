package Classes;

import Interface.Carriage;

public class Locomotive implements Carriage {
    private int capacity;
    public void setCountCapacity(int capacity) {
        this.capacity=capacity;
    }

    public int getCountPassenger() {
        return 0;
    }

    public int getCountCapacity() {
        return capacity;
    }

}
