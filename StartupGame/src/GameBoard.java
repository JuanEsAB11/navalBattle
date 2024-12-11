import java.util.*;

public class GameBoard {
    private static final String ALPHABET = "ABCDEFG";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH;

    private static final GameFormat formatTool = new GameFormat();
    private ArrayList<String> format = new ArrayList<>();
    private ArrayList<int[]> formatStates = new ArrayList<>();


    public boolean startupFits(int[] startupCoords, int increment){
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
            //check end is on same row as start
        } else {
            return finalLocation < GRID_SIZE;
            //check end isn't off the bottom
        }
    }

    public ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<>();
        for (int index : startupCoords){
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }

    public int[] convertAlphaFormatInCoords(String alphaFormat){
        int row = ALPHABET.indexOf(alphaFormat.substring(0, 1));
        int column = Integer.parseInt(alphaFormat.substring(1, 2));
        int[] coords = {row, column};

        return coords;
    }

    public boolean coordInBoard(String coord){
        try {
            if (ALPHABET.contains(coord.substring(0, 1))){
                try {
                    int columnIndex = Integer.parseInt(coord.substring(1, coord.length()));
                    return (columnIndex <= GRID_LENGTH && columnIndex >= 1);

                } catch (ClassCastException e){
                    return false;
                }
            }
        } catch (Exception e){
            System.out.print("");
        }
        return false;
    }

    public String getAlphaCoordsFromIndex(int index){
        int row = calcRowFromIndex(index);
        int column = (index % GRID_LENGTH);
        if (column == 0){
            column = 7;
        }
        String letter = ALPHABET.substring(column-1, column);
        //VERIFICACIÓN
//        System.out.println(letter + row);
        return letter + row;
    }

    public int calcRowFromIndex(int index) {
        return Math.ceilDiv(index, GRID_LENGTH);
    }

    public int getIncrement(int startupCount) {
        if (startupCount % 2 == 0) {
            return HORIZONTAL_INCREMENT;
        } else {
            return VERTICAL_INCREMENT;
        }
    }

    public int getGridSize(){
        return GRID_SIZE;
    }


    public ArrayList<int[]> getFormatStates(){
        return formatStates;
    }

    public void display() {
        int[] states = new int[GRID_LENGTH];
        for (int i=0; i < states.length; i++){
            states[i] = 0;
        }

        for (int i = 0; i < GRID_LENGTH; i++) {
            String formatRow = (formatTool.cyan + ALPHABET.substring(i, i+1) + formatTool.reset) + formatTool.createStringFormat(GRID_LENGTH, 4);
            String[] formatValues = formatTool.setValuesForFormat(states);
            formatStates.add(states);
            format.add(String.format(formatRow, formatValues));
        }
        printGameBoard();
    }

    public void updateDisplay(int indexOfRow){
        format.remove(indexOfRow);
        int[] states = formatStates.get(indexOfRow);
        String formatRow = (formatTool.cyan + ALPHABET.substring(indexOfRow, indexOfRow+1) + formatTool.reset) + formatTool.createStringFormat(GRID_LENGTH, 4);
        String[] formatValues = formatTool.setValuesForFormat(states);
        format.add(indexOfRow, String.format(formatRow, formatValues));

        printGameBoard();
    }

    private void printGameBoard(){
        String format1 = formatTool.cyan + String.format(("%6s%5s%5s%5s%5s%5s%5s"), "1", "2", "3", "4", "5", "6", "7") + formatTool.reset;
        System.out.println(format1);
        for (String formatRow: format) {
            System.out.println(formatRow);
        }
    }

}
