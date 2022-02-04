
package com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {

        // Here are some instructions to play this game

        System.out.println("--------------------Instructions----------------------");
        System.out.println("PushDown   : press 2");
        System.out.println("PushLeft   : press 4");
        System.out.println("PushRight  : press 6");
        System.out.println("Push Up    : press 8\n");
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;

        // First we add an random element 2 or 4 int game grid
        game.pushRandomly();

        // After that user should input a number as mentioned int instructions to push
        // Up,Down,Left or Right

        while(isTrue){
            try{

                // If at any time the user completed the game then we simply terminate the game
                if(Game.isWon){
                    break;
                }

                // Taking user input to run next move
                int c = sc.nextInt();
                switch (c) {
                    case 4:
                        game.pushLeft();
                        isTrue = game.pushRandomly();
                        System.out.println("Score:" + Game.currentScore);
                        break;
                    case 6:
                        game.pushRight();
                        isTrue = game.pushRandomly();
                        System.out.println("Score:" + Game.currentScore);
                        break;
                    case 8:
                        game.pushUp();
                        isTrue = game.pushRandomly();
                        System.out.println("Score:" + Game.currentScore);
                        break;
                    case 2:
                        game.pushDown();
                        isTrue = game.pushRandomly();
                        System.out.println("Score:" + Game.currentScore);
                        break;
                    default:
                        System.out.println("Game is terminating due to invalid input...");
                        System.exit(0);
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input");
                break;
            }
        }
        if(Game.isWon){
            System.out.println("Congratulations! you have completed the game");
            System.out.println("Score : "+Game.currentScore);
        }else {
            System.out.println("Game Over with Score : " + Game.currentScore);
        }
    }
}
