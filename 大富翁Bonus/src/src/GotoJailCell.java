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
class GotoJailCell extends FunctionCell {
    public GotoJailCell() {
        super("Go to Jail"); // this cell called "Go to Jail"
    }

    @Override
    public void event(Player p, Cell[] cells) {
        if(p.getBlackmoney()>0){
            p.addBlackMoney(p.getBlackmoney()*-1);
            p.charge((p.getBlackmoney()*-1)-8000);
            System.out.println("All of your black money needs to be confiscated and an extra 8,000 fine must be paid");
        }
        if (p.getGetOutofJailFree_Card() <= 0) {
            System.out.println(p.getName() + " go to Jail!");
            p.putToJail();
        }else{
            p.setGetOutofJailFree_Card(-1);
            System.out.println(p.getName() + " Used 1 \"GetOutofJailFree\" to skip the Jail!"+" You now have "+p.getGetOutofJailFree_Card()+" \"GetOutofJailFree\" left." );
        }
    }
}
