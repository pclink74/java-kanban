package service;

import model.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    List<Task> getAllHistory();

    void remove(int id);
}
