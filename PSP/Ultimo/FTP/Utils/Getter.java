package Utils;

import java.util.Scanner;

public class Getter
{
    
    public static String GetString(String sentence)
    {
        System.out.println(sentence);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
    public static int GetInt(String sentence)
    {
        System.out.println(sentence);
        Scanner input = new Scanner(System.in);
        return Integer.parseInt(input.nextLine());
    }
    
    public static int GetMenu(String[] sentences)
    {
        System.out.print("\n");
        for (int i = 0; i < sentences.length; i++)
            System.out.println("\t" + (i + 1) + ". " + sentences[i]);
        System.out.println("\t0. Exit");
        
        return GetInt("Selection:");
    }
    
}
