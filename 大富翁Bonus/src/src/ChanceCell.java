import java.util.concurrent.ThreadLocalRandom;
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
 * Got one of the following chances randomly:
 * 1. Roll again
 * 2. +1000
 * 3. -1000
 * 4. Move to Jail directly without getting the 2000
 * 5. Get "Get Out Of Jail Free Card" *1
 * 6. Get Black Money
 * 7. Math Quiz
 */
public class ChanceCell extends FunctionCell {
    private final int Two = +1000; //get 1000 money
    private final int Three = -1000; //lost 1000 money
    private final int CostOfGetOutofJailFree = +200;//get 200money after sell the "GetOutofJailFree"


    public ChanceCell(String function) {
        super(function);
    }

    @Override
    public void event(Player p, Cell[] cells) {
        Scanner scanner = new Scanner(System.in);
        int RandomChance = ThreadLocalRandom.current().nextInt(1, 8); //random in the range of 1-7(without8)
        if (RandomChance == 1) { //if random number is 1
            System.out.println(this.getName() + "\tResult: Roll again!!");
            p.roll();   //have a chance to roll again
            cells[p.getPosition()].event(p, cells);
        } else if (RandomChance == 2) {//if random number is 2
            System.out.println(this.getName() + "\tResult: Gain $1000!!");
            p.charge(Two); //player's money add +1000
        } else if (RandomChance == 3) {//if random number is 3
            System.out.println(this.getName() + "\tResult: Deduct $1000!!");
            p.charge(Three); //player's money add -1000
        } else if (RandomChance == 4) {//if random number is 4
            System.out.println(this.getName() + "\tResult: Go to Jail, now!!");
            p.putToJail();  //player jail immediately
        } else if (RandomChance == 5) {
            System.out.println(this.getName() + "\tResult: You get \"GetOutofJailFree\"*1!!");
            outer:
            while (true) {
                System.out.println("Do you want to sell it and get $200? (y/n)");
                String input = scanner.next();
                if (input.equals("y")) {
                    p.charge(CostOfGetOutofJailFree); //player's money add +200
                    System.out.println("OK, You has been selled \"GetOutOfJailFree\"*1 and get $200");
                    break outer;
                } else if (input.equals("n")) {
                    p.setGetOutofJailFree_Card(1); // player add 1 GetOutofJailFree Card
                    System.out.println("OK, You keep the \"GetOutOfJailFree\"*1");
                    break outer;
                } else {
                    System.out.println("Please type the correct input.");
                }
            }
        } else if (RandomChance == 6) {
            int RandomBlackMoney = ThreadLocalRandom.current().nextInt(1, 501); //random black money in the range of 1-500
            p.charge(RandomBlackMoney);
            p.addBlackMoney(RandomBlackMoney);
            System.out.println("Oh you get "+RandomBlackMoney +" of black money, the only way you can spend black money is when you pay someone else’s rent");
            System.out.println("** Note: If the police find that you have black money, you need to pay a fine of 8,000 and confiscate all of your black money **");
        }else if(RandomChance == 7){
            String operatorSwitch = null;
            int Ans = 0;
            int input =0;
            String msg = "? <= Please calculate this math question, if it is correct, you will get 200 yuan, else 2,000 will be deducted";
            int a = ThreadLocalRandom.current().nextInt(1, 10000);
            int b = ThreadLocalRandom.current().nextInt(1, 10000);
            int operator = ThreadLocalRandom.current().nextInt(4);
            switch (operator){
                case 0: operatorSwitch= "+";
                    Ans = a+b;
                    System.out.println(a+operatorSwitch+b+msg);
                    break;
                case 1: operatorSwitch= "-";
                    Ans = a-b;
                    System.out.println(a+operatorSwitch+b+msg);
                    break;
                case 2: operatorSwitch= "*";
                    Ans = a*b;
                    System.out.println(a+operatorSwitch+b+msg);
                    break;
                case 3: operatorSwitch= "/";
                    Ans = a/b;
                    System.out.println(a+operatorSwitch+b+msg);
                    break;
            }
            try {
                input = scanner.nextInt();
            }catch(Exception e){
                input =-999999999;
            }
            if(input == Ans){
                System.out.println("Correct! You get 200");
                p.charge(200); //player's money add +200
            }else{
                System.out.println("Wrong! You will be deducted 2000");
                p.charge(-2000); //player's money add -2000
            }
        }
    }
}