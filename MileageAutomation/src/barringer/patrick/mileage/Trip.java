package barringer.patrick.mileage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Barringer on 11/18/2015.
 */
public class Trip {
    private static final String HOME = "H";
    private int row;
    private List<Leg> legs;

    public Trip(String tripString, int rowNumber){
        this.row = rowNumber;
        createLegs(tripString);
    }

    private void createLegs(String tripString){
        legs = new ArrayList<Leg>();
        String[] destinationsInOrder = tripString.split(">");
        destinationsInOrder = trimSpaces(destinationsInOrder);
        for(int i=0; i<destinationsInOrder.length-1; i++){
            legs.add( new Leg(destinationsInOrder[i], destinationsInOrder[i+1]) );
        }
    }

    private String[] trimSpaces(String[] strings){
        String[] results = new String[strings.length];
        for(int i=0; i<strings.length; i++){
            results[i] = strings[i].trim();
        }
        return results;
    }

    public double getTotalDistance(MileageTable mileageTable){
        double total = 0;
        for(Leg leg : legs){
            total += leg.getDistance(mileageTable);
        }
        if(legs.size()==0){
            return -1;
        }
        return total;
    }

    public double getDistanceNotIncludingHome(MileageTable mileageTable){
        double total = 0;
        for(Leg leg : legs){
            if(!leg.start.equals(HOME) && !leg.end.equals(HOME)) {
                total += leg.getDistance(mileageTable);
            }
        }
        return total;
    }

    public double getDeductionForHomeMileage(MileageTable mileageTable){
        double total = 0;
        for(Leg leg : legs){
            if(leg.start.equals(HOME) || leg.end.equals(HOME)) {
                total += leg.getDistance(mileageTable);
            }
        }
        return total;
    }

    private void printArray(String[] toPrint){
        for(String s : toPrint){
            System.out.println("\""+s+"\"");
        }
    }

    public int getRow(){
        return row;
    }
}
