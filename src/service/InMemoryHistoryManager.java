package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    int historyLength =10;
    private final List<Task> history = new ArrayList<>(historyLength);

    @Override
    public void add(Task task) {
        if (task.equals(null)){return;}
        if (history.size() >= historyLength){ history.removeFirst();}
        history.add(task);
    }

    @Override
    public List<Task> getAllHistory() {
     //   return history;
        ArrayList<Task> historyCopy = new ArrayList<>();
        historyCopy = (ArrayList<Task>) history;
        return historyCopy;
    }
}
