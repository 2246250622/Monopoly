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
public class PropertyCell extends Cell {
    protected int baseCost;
    protected Player owner = null;
    int house = 0;

    /**
     * Return the owner of the property
     * @return owner
     */
    public Player getOwner() {
        return this.owner;
     }
     /**
      * Constructor
      */
    public PropertyCell(String name, int baseCost) {
        super(name);
        this.baseCost=baseCost;
    }
    /**
     * Return the cost of the property cell
     * @return the base cost of the property cell.
     */
    public int getCost() {
        return this.baseCost;
    }
    /**
     * Return number of houses built on this property cell.
     * @return the number of houses.
     */
    public int getHouse() {
        return this.house;
    }

    /**
     * Return a specific format of string. See the assignment webpage
     * for more details
     * @return the specific string of the property cell/
     */
    @Override
    public String toString() {
        if (this.owner == null) {
            return this.getName() + " owned by - : " + this.getCost();
        }
        if (this.house == 0) {
            return this.getName() + " owned by " + this.owner.getName() + " : " + this.getCost();
        }
        return this.getName() + " owned by " + this.owner.getName() + " : " + this.getCost() + " House: " + this.house;
    }


    /**
     * Return the rent charged against this player. The formula for an ordinary cell is
     * baseCost * (1 + house * 0.5) rounding the nearest integer.
     *
     * @param p - The player who is charged. p is irrelevant in this case.
     * @return The rent.
     */
    public int getRent(Player p) {
        if (p == this.owner || this.owner == null) {
            return 0;
        }
        return (int)(this.baseCost * (1.0 + this.house * 0.5));
    }

    public void UpgradeHouse(Player owner) {
        Scanner sc = new Scanner(System.in);
        int BuildingHouseCost = this.baseCost / 5; //Cost for building a house is (baseCost / 5)
        System.out.println("Do you want to build a house for this land for "+BuildingHouseCost+"? (yes[y]/no[n])");
        outer:
        do {
            String input = sc.next();
            if(input.equals("y")||input.equals("Y")||input.equals("yes")||input.equals("Yes")||input.equals("YES")){
                System.out.println("Your choice is Yes!");
                if(BuildingHouseCost >= owner.getMoney()) { //if the building house cost is large than own money, then cant buy
                    System.out.println("Sorry you don't have enough money");
                    break outer;
                }else{
                    this.owner.charge(BuildingHouseCost*-1);
                    System.out.println("You have bought this land!");
                    this.house = this.getHouse()+1; //count of own house +1
                    break outer;
                }
            }else if (input.equals("n")||input.equals("N")||input.equals("no")||input.equals("No")||input.equals("NO")){
                System.out.println("Your choice is No!");
                break outer;
            };

        }while (true);
    }

    public void BuyHouse(Player owner){
        Scanner sc = new Scanner(System.in);

        outer:
        do {
            System.out.println("Do you want to buy this for "+(this.baseCost)+"? (yes[y]/no[n])");
            String input = sc.next();
            if(input.equals("y")||input.equals("Y")||input.equals("yes")||input.equals("Yes")||input.equals("YES")){
                    System.out.println("Your choice is Yes!");
                if(this.baseCost>=owner.getMoney()) { //if the base cost is large than own money, then cant buy
                    System.out.println("Sorry you don't have enough money");
                    break outer;
                }else{
                    owner.charge(this.baseCost*-1);
                    this.owner=owner;
                    System.out.println("You have bought this land!");
                    break outer;
                }

            }else if (input.equals("n")||input.equals("N")||input.equals("no")||input.equals("No")||input.equals("NO")){
                System.out.println("Your choice is No!");
                break outer;
            };
        }while (true);
    }

    @Override
    public void event(Player p, Cell[] cells) {
         if(getOwner() == p) {
             this.UpgradeHouse(p);
         }else if(getOwner() == null){
            this.BuyHouse(p);
        }else if(p.isInPark() == true){
                System.out.println(p.getName() + " is in the Park. Free parking.");
            }else{
            p.charge(this.getRent(p)*-1); //player pay the rent
             p.addBlackMoney(this.getRent(p)*-1); //player pay the rent with black money
             if(p.getBlackmoney()<0){
                 p.setBlackmoney(0);
             }
            this.getOwner().charge(this.getRent(p));    //owner get the rent
            System.out.println(p.getName()+" have paid "+this.getOwner().getName()+" $"+this.getRent(p));
        }
    }
}


