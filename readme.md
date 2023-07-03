![](hkbu.png)
# COMP2026 Programming Assignment 3

## 2021-22 Semester 1

* Designed by: [Dr. Kevin Wang](mailto:kevinw@comp.hkbu.edu.hk)
* Q & A: [Piazza](https://piazza.com/class/kodsr5zs2km5ls)
* Due: 
  * UAT Due: 24/11/2021 (Wed 12:00 noon)
  * Programming Due: 4/12/2021 (Sat 23:59pm) 
* Download the demo program: [here](demo.jar)
* Download everything from the assignment: [here](https://github.com/khwang0/COMP2026-2122PA3/archive/refs/heads/master.zip)

> To run the demo program type the following in your terminal:
> ```java
> java -jar demo.jar
> ```
> It may happen that you can't launch the GUI version due to configuration problem.
> You can watch a video here if you are very keen to watch it: [Video](https://youtu.be/J7wpJQvSPHM)




## Introduction

You are given the opportunity to get familiar with the concept of class, object instantiation, inheritance, and polymorphism. In this assignment we are building a [monopoly game](https://en.wikipedia.org/wiki/Monopoly_(game)). I am pretty sure you have heard about this game and its variants. This is a broad game where players take turns to roll a dice (value 1 to 6) to move on a circular game broad. Players will buy properties or build houses on those properties. If other players move on a player's property, they will need to pay the owner a fee. Some cells like **Jail**, **Car Park**, **Home** will trigger some special events when a player steps on it. The game ends when one of the players has no more money.

![monopoly logo](Monopoly_pack_logo.png)

## Detailed Rule Set

The game board has 22 cells arranged in a circular one-way circular path (can't go backward). These cells include 16 properties cells, one **Home**, one **Jail**, one **Car Park**, one **Goto Jail**, one **Chance**, one **Community Chest**. Among these 16 properties cells, four of them are train stations, two of them are utilities, plus the rest of them (10) are ordinary property cells.

Each player has $10000 to begin with. All players start at the position `0`, i.e. **Home**. Worth noting that they would not receive an addition $2000 when they depart from home. $2000 is given to them each time they pass through or arrive Home.

### Property Cells

All property cells are initially not owned by any player. They can be bought by a player if they are not owned. Once a property cell is owned, it cannot be bought anymore. Each property cell has a `baseCost` which tells how much a player needs to pay when he/she buys this property.

When a player moves to a property owned by another player (owner), this player will need to pay a rent to the owner by the formula prescribed later in this section. Their `money` should be changed accordingly.

When a player moves to a property owned by himself/herself, the player will be prompted to build a house **if this is an ordinary property**. There is no upper limit on how many houses a player can build (i.e., if he returns to this property again later, he can build another house). He cannot build a house on a utility or a train station. Nothing will happen. Unlike the traditional rule in monopoly, we don't have the *property set* concept which affects the rents and house building rules. The equation related to the property card is as follows.


| Item | Money |
|---|---|
| Cost for buying a property | `baseCost` |
| Cost for building a house |  `baseCost` / 5 |
| Rent for an ordinary property | `baseCost * (1 + house * 0.5)` where `house` denotes the number of houses built on that property |
| Rent for train stations | If the owner has one train station, $500. If the owner has two train stations, $1000. If the owner has three stations, $2000. If the owner has four stations $4000. |
| Rent for utility | If the owner has one utility, the charge is computed by the value of the dice the player rolled multiplied by 10. If the owner has two utilities, the charge is the value of the dice the player rolled multiplied by 100. For example, Alice has owned Library and Canteen. Bob rolls 5 and reach Library. Bob will need to pay Alice 500.  |


## Park Cell

When a player moves to a Car Park, this player will not be able to charge any money from other players until he/she leaves the park. Other players who moves to a property owned by that player does not need to pay the rent.

## Home

When a player passes the Home cell, he/she will receive $2000 from nowhere. If a player move onto the Home Cell, nothing happen.

## Jail

A player moves to a Jail cell (visiting) does nothing.

## Goto Jail

A player moves to a Goto Jail cell will be directly move to the Jail Cell which is at `Gameboard.JAIL_POSITION` position of the cell. The player cannot get $2000 because of passing by the Home cell. The player then will need to suspend for a turn (can't move) during the imprisonment. *Note: a person in jail can still receive rent.*

## Chance and Community Chest

A player moves to a chance or a community chest will incur one of the following outcomes randomly:
* Roll again
* +1000
* -1000
* Being put to Jail (same as Goto Jail)

## Other rules

There isn't any trade system or mortgage rules in this assignment. Each time a player will roll one dice to move. Thus, each time a player will move for 1 step to 6 steps if he/she is not during imprisonment.

---

# Classes

You should started with the given code. There are some descriptions on each of the classes and methods that you are required to complete. Noted that you are also required to create some exact methods. The classes `Gameboard`, `GUIGameboard`, and `Cell` are finished. You should not modify these files when you submit the assignment. Even if you does, we will overwrite these files and compile your code.  

| Class Name | Function | Methods to Implement |
|---|---|---|
| `Gameboard` | The mastermind of the program. It has cells and players |  All finished. Don't modify this file. |
| `GUIGameboard` | A graphic version alternative of the program. You are not even supposed to understand the code inside this file. | All finished. Don't modify this file. |
| `Cell` | The generic type of cell. This class is an abstract class. | All finished. Don't modify this file. |
| `Player` | A class to model player. A player should have stored his/her name, amount of money he/she has and other possible attributes. | `void roll()` - A player roll a dice and change its position. |
| | |`Player(String name)` - The player constructor that initialize a player. |
| | | `boolean isInPark()` - Return true if the player is in Park |
| | | `void setInPark(boolean inPark)` - Set the player is in Park or not |
| | |`boolean isInJail()` - Return true if the player is in Jail |
| | |`void putToJail()` - Put the player into Jail directly without passing Home (i.e., no 2000) |
| | | `int getMoney()` - Get the amount of money that the player has |
| | | `int getLastMove()` -  Get the value of dice that the player has just rolled. |
| || `String getName()` - Get the name of the player |
| || `int getPosition()` - Get the position of where the player is |
| ||`void charge(int c)` - Charge c dollars from a player. |
| `PropertyCell` | Representing ordinary property cell  | `Player getOwner()` - Return the owner of the cell. Return `null` if it is not owned by any player. |
| | |   `String toString()` - Return a string in the following format: 1) `<property name> owned by <owner's name> : <baseCost>` or 2) `<property name> owned by <owner's name> : <baseCost> House : <house>` Return 1) if the property have no house. Return 2) if the property has more than one house. If the property is not owned, write `-` for the owner's name. |
| | | `void event(Player p, Cell[] cells)` - Trigger the event when a player moves onto this cell. i.e., it should prompt a player to buy an unowned property, or ask the owner to build a house, or make the player to pay rent to the owner (unless the owner is in the park).
| | | `int getRent(Player p)` - Return the rent that this player needs to pay. |
| `TrainStationPropertyCell` | Representing the Train Station cell which can't build any house and the rent is calculated differently. | *Unknown* - not sure what method should this class include</br>. Please pay extra attention to the variables `TrainStationPropertyCell[] TRAIN_STATION_ARRAY` that is written for you. This array holds four train station objects which are used in `Gameboard`.
| `UtilityPropertyCell` | Representing the utility cell which can't build cany house and the rent is calculated differently. | *Unknown* - not sure what method should this class include</br>. Please pay extra attention to the variables `CANTEEN_UTILITY_CELL` and `LIBRARY_UTILITY_CELL`. These static variables holds the utility cells used in `Gameboard`.
| `ChanceCell` | Representing the cells Chance and Community Chest. | *Unknown*
| `GotoJailCell` | Representing the cell Goto Jail. | *Unknown*.
| `ParkCell` | Representing the cell Cark Park. | *Unknown*.
| `FunctionCell` | Representing the cells Home and Jail. There isn't any effect when a player moves to this cell via **rolling dice**. | *Unknown*

---

## Demo

A sample program can be found here [demo](demo.jar). The sample program provides you an understanding of the program. You do not need to follow the exact wording and output format of the program except for the method `String toString()` of the class `Property` (see the specification above). 

The demo include a GUI version and a command line version (which decided by typing Y or N at the start of the programme). Whenever there is a discrepancy, we follow the command line version. 

**It is totally OK if your program does not work with the GUI version. We will grade your code based on the command line only.**



## Programming Style and Documentation 

Good programming style (indentation, comments) are always essential.  Blank lines, spaces between operators/variables (wherever appropriate) and meaningful variable names are required. Your program should be properly indented.  Good choice of variable names and method names is also essential.  Your program must have proper internal documentation, as described below: 

### Header Block 
For your java file, there must be a header at the beginning of the file, with your name and your UID. 

### Inline Comments 
Wherever necessary and appropriate, you should add inline comments to explain the execution flow of your program. 

 

## Submission 
For submission, zip the src folder of your IntelliJ project, name the zip file as “XXXXXXXX_assign3.zip” (where XXXXXXXX is your Student ID number), and upload it to Moodle.  If you go for the bonus marks, you should also submit your short explanation inside this zip file. 

Please be reminded that both the **Late Penalty Rule** and the **Penalty for Plagiarism** are applied strictly to all submissions of this course (including this assignment).   

### Late Penalty Rule

```java
if (lateHour > 0) {
    if (lateHour < 24) 
        mark *= 0.8;
    else if (lateHour < 48)
        mark = mark >> 1;
        else if (lateHour < 72)
            mark = mark >> 2;
            else
                mark &= 0;
}
```
 
 ### Plagiarism

 Plagiarism is a serious offence and can be easily detected. Please don't share your code to your classmate even if they are threatening you with friendship. If they don't have the ability to work on something that can compile, they would not be able to change your code to a state that we can't detect the plagiarism. For the first commit of plagiarism, regardless you shared your code or copied code from others, you will receive 0 with an addition of 5 mark penalty. If you commit plagiarism twice, your case will be presented in the exam board and you receive a F directly.


 

## Marking Scheme 
This assignment is worth 8% of the course mark.  There are three elements in the marking scheme: 
* 5% - Understanding the Assignment Test (UAT)
* 25% - The program can be compiled.
* 65% - a working program that functions as specified 
* 5% - Programming style and documentation 
* up to 15% - Bonus - creative design of the assignment. You are qualified for this part only if you have received 85% mark or above. Bonus is given based on the **amount of effort** (which may not be proportion to the outcome) you have spent on it. 

 

## Interview 
Should the teaching team see fit, students may be requested to attend an interview to explain about their program.  Students failing to attend such interview or to demonstrate a good understanding of their own program may result in mark deduction. 

## Understanding the Assignment (UAT)

Answer the following question that on Moodle.
Due: 24/11/2021 (Wed 12:00 noon)
1. Please draw the hierarchy for the class Cell and its subclasses.
2. Which method can be used to read the remaining money of a player?
3. How much does it charge for the property `AAB` if it has a base cost $1000 and two houses built on it?