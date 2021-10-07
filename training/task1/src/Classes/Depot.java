package Classes;

import Interface.Carriage;
import java.util.ArrayList;
import java.util.List;

public class Depot implements Carriage {

private int countPassCarriage;
private int countCapacityCarriage;
private int countMainCarriage;

public List<Carriage> list = new ArrayList<Carriage>();

    public void setCountPassCarriage(int countPassCarriage) {
        this.countPassCarriage=countPassCarriage;
    }
    public void setCountCapacityCarriage(int countCapacityCarriage) {
        this.countCapacityCarriage=countCapacityCarriage;
    }
    public void setCountMainCarriage(int countMainCarriage) {
        this.countMainCarriage = countMainCarriage;
    }

    public int getCountPassenger() {
        return 0;
    }

    public int getCountCapacity() {
        return 0;
    }
}
