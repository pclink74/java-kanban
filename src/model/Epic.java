package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    // private Epic epic;
    private int id;
    private String  name;
    private Status status;
    private String description;
    public List<SubTask> subTasksList;

    //private int id;
    public Epic (String name, Status status, String description) {

        super(name, status, description);
        this.name = name;
        this.status = status;
        this.description = description;
        subTasksList = new ArrayList<>();
        //updateStatus;
    }
    public Epic (String name, String description) {
        super(name, Status.NEW, description);
        this.name = name;
        // this.status = status;
        this.description = description;
        subTasksList = new ArrayList<>();
    }


    public void setId(int id){ this.id = id;}
    public void setStatus(Status status){ this.status = status;}

    @Override
    public Status getStatus() {
        return status;
    }

    // public int getId(){return id;}
    public List<SubTask> getSubTaskList() {
        return subTasksList;
    }
    public void addSubTask(SubTask subTask) {
        subTasksList.add(subTask);
    }
    public void deleteSubTask(SubTask subTask) {
        subTasksList.remove(subTask);
    }

    public int getId(){
        return id;
    }

 /*   public Status updateStatus(){

        boolean allStatusNew = true;
        boolean allStatusDone = true;
        if (subTasksList.isEmpty()){
            return Status.NEW;
        }else{
            for (SubTask subTask : subTasksList){
                Status tempStatus = subTask.getStatus();
                if (subTask.getStatus() != Status.NEW) {
                    allStatusNew = false;
                }
                if (subTask.getStatus() != Status.DONE) {
                    allStatusDone = false;
                }
            }
            if (allStatusNew){
                return Status.NEW;
            }else if (allStatusDone){
                return Status.DONE;
            }else{
                return Status.IN_PROGRESS;
            }
        }

    } */

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", subTasksList=" + subTasksList.size() +
                '}';
    }
}
