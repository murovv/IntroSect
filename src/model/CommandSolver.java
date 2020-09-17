package model;

import view.MainFrame;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CommandSolver {
    CaseManager caseManager;
    public ArrayList<Task> currentTasks;
    public CommandSolver(ArrayList<Task> currentTasks) {
        this.currentTasks = currentTasks;
        caseManager = new CaseManager(currentTasks);
    }
    public void solve(String command) {
        StringTokenizer st = new StringTokenizer(command);
        String commandName = st.nextToken();
        switch (commandName) {
            case "add":
                caseManager.add(st.nextToken());
                break;
            case "delete":
                caseManager.delete(Integer.parseInt(st.nextToken()));
                break;
            case "save":
                caseManager.save(st.nextToken());
                break;
            case "load":
                caseManager.load(st.nextToken());
                break;
            case "complete":
                caseManager.complete(Integer.parseInt(st.nextToken()));
            default:
                System.out.println("Команда не распознана(");
        }
    }
}
