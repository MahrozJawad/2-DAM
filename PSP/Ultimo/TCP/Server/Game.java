package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Game
{
    
    private static final int DEFAULT_MIN_RANGE = 0;
    private static final int DEFAULT_MAX_RANGE = 9;
    private static final int DEFAULT_ATTEMPTS = 2;

    public enum MenuSelection
    {
        Normal, Custom, Exit
    };

    public static MenuSelection sendMenu(PrintWriter output, BufferedReader input) throws IOException
    {
        String menu = "\t1.- Normal"
                + "\t2.- Custom"
                + "\t0.- Exit";

        output.println(menu);

        return MenuSelection.values()[Integer.parseInt(input.readLine()) - 1];
    }

    public static void startGame(PrintWriter output, BufferedReader input, MenuSelection menuSelection) throws IOException
    {
        int attempts = DEFAULT_ATTEMPTS;
        int min = DEFAULT_MIN_RANGE;
        int max = DEFAULT_MAX_RANGE; 

        if (menuSelection == MenuSelection.Custom)
        {
            output.println("Attempts: ");
            attempts = Integer.parseInt(input.readLine());
            output.println("Minimum: ");
            min = Integer.parseInt(input.readLine());
            output.println("Maximum: ");
            max = Integer.parseInt(input.readLine());
        }
        else if(menuSelection == MenuSelection.Exit)
            return;
        
        Random rand = new Random();
        int toGuess = rand.nextInt((max - min) + 1) + min;
        ArrayList<Integer> checkedNumbers = new ArrayList<>();
        
        for (int i = 0; i < attempts; i++)
        {
            output.println(attempts - i + " attempts left " + toGuess);
            
            int clientInput = Integer.parseInt(input.readLine());
            
            if(clientInput == toGuess)
            {
                output.println("Correct! Press enter");
                return;
            }
            else if(checkedNumbers.contains(clientInput))
            {
                output.println("Game over: repeated number! Press enter");
                return;
            }
            
            checkedNumbers.add(clientInput);
        }
        
        output.println("Game over: no more attempts left! Press enter");
        
    }
}
