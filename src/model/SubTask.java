package model;

public class SubTask extends Task {
    private Epic epic;
    private int id;
    private String  name;
    private Status status;
    private String description;
    public SubTask(String name, Status status, String description, Epic epic) {
        super(name, status, description);
        this.epic = epic;
        this.name = name;
        this.status = status;
        this.description = description;
    }
    public SubTask(String name, Status status, String description) {
        super(name, status, description);
        this.name = name;
        this.status = status;
        this.description = description;
    }
    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
    @Override
    public Status getStatus() {
        return status;
    }
    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public int getId() { return id; }
    public void setEpic(Epic epic){ this.epic = epic;}
    public Epic getEpic() {return epic;}
    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", epic=" + epic.getName() +
                '}';
    }
}
