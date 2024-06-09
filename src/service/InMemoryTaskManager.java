package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int seq = 0;
    private final InMemoryHistoryManager inMemoryHistoryManager;

    public InMemoryTaskManager(InMemoryHistoryManager inMemoryHistoryManager) {
        this.inMemoryHistoryManager = inMemoryHistoryManager;
    }

    @Override
    public Task create(Task task) {                     // 1.1 Создать задачу!
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void update(Task task) {                    // 1.2 Обновить задачу!
        if (!tasks.containsKey(task.getId())) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public Task get(int id) {                          // 1.3  Получить задачу!
        if (!tasks.containsKey(id)) {
            return null;
        }
        inMemoryHistoryManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public void delete(int id) {                     // 1.4 Удалить задачу!
        inMemoryHistoryManager.remove(id);
        tasks.remove(id);
    }

    @Override
    public List<Task> getAll() {                             // 1.5 Получить все задачи!
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks(){                           // 1.6 Удалиь все задачи!
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            inMemoryHistoryManager.remove(entry.getKey());
        }
        tasks.clear();
    }
    @Override
    public Epic createEpic(Epic epic) {                     // 2.1 Создать Эпик!
        epic.setId(generateId());
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public void updateEpic(Epic epic) {                      // 2.2 Обновить Эпик
        Epic saved=epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
    }

    @Override
    public Epic getEpic(int id) {                           // 2,3 Получить Эпик !
        if (!epics.containsKey(id)) {
            return null;
        }
        inMemoryHistoryManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void  deleteEpic(int id) {                        // 2.4 Удалить эпик
         Epic removed = epics.remove(id);
        if (removed == null) {
            return;
        }
        inMemoryHistoryManager.remove(removed.getId());
         HashSet<SubTask> set =  removed.getSubTaskSet();
         if (!set.isEmpty()) {
             for (SubTask subTask: set) {
                 inMemoryHistoryManager.remove(subTask.getId());
                 subTasks.remove(subTask.getId());

             }
         }
    }

    @Override
    public List<Epic> getAllEpics() {                         // 2.5 Получить все эпики!
        return new ArrayList<>(epics.values());
    }

    @Override
    public void  deleteAllEpic() {                         // 2.6 Удалить все эпики
        subTasks.clear();
        epics.clear();
    }

    @Override
    public SubTask createSubTask(SubTask subTask){          // 3.1 Создать подзадачу
        Epic epic = epics.get((subTask.getEpic()).getId());
        if (epic==null) {return null;}
        subTask.setId(generateId());
        epic.addSubTask(subTask);
        subTasks.put(subTask.getId(), subTask);
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
        return subTask;
    }

    @Override
    public void updateSubTask(SubTask subTask){             // 3.2 Обновить подзадачу
        if (subTask == null) {
            return;
        }
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpic().getId());
        epic.addSubTask(subTask);
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
    }

    @Override
    public SubTask getSubTask(int id) {                     // 3.3 Вернули подзадачу
        if (!subTasks.containsKey(id)) {
            return null;
        }
        inMemoryHistoryManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void deleteSubTask(int id) {                          // 3.4 Удалить подзадачу
        if (subTasks.get(id) == null) {
            return;
        }
        Epic epic = subTasks.get(id).getEpic();
        epic.deleteSubTask(subTasks.get(id));
        subTasks.remove(id);
        inMemoryHistoryManager.remove(id);
        epic.setStatus(updateEpicStatus(epic));
        epics.put(epic.getId(), epic);
    }
    @Override
    public List<SubTask> getAllSubTasks() {                    // 3.5 Получить все подзадачи!
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void deleteAllSubTask() {                           // 3.6 Удалить все подзадачи
        if (subTasks.isEmpty()) {
            return;
        }
        subTasks.clear();
        for (Epic epic : epics.values()) {
            for (SubTask item : epic.getSubTaskSet()) {
                inMemoryHistoryManager.remove(item.getId());
            }
            epic.getSubTaskSet().clear();
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public HashSet<SubTask> getEpicAllSubTasks(int id) {                    // 4.1 Все подзадачи эпика
        Epic epic = epics.get(id);
        if (epic==null) {
            return new HashSet<>();
        } else {
            HashSet<SubTask> set = epic.getSubTaskSet();
            return set;
        }
    }
    private int generateId(){
        return seq++;
    }

    private Status updateEpicStatus(Epic epic) {
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

    @Override
    public List<Task> getHistory(){
        return inMemoryHistoryManager.getAllHistory();
    }
}




