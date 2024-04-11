package model;

public class SubTask extends Task {
    private Epic epic;
    private int id;
    public SubTask(String name, Status status, String description, Epic epic) {
        super( name, status, description);
        this.epic = epic;
    }
    @Override
    public void setId(int id) { this.id = id; }
    @Override
    public int getId() { return id; }
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
