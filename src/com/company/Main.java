package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main (String str[]) throws IOException {
        Scanner scan = new Scanner (System.in);
        boolean gameNotOver = true;
        ArrayList<String> words = new ArrayList<>();
        try {
            File wordList = new File("/home/benzimm07/IdeaProjects/Wordle Solver/src/com/company/smallWordleArray.csv");
            Scanner reader = new Scanner(wordList);
            reader.useDelimiter(",");
            while (reader.hasNext()){
                words.add(reader.next());
            }
        } catch (FileNotFoundException e){
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        String answer = words.get((int) (Math.random()*2315));

        while (gameNotOver)
        {
            boolean fiveLetter = true;
            String guess = "";

            while (fiveLetter){
                System.out.println("Guess the five letter word!");
                guess = scan.nextLine();
                if (guess.length() < 5)
                    System.out.println("Sorry, your word is not long enough!");
                else if (guess.length() > 5)
                    System.out.println("Sorry, your word is too long!");
                else
                    fiveLetter = false;
            }

            String guessLower = guess.toLowerCase();
            System.out.println("Your guess is: " + guessLower);

            String[] characters = {"-", "-", "-", "-", "-"};

            boolean match = true;
            for (int i = 0; i < 5; i++)
            {
                if (answer.substring(i, i + 1).equals(guessLower.substring(i, i + 1)))
                    characters[i] = answer.substring(i, i + 1);
                else if (answer.indexOf(guessLower.substring(i, i + 1)) != -1)
                {
                    characters[i] = "(" + guessLower.substring(i, i + 1) + ")";
                    match = false;
                }
                else
                    match = false;
            }

            if (match == true)
                gameNotOver = false;
            System.out.println(characters[0] + characters[1] + characters[2] + characters[3] + characters[4]);
        }
    }
}