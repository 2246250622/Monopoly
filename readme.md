![](hkbu.png)
# COMP2026 Programming Assignment 1 - Sudoku

## 2021-22 Semester 1

* Designed by: [Dr. Kevin Wang](mailto:kevinw@comp.hkbu.edu.hk)
* Q & A: [Piazza](https://piazza.com/class/kodsr5zs2km5ls)
* Assignment Website: [GitHub](https://github.com/khwang0/COMP2026-2122PA1)
* Due: 
  * UAT Due: 11:59am (noon), 11th October, 2021 (Monday)
  * Programming Due: 23:59pm (midnight), 23rd October, 2021 (Saturday) 
* Download the starter code: [Sudoku.java](Sudoku.java) and the puzzle [sudoku1.txt](sudoku1.txt)
* Download the demo program: [here](demo.jar)
* Download everything from the assignment: [here](https://github.com/khwang0/COMP2026-2122PA1/archive/refs/heads/master.zip)

> To run the demo program on Windows, download the puzzle file `sudoku1.txt` and `demo.jar` under the same directory type the following in your terminal:
> 
> **For Windows**
> ```sh
> > > chcp 65001
> > java -jar -Dconsole.encoding=UTF-8 -Dfile.encoding=UTF-8 demo.jar
> Please enter a filename:
> sudoku1.txt
> ```
> **For macOS**
> ```sh
> > java -jar demo.jar
> ```
> Note: your terminal may not be able to display some unicode characters properly. Please don't panic if it is the case.



# Learning outcome

Students are expected to have some practice on arrays/2D arrays/parameter passing/method construction and usage in this assignments. We expect most students would spend 8 hours or more to finish the assignment without any assistant. Make sure you start earlier as possible and ask us on Piazza if you have any difficulty!


# Introduction

You are going to complete the sudoku program! Open [Sudoku.java](Sudoku.java) and complete the methods stated in the skeleton code. Some of these methods are very straight forward while some of them isn't that easy. Make sure you can follow the instructions given at the top of each method.

A [sample program](demo.jar) is given to you. When there is something you are not sure, you can take a look at the sample program to decide what to do.

Some methods are labeled as completed or given. Please don't make any change on those methods. You are not supposed to modify them.

# Explanation of the game of Sudoku

You can skip this section if you have some experiences in playing Sudoku puzzles. It is a grid of 9x9 blocks with some given digits as shown in the figure. The puzzle has a unique solution such that all empty cells are filled with the digit 1 to 9 one each on each row, each column and each 3x3 boxes. A fast growing youtube channel [Cracking the Cryptic](https://www.youtube.com/c/CrackingTheCryptic) contains a lot of interesting puzzle released daily.

![](jovi_al.png)

 <tiny>This puzzle is a very famous handcrafted puzzle by [Jovi_al](https://twitter.com/jovi_al01?lang=en)</tiny>



# Explanation about the assignment

You are given the skeleton code [Sudoku.java](Sudoku.java). Complete all methods in the assignment with respect to the instruction stated on the assignment.


# Demo

You can refer to the demo program for the correct behavior of the program. Your program may behave as follow if it is implemented correctly. (Note: this puzzle does not have a unique solution, it is placed here because it is simple.)



```txt
s
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│ 1 2 3 │       │       │ 
│ 4 5 6 │ ▪     │       │ 
│ 7 8 9 │       │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │ 1   3 │       │ 
│       │ 4 5 6 │       │ 
│       │   8 9 │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │       │ 4   3 │ 
│       │       │ 1 5 6 │ 
│       │       │   8 2 │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
a
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│ 1 2 3 │       │       │ 
│ 4 5 ₆ │       │       │ 
│ 7 8 9 │       │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │ 1   3 │       │ 
│       │ 4 5 6 │       │ 
│       │   8 9 │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │       │ 4   3 │ 
│       │       │ 1 5 6 │ 
│       │       │   8 2 │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
d
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│ 1 2 3 │       │       │ 
│ 4 5 6 │ ▪     │       │ 
│ 7 8 9 │       │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │ 1   3 │       │ 
│       │ 4 5 6 │       │ 
│       │   8 9 │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │       │ 4   3 │ 
│       │       │ 1 5 6 │ 
│       │       │   8 2 │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
3
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│ 1 2 3 │       │       │ 
│ 4 5 6 │ ₃     │       │ 
│ 7 8 9 │       │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │ 1   3 │       │ 
│       │ 4 5 6 │       │ 
│       │   8 9 │       │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 
│       │       │ 4   3 │ 
│       │       │ 1 5 6 │ 
│       │       │   8 2 │ 
┼ ─ ─ ─ ┼ ─ ─ ─ ┼ ─ ─ ─ ┼ 

```


## Understanding the Assignment Test (UAT)

This part is independent to your programming code. You will need to answer the following short questions by **another due date**.
Submit your answers on Moodle. 

1. Which method is responsible to mark a digit into a cell?
2. Which method is responsible to check if there are duplicate values in a box?
3. What value will the above method return if there are duplicate values in a box?



---

## Programming Style and Documentation 

Good programming style (indentation, comments) are always essential.  Blank lines, spaces between operators/variables (wherever appropriate) and meaningful variable names are required. Your program should be properly indented.  Good choice of variable names and method names is also essential.  Your program must have proper internal documentation, as described below: 

### Header Block 
For your java file, there must be a header at the beginning of the file, with your name and your UID. 

### Inline Comments 
Wherever necessary and appropriate, you should add inline comments to explain the execution flow of your program. 



## Submission 
For submission upload the file `Sudoku.java` to Moodle.  

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

 Plagiarism is a serious offence and can be easily detected. Please don't share your code to your classmate even if they are threatening you with friendship. If they don't have the ability to work on something that can compile, they would not be able to change your code to a state that we can't detect the act of plagiarism. For the first commit of plagiarism, regardless you shared your code or copied code from others, you will receive 0 with an addition of 5 mark penalty. If you commit plagiarism twice, your case will be presented in the exam board and you will receive a F directly.

## Marking Scheme 
This assignment is worth 9% of the course mark.  There are three elements in the marking scheme: 
* 5% - Understanding the Assignment Test (UAT)
* 90% - a working program that functions as specified 
* 5% - Programming style and documentation 
* -50% - if you define any class variable (field), addition class, or change the method `runOnce`.

Please note that submitting a program that cannot be compiled would result in a very low mark. 

 

## Interview 
Should the teaching team see fit, students may be requested to attend an interview to explain about their program.  Students failing to attend such interview or to demonstrate a good understanding of their own program may result in mark deduction. 


