import java.util.ArrayList;

public class Startup {
    private ArrayList<String> locationCells;
    private String name;


    public void setLocationCells(ArrayList<String> locs){
        this.locationCells = locs;
    }

    public void setName(String name){
        this.name = name;
    }

    public String checkYourself(String userInput){
        String result = "fallo";
        int index = locationCells.indexOf(userInput);

        if (index >= 0){
            locationCells.remove(index);

            if (locationCells.isEmpty()){
                result = "baja";
                System.out.println("Ouch! hundiste a " + name + "\n");
            } else {
                result = "impacto";
            }
        }

        return result;
    }
}
