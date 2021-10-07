package Classes;

import Interface.Carriage;

public class PassCarriage implements Carriage {
public int passenger;

    public void setCountPassenger(int passenger) {
        this.passenger=passenger;
    }

    public int getCountPassenger() {
        return passenger;
    }

    public int getCountCapacity() {
        return 0;
    }
}