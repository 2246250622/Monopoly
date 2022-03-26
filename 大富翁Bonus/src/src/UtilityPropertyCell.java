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
* The rent of UtilityPropertyCell is computed by the number of steps that the player rolled multiply by x
* x = 100, if both property are owned by the same player
* x = 10, if the owner of this property cell owns only one property cell.
*/
public class UtilityPropertyCell extends PropertyCell {
    public static final int UTILITY_PROPERTY_COST = 500;
    public static final UtilityPropertyCell LIBRARY_UTILITY_CELL = new UtilityPropertyCell("Library", UTILITY_PROPERTY_COST);
    public static final UtilityPropertyCell CANTEEN_UTILITY_CELL = new UtilityPropertyCell("Canteen", UTILITY_PROPERTY_COST);

    public UtilityPropertyCell(String name, int baseCost) {
        super(name, baseCost);
    }

    @Override
    public int getRent(Player p) {
        int count = 0;
        if (p == this.owner || this.owner == null) { //if this cell is own or no own, then no need pay rent
            return 0;
        }
            if (LIBRARY_UTILITY_CELL.getOwner()==(this.getOwner())) {
                count++;
            }
            if (CANTEEN_UTILITY_CELL.getOwner()==(this.getOwner())) {
            count++;
            }
            switch (count){
                case 1:
                    return p.getLastMove() * 100; //If the owner has two utilities, the charge is the value of the dice the player rolled multiplied by 100
                case 2:
                    return p.getLastMove() * 10; //If the owner has one utility, the charge is computed by the value of the dice the player rolled multiplied by 10
                default:
                    return 0;
            }
    }
}
