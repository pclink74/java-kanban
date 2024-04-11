package model;

import java.util.HashSet;

public class Epic extends Task {

 /*   private int id;
    private String  name;
    private Status status;
    private String description;
 */   public HashSet<SubTask> subTasksSet;
    public Epic (String name, Status status, String description) {
        super(name, status, description);
        this.name = name;
        this.status = status;
        this.description = description;
        subTasksSet = new HashSet<>();
        //updateStatus;
    }
    public Epic (String name, String description) {
        super(name, Status.NEW, description);
        this.name = name;
        // this.status = status;
        this.description = description;
        subTasksSet = new HashSet<>();
    }
   // public void setId(int id){ this.id = id;}
 //   public void setStatus(Status status){ this.status = status;}

    @Override
    public Status getStatus() {
        return status;
    }
    public HashSet<SubTask> getSubTaskSet() {
        return subTasksSet;
    }
    public void addSubTask(SubTask subTask) {
        subTasksSet.add(subTask);
    }
    public void deleteSubTask(SubTask subTask) {
        subTasksSet.remove(subTask);
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", subTasksList=" + subTasksSet.size() +
                '}';
    }
}
