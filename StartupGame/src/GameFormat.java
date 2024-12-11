public class GameFormat {

    //colors
    public String black = "\033[30m";
    public String red = "\033[31m";
    public String green = "\033[32m";
    public String yellow = "\033[33m";
    public String blue = "\033[34m";
    public String purple = "\033[35m";
    public String cyan = "\033[36m";
    public String white = "\033[37m";
    public String reset = "\u001B[0m";

    //Special chars
    public String hitPoint = green + "\u25CF" + reset;
    public String missHit = red + "\u2715" + reset;
    public String cell = white + "\u25A1" + reset;


    public String createStringFormat(int numOfStrings, int space){
        //Servirá para establecer el espaciado de elementos en un formato
        String formatString = (" ".repeat(space) + "%s").repeat(numOfStrings);
        return formatString;
    }

    public String[] setValuesForFormat(int[] states){
        /*Los números en el array que se recibe como argumento representan un estado de celda en el tablero:
        * 0 -> Celda sin modificar
        * 1 -> Fallo
        * 2 -> Acierto
        * */

        String[] formatValues = new String[states.length];
        for (int i=0; i < states.length; i++){
            if (states[i] == 0){
                formatValues[i] = cell;
            } else if (states[i] == 1){
                formatValues[i] = missHit;
            } else {
                formatValues[i] = hitPoint;
            }
        }

        return formatValues;
    }


}
