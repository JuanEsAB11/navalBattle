import java.util.*;

public class GameHelper {

    private static final int MAX_ATTEMPTS = 200;

    private final Random random = new Random();
    private int startupCount = 0;
    private GameBoard gameBoard = new GameBoard();

    private final int[] grid = new int [gameBoard.getGridSize()];

    public String getUserInput(String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt + ": ");
        String input = sc.nextLine().toUpperCase();

        while (!gameBoard.coordInBoard(input) || input.equals("")){
            System.out.println("No pudo procesarse el disparo en el tablero. Intenta de nuevo.");
            System.out.println(prompt + ": ");
            input = sc.nextLine().toUpperCase();
        }

        return input;
    }

    public ArrayList<String> placeStartup(int startupSize) {
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = gameBoard.getIncrement(startupCount);

        while(!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(1, grid.length+1);

            for (int i = 0; i < startupCoords.length; i++){
                startupCoords[i] = location;
                location += increment;
            }

            if (gameBoard.startupFits(startupCoords, increment)){
                success = coordsAvailable(startupCoords);
            }
        }
        savePositionToGrid(startupCoords);
        ArrayList<String> alphaCells = gameBoard.convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    private boolean coordsAvailable(int[] startupCoords){
        for (int coord : startupCoords) {
            if (grid[coord] != 0) {
                return false;
            }
        }
        return true;
    }

    private void savePositionToGrid(int [] startupCoords) {
        for (int index : startupCoords) {
            grid[index] = 1;   // mark grid position as used
        }
    }

    public GameBoard getGameBoard(){
        return gameBoard;
    }

}
