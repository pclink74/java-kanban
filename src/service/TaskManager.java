package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.HashSet;
import java.util.List;

public interface TaskManager {
    Task create(Task task);

    void update(Task task);

    Task get(int id)          // 1.3  Получить задачу!
    ;

    void delete(int id)            // 1.4 Удалить задачу!
    ;

    List<Task> getAll();

    void deleteAllTasks();

    Epic createEpic(Epic epic);

    void updateEpic(Epic epic);

    Epic getEpic(int id);

    void deleteEpic(int id);

    List<Epic> getAllEpics();

    void deleteAllEpic();

    SubTask createSubTask(SubTask subTask);

    void updateSubTask(SubTask subTask);

    SubTask getSubTask(int id);

    void deleteSubTask(int id);

    List<SubTask> getAllSubTasks();

    void deleteAllSubTask();

    HashSet<SubTask> getEpicAllSubTasks(int id);

    List<Task> getHistory();

}
