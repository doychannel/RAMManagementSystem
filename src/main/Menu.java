package main;

import java.util.Scanner;

public class Menu {
    public static int getChoice(String[] options) {

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your options from 1 - " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        String s="";
        do{
            System.out.println("Enter valid value!");
            s=sc.nextLine();
        }
        while(s.isEmpty()||!s.matches("[\\d+]"));
        System.out.println("---------------------------");
        return Integer.parseInt(s);
    }
}
