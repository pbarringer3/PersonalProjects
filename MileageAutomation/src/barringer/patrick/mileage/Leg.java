package barringer.patrick.mileage;

/**
 * Created by Patrick Barringer on 11/18/2015.
 */
public class Leg {
    public String  start;
    public String end;

    public Leg(String start, String end){
        this.start = start;
        this.end = end;
    }

    public double getDistance(MileageTable mileageTable){
        return mileageTable.getDistance(start, end);
    }
}
