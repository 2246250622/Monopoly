import java.util.Scanner;
import java.util.*;
/***
 * Name: Cheuk Shu Ho
 * ID:21237387
 * Section: 1
 *
 * Disclaimer: I have not committed any form of plagiarism. I did not disclose any
 *             part of my code to my classmate. I did not upload my code to any
 *             website or public repository.
 *
 * Shall you have any problem in doing the assignment, please feel free to ask
 * questions on Piazza. However, NEVER post your code there.
 */
/**
 * The Gameboard class. This class is given to you already. The implementation of this class is completed.
 * You are not supposed and not allowed to change any part of this class.
 *
 * Gameboard is the mastermind (or controller) of the project. It has cells and players as its data members.
 *
 * To make the assignment be more interesting. We have provided a GUI version of the gameboard which work exactly
 * the same as this class. For testing purpose, we you recommend you work on the command-line version until
 * you program work perfectly fine.
 *
 * In case your program work perfectly fine in command line version but you can't make the GUI work, don't worry about
 * that! We will be grading your work in command line version only.
 */
public class Gameboard {

    /**
     * A constant variable that tells how many cells are there
     */
    public static int CELL_SIZE = 22;

    /**
     * The position where the Jail is located
     */
    public static int JAIL_POSITION = 8;

    /**
     * The position where the home is located
     */
    public static int HOME_POSITON = 0;

    /**
     * The array storing all cells.
     */
    private Cell[] cells = null;

    /**
     * The array storing all players
     */
    private Player[] players = {new Player("Kevin"), new Player("Sandy"), new Player("Emily")};;

    /**
     * Record whos' turn now
     */
    private int turn = 0;


    /**
     * A method that check if the game is over (i.e., any player has money less than 0)
     * @return true if game is over
     */
    private boolean gameOver() {
        for (int i = 0; i < players.length; i++) //check every player
            if (players[i].getMoney() < 0) //after the event if who's money smaller than 0
                return true; //return true and end game
        return false; //return false and continue to play
    }

    /**
     * A method that construct the gameboard.
     *
     * @return an array of cells
     */
    public static Cell[] initCell() { //create 22 cell object in game
        Cell[] cells = new Cell[22];
        cells[0] = new FunctionCell("Home");
        cells[1] = new PropertyCell("RRS",500);
        cells[2] = UtilityPropertyCell.LIBRARY_UTILITY_CELL;
        cells[3] = new PropertyCell("LMC", 600);
        cells[4] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[0];
        cells[5] = new PropertyCell("OEE", 600);
        cells[6] = new PropertyCell("OEW", 700);
        cells[7] = new PropertyCell("FSC", 800);
        cells[JAIL_POSITION] = new FunctionCell("Jail");
        cells[9] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[1];
        cells[10] = new ChanceCell("Chance");
        cells[11] = new ParkCell();
        cells[12] = new PropertyCell("AAB", 1000);
        cells[13] = UtilityPropertyCell.CANTEEN_UTILITY_CELL;
        cells[14] = new PropertyCell("DLB", 1000);
        cells[15] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[2];
        cells[16] = new PropertyCell("WLB", 1000);
        cells[17] = new PropertyCell("WHS", 1000);
        cells[18] = new PropertyCell("STB", 1000);
        cells[19] = new GotoJailCell();
        cells[20] = TrainStationPropertyCell.TRAIN_STATION_ARRAY[3];
        cells[21] = new ChanceCell("Comm Chest");
        return cells;
    }

    /**
     * The main logic of the program. It runs once only.
     */
    private void runOnce() {
        print();
        while (!gameOver()) { //just keep looping and play. Until gameOver() is true, then end game
            Player currentPlayer = players[turn];
            currentPlayer.roll();
            print();
            Cell currentCell = cells[currentPlayer.getPosition()];
            System.out.println("You have landed on: " + currentCell.getName()+"!");
            currentCell.event(currentPlayer, cells);
            turn = (turn + 1) % players.length; //next Player
        }
        System.out.println("Game over");
    }

    /**
     * The constructor of the class.
     */
    public Gameboard() {
        cells = initCell();
    }

    /**
     * Prints the status before each round.
     */
    private void print() {
        for (Player p : players)
            System.out.println(p.getName() + " : " + "(Money "+p.getMoney() +"|Black Money "+p.getBlackmoney()+")"+ " (GetOutOfJail Card "+p.getGetOutofJailFree_Card() + ")");
        for (int i = 0; i < cells.length; i++) {
            Cell c = cells[i];
            System.out.print(c.toString() + "\t\t");
            for (Player p : players) {
                if (p.getPosition() == i)
                    System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] argv) {
        System.out.println("Do you want to launch the GUI version? (y/n)");
        Scanner s = new Scanner(System.in);
        if (s.next().equals("n") || !GUIGameboard.kickoff())
            new Gameboard().runOnce();
    }
}
