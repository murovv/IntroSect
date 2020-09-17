package model;

import view.MainFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CaseManager {
    ArrayList<Task> currentTasks;
    public CaseManager(ArrayList<Task> currentTasks) {
        this.currentTasks = currentTasks;
    }
    public void add(String arg){
        System.out.println(arg);
        int nextId = 0;
        if(currentTasks.size()!=0){
            nextId = currentTasks.get(currentTasks.size()-1).id;
        }
        Task task = new Task(nextId+1, arg, false);
        currentTasks.add(task);
    }
    public void all(){
        for (Task i : currentTasks) {
            System.out.print(i.stringToSave());
        }
    }
    public void delete(int id){
        boolean found = false;
        System.out.println(currentTasks.size());
        for (int i = 0; i < currentTasks.size(); i++) {
            if (currentTasks.get(i).id == id) {
                currentTasks.remove(i);
                found = true;
                System.out.println("задание успешно удалено");
                break;
            }
        }
        if (!found) {
            System.out.println("такого задания нет(");
        }
    }
    public void save(String arg) {
        int minId = 0;
        if (arg.lastIndexOf(".") != -1 && arg.lastIndexOf(".") != 0){
            if (arg.substring(arg.lastIndexOf(".") + 1).equals("txt")) {
                try {
                    if (!(new File(arg).exists())) {
                        File f = new File(arg);
                        f.createNewFile();
                    }
                    Scanner scannerForId1 = new Scanner(new FileReader(arg));
                    while (scannerForId1.hasNextLine()) {
                        minId = scannerForId1.nextInt();
                        scannerForId1.nextLine();
                    }
                    FileWriter fw = new FileWriter(arg, true);
                    for (int i = 0; i < currentTasks.size(); i++) {
                        currentTasks.get(i).id = minId + i + 1;
                        fw.write(currentTasks.get(i).stringToSave());
                    }

                    //теперь нужно почистить буфер записи
                    currentTasks.clear();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("ошибка сохранения");
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Некорректное название файла");
            }
            }else{
                System.out.println("Некорректное название файла");
            }
    }
    public void load(String arg) {
        if (arg.lastIndexOf(".") != -1 && arg.lastIndexOf(".") != 0) {
            if (arg.substring(arg.lastIndexOf(".") + 1).equals("txt")) {
                //для начала нужно понять какой id присваивать элементам
                int Id = 0;
                if (currentTasks.size() != 0) {
                    Id = currentTasks.get(currentTasks.size() - 1).id;
                }
                try {
                    FileReader fr = new FileReader(arg);
                    Scanner fileScanner = new Scanner(fr);

                    while (fileScanner.hasNextLine()) {
                        Id++;
                        StringTokenizer st = new StringTokenizer(fileScanner.nextLine());
                        st.nextToken();
                        Task downloadedTask = new Task(Id, st.nextToken(), Boolean.parseBoolean(st.nextToken()));
                        currentTasks.add(downloadedTask);
                    }
                    //подозреваю, что после чтения файл нужно чистить чтобы не записать эти задания еще раз
                    PrintWriter deleter = new PrintWriter(new File(arg));
                    deleter.print("");
                    deleter.close();
                    fr.close();
                } catch (FileNotFoundException fnf) {
                    System.out.println("такого файла не существует");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
           else {
                System.out.println("Некорректное название файла");
            }
        }else{
                System.out.println("Некорретное название файла");
            }
    }
    public void complete(int id){
        boolean found = false;
        for(int i = 0;i<currentTasks.size();i++){
            if(currentTasks.get(i).id==id){
                currentTasks.get(i).completed = true;
                found = true;
                System.out.println("задание успешно выполнено");
                break;
            }
        }
        if(!found){
            System.out.println("такого задания нет(");
        }
    }
}
