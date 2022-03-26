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
public class ParkCell extends FunctionCell {
    public ParkCell() {
        super("Park");// this cell called "Park"
    }

    @Override
    public void event(Player p, Cell[] cells) {
        p.setInPark(true); //call p.setInPark(true) to set this.inPark = true
        System.out.println(p.getName() + " is in the park"); //print message
    }
}

