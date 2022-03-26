import java.util.concurrent.ThreadLocalRandom;

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
public class Player {
    private final String name;
    private int money;
    private int blackmoney;
    private int  GetOutofJailFree_Card;
    private int position;
    private int lastMove;
    private boolean inPark;
    private boolean inJail;

    /**
     * Constructor
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.money = 10000;
        this.blackmoney = 0;
        this.GetOutofJailFree_Card = 0;
        this.position = 0;
        this.inPark = false;
        this.inJail = false;
    }

    /**
     * A player roll a dice and change its position
     */
    public void roll() {
        int theRandomNum;
        System.out.println("\n"); //print a new line for the next player, make the menu easy to look
        if(this.isInJail()){ //check if is jail
            this.inJail = false; //turn false, then next round can move
            System.out.println(this.getName() + ", sorry you are in Jail."+" " +"Skip one round."); //print skip message
        } else {
            System.out.println(this.getName() + ", this is your turn. We roll for you"); //your turn message
              theRandomNum = ThreadLocalRandom.current().nextInt(1, 7);  //random dice 1-6， without 7
            System.out.println("It is " + theRandomNum);
            if (this.position + theRandomNum >= Gameboard.CELL_SIZE) { //$2000 is given to them each time they pass through or arrive Home.
                this.money += 2000; //get 2000money
                this.position = this.position + theRandomNum - (Gameboard.HOME_POSITON + Gameboard.CELL_SIZE);  //if player go through the start point player ,get 2000 money and the position need reduce the gameboard size to reset the location
                if(this.getMoney()>=20000){ //if own money is greater than 20000
                    this.money -= 6000; //pass the start point need to pay a tax of 6000 (still can get 2000, so reduce 4000 in total)
                }
            }
            else {
                this.position += theRandomNum; // if no go through the start point, just keep going, and cant get 2000
            }
            this.lastMove = theRandomNum; //record the random dice number to lastMove
            if (this.isInPark()) {
                this.inPark = false;
            }
        }
    }
    /**
     * Return true if the player is in Park 
     *
     * @return true if the player is in Park
     */
    public boolean isInPark() {
        if(this.inPark){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Set the player in a park. This will only be called when a player
     * move to the cell Car Park. 
     *
     * @param inPark true if the player is set in the park
     */
    public void setInPark(boolean inPark) {
        this.inPark = inPark;
     }
    /**
     * Return true if the player is in Jail. It should return false
     * if the player visits Jail (i.e., rolls a dice and moves to the cell Jail)
     *
     * @return true if the player is in Jail
     */
    public boolean isInJail() {
        if(this.inJail == true){
            return true;
        }else
        return false;
     }
    /**
     * Put the player into Jail directly without passing Home (i.e., no 2000)
     */
    public void putToJail() {
        if(this.inJail==false){    //if not in jail, take the player to jail
            this.inJail = true;   //change true when he is inJail
            this.position = Gameboard.JAIL_POSITION; //move to JAIL_POSITION now position!
        }

    }

    /**
     * Get the value of dice that the player has just rolled.
     *
     * @return the value of dice
     */
    public int getLastMove() {
        return this.lastMove;
    }
    /**
     * Get the amount of money that the player has
     *
     * @return the amount of money the player has
     */
    public int getMoney() {
        return this.money;
     }
     /**
      * Return the name of the player
      *
      * @return the name of the player
      */
    public String getName() {
        return this.name;
     }
     /**
      * return the current position of the player
      * 
      * @return the position of the player
      */
    public int getPosition() {
        return this.position;
     }
     /**
      * charge certain amount of dollar from the player.
      * 
      * @param money The amount being charged
      */
    public void charge(int money) { //this method to the add a passive or negative money
        this.money += money;
     }

     public int getGetOutofJailFree_Card(){
        return this.GetOutofJailFree_Card;
     }

    public void setGetOutofJailFree_Card(int getOutofJailFree_Card) {
        this.GetOutofJailFree_Card += getOutofJailFree_Card;
    }

    public int getBlackmoney(){
        return this.blackmoney;
    }

    public void setBlackmoney(int blackmoney) {
        this.blackmoney = blackmoney;
    }

    public void addBlackMoney(int blackmoney){
        this.blackmoney += blackmoney;
    }
}
