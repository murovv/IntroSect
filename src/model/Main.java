package model;

import view.MainFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Task> currentTasks = new ArrayList<>();
        String command;
        String arg;
        CommandSolver commandSolver = new CommandSolver(currentTasks);
        MainFrame mainFrame = new MainFrame(commandSolver);
    }
}
