import java.util.ArrayList;

public class StartupBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<>();
    private int numOfGuesses = 0;
    private ArrayList<String> listOfHits = new ArrayList<>();

    public static void main(String[] args){
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }

    private void setUpGame() {
        Startup s1 = new Startup();
        s1.setName("poniez");

        Startup s2 = new Startup();
        s2.setName("hacqi");

        Startup s3 = new Startup();
        s3.setName("cabista");

        startups.add(s1);
        startups.add(s2);
        startups.add(s3);

        System.out.println("Tu objetivo es hundir tres Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Trata de hundirlas en el menor número de intentos.\n");

        for (Startup startup: startups){
            ArrayList<String> newLocation = helper.placeStartup(3);
            startup.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        helper.getGameBoard().display();
        System.out.println();

        while (!startups.isEmpty()){
            String userGuess = helper.getUserInput("Trata de adivinar");
            if (listOfHits.contains(userGuess)){
                System.out.println(userGuess + " ya fue impactada.\n");
            } else {
                String check = checkUserGuess(userGuess);
                setRowStates(userGuess, check);
                helper.getGameBoard().updateDisplay(helper.getGameBoard().convertAlphaFormatInCoords(userGuess)[0]);
                System.out.println();
                System.out.println(check);

                if (check.equals("impacto") || check.equals("baja")){
                    listOfHits.add(userGuess);
                }
            }

            System.out.println("-".repeat(25));
            System.out.println("Número de intentos: " + numOfGuesses);
            System.out.println("Lista de impactos: " + listOfHits);
            System.out.println("-".repeat(25));
            System.out.println();
        }
        finishGame();
    }

    private String checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "fallo";

        for (Startup startupToTest: startups){
            result = startupToTest.checkYourself(userGuess);

            if (result.equals("impacto")){
                break;
            }

            if (result.equals("baja")){
                startups.remove(startupToTest);
                break;
            }
        }
        return result;
    }

    private void finishGame(){
        System.out.println("\nJuego terminado.\n");
        if (numOfGuesses <= 18){
            System.out.println("¡Felicitaciones!.");
            System.out.println("Solo te tomo " + numOfGuesses + " intentos.");
        } else {
            System.out.println("Te tomó bastante tiempo. " + numOfGuesses + " intentos.");
            System.out.println("Que lástima...");
        }
    }

    private void setRowStates(String guess, String check){
        int[] coords = helper.getGameBoard().convertAlphaFormatInCoords(guess); //Fila: coords[0], columna: coords[1]
        int[] oldStates = helper.getGameBoard().getFormatStates().get(coords[0]);
        int[] newStates = new int[oldStates.length]; //Se guardarán los estados actualizados de las celdas para una fila en este arreglo
        for (int i=0; i < newStates.length; i++){
            if (i == (coords[1]-1)){
                if (check.equals("fallo")){
                    newStates[i] = 1;
                } else {
                    newStates[i] = 2;
                }
            } else {
                newStates[i] = oldStates[i];
            }
        }
        helper.getGameBoard().getFormatStates().remove(coords[0]);
        helper.getGameBoard().getFormatStates().add(coords[0], newStates);
    }

}
