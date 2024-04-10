package Service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    private int seq = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }
    public Task create(Task task){                     // 1.1 Создать задачу!
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }
    public void update(Task task){                    // 1.2 Обновить задачу!
        tasks.put(task.getId(), task);
    }
    public Task get(int id) {return tasks.get(id);}          // 1.3  Получить задачу!
    public void delete(int id){tasks.remove(id);}            // 1.4 Удалить задачу!
    public List<Task> getAll() {                             // 1.5 Получить все задачи!
        return new ArrayList<>(tasks.values());
    }
    public void deleteAllTasks(){                           // 1.6 Удалиь все задачи!
        tasks.clear();
    }
    public Epic createEpic(Epic epic) {                     // 2.1 Создать Эпик!
        epic.setId(generateId());
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
        return epic;
    }
    public void updateEpic(Epic epic){                      // 2.2 Обновить Эпик
        Epic saved=epics.get(epic.getId());
        if(saved == null) { return; }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
    }
    public Epic getEpic(int id) {                           // 2,3 Получить Эпик !
        return epics.get(id);
    }
    public void  deleteEpic(int id) {                        // 2.4 Удалить эпик
         Epic epic = epics.get(id);
         HashSet<SubTask> set =  epic.getSubTaskSet();
         if (!set.isEmpty()) {
             for (SubTask subTask: set) {
                 subTasks.remove(subTask.getId());
                 epic.deleteSubTask(subTask);
             }
         }
         epics.remove(id);
    }
    public List<Epic> getAllEpics() {                         // 2.5 Получить все эпики!
        return new ArrayList<>(epics.values());
    }
    public void  deleteAllEpic() {                         // 2.6 Удалить все эпики
        subTasks.clear();
        epics.clear();
    }
    public SubTask createSubTask (SubTask subTask){          // 3.1 Создать подзадачу
        subTask.setId(generateId());
        Epic epic = epics.get(subTask.getEpic().getId());
        epic.addSubTask(subTask);
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epic);
        epics.put(epic.getId(), epic);
        return subTask;
    }
    public void updateSubTask (SubTask subTask){             // 3.2 Обновить подзадачу
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpic().getId());
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
    }
    public SubTask getSubTask ( int id){
        return subTasks.get(id);
    }  // 3.3 Вернули подзадачу
    public void deleteSubTask ( int id){                          // 3.4 Удалить подзадачу
        Epic epic = subTasks.get(id).getEpic();
        epic.deleteSubTask(subTasks.get(id));
        subTasks.remove(id);
        updateEpicStatus(epic);
        epics.put(epic.getId(), epic);
    }
    public List<SubTask> getAllSubTasks () {                    // 3.5 Получить все подзадачи!
        return new ArrayList<>(subTasks.values());
    }
    public void deleteAllSubTask () {           // 3.6 Удалить все подзадачи
        if (subTasks.isEmpty()) {
            return;
        }
        for (int key : subTasks.keySet()) {
            SubTask subTask = subTasks.get(key);
            Epic epic = subTasks.get(key).getEpic();
            epic.deleteSubTask(subTasks.get(key));
            subTasks.remove(key);
            updateEpicStatus(epic);
            epics.put(epic.getId(), epic);
        }
    }
    public HashSet<SubTask> getEpicAllSubTasks (int id) {                    // 4.1 Все подзадачи эпика
        Epic epic = epics.get(id);
        HashSet<SubTask> set =  epic.getSubTaskSet();
        return set;
    }
    public int generateId(){
        return seq++;
    }
    private Status updateEpicStatus(Epic epic) {
        //this.epic = epic;
        boolean allStatusNew = true;
        boolean allStatusDone = true;
       HashSet<SubTask> subTaskInEpic = epic.getSubTaskSet();
        if (subTaskInEpic.isEmpty()) {
            return Status.NEW;
        } else {
            for (SubTask subTask : subTaskInEpic) {
                Status tempStatus = subTask.getStatus();
                if (subTask.getStatus() != Status.NEW) {
                    allStatusNew = false;
                }
                if (subTask.getStatus() != Status.DONE) {
                    allStatusDone = false;
                }
            }
            if (allStatusNew) {
                return Status.NEW;
            } else if (allStatusDone) {
                return Status.DONE;
            } else {
                return Status.IN_PROGRESS;
            }
        }
    }
}



