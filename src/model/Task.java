package model;

public class Task {
    public String info;
    public int id;
    public boolean completed;
    public Task(int id, String info, boolean completed){
        this.info = info;
        this.id = id;
        this.completed = completed;
    }
    public String stringToSave(){
        String out = id+" "+info+" "+completed+"\n";
        return out;
    }
}
