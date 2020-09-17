package view;

import model.CommandSolver;
import model.Task;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class MainFrame extends JFrame {
    Vector<Vector<Object>> data = new Vector< Vector<Object>>();
    CommandSolver commandSolver;

    JTextField commandLine = new JTextField();
    String[] temp = {"id","task info","completed"};
    Vector<String> columnNames = new Vector<String>(Arrays.asList(temp));
    DefaultTableModel dft = new DefaultTableModel(data,columnNames);
    JTable table = new JTable(dft){
        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 0:
                    return Integer.class;
                case 1:
                    return String.class;
                default:
                    return Boolean.class;
            }
        }
    };
    JScrollPane scrollPane = new JScrollPane(table);
    BorderLayout bl = new BorderLayout();

    String command;
    public void updateDataFromCurrentTasks(){
        data.clear();
        for(int i = 0;i<commandSolver.currentTasks.size();i++){
            Vector<Object> task = new Vector<Object>();
            task.add(commandSolver.currentTasks.get(i).id);
            task.add(commandSolver.currentTasks.get(i).info);
            task.add(commandSolver.currentTasks.get(i).completed);
            data.add(task);
        }
    }
    public void updateCurrentTasksFromData(){
        for(int i = 0;i<data.size();i++){
            for(int j = 0;j<commandSolver.currentTasks.size();j++){
                if((Integer) data.get(i).get(0)==commandSolver.currentTasks.get(j).id){
                    commandSolver.currentTasks.get(j).completed = (Boolean)data.get(i).get(2);
                }
            }
        }
    }
    public MainFrame(CommandSolver commandSolver){
        this.commandSolver = commandSolver;
        this.setSize(new Dimension(480,320));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(bl);
        this.add(commandLine,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        commandLine.addActionListener(e -> {
            command = commandLine.getText();
            if(command.equals("")){
                System.out.println("команда не распознана");
            }else {
                commandLine.setText("");
                if (new StringTokenizer(command).nextToken().equals("all")) {
                    updateCurrentTasksFromData();
                    updateDataFromCurrentTasks();
                    dft = new DefaultTableModel(data, columnNames);
                    table.setModel(dft);
                    this.repaint();
                } else if (new StringTokenizer(command).nextToken().equals("completed")) {
                    updateCurrentTasksFromData();
                    updateDataFromCurrentTasks();
                    for (int i = 0; i < data.size(); i++) {
                        if (!((Boolean) data.get(i).get(2))) {
                            data.remove(i);
                            i--;//remove сдвигает вектор, тем самым будут пропускаться занчения, поэтому i тоже нужно уменьшить
                        }
                    }
                    dft = new DefaultTableModel(data, columnNames);
                    table.setModel(dft);
                    this.repaint();
                } else {
                    dft.fireTableDataChanged();
                    updateCurrentTasksFromData();
                    commandSolver.solve(command);
                }
            }
        });
    }


}
