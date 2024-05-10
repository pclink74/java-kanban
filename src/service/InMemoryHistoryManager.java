package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    int historyLength =10;
    private List<Task> history = new ArrayList<>(historyLength);

    @Override
    public void add(Task task) {
        if (!(history.size() < historyLength)){ history.remove(0);}
        history.add(task);
    }

    @Override
    public List<Task> getAllHistory() {
        return history;
    }
}
