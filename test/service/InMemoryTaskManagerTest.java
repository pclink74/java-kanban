package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;





class InMemoryTaskManagerTest {

    @BeforeEach
    public void beforeEach() {
        HashMap<Integer, Task> tasks = new HashMap<>();
        HashMap<Integer, Epic> epics = new HashMap<>();
        HashMap<Integer, SubTask> subTasks = new HashMap<>();
        int seq = 0;
      //  TaskManager taskManager = Managers.getDefault();
    }
    @Test
    void  dasTaskCreateCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        final int taskId = task1.getId();
        final Task savedTask = taskManager.get(taskId);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");
        final List<Task> tasks = taskManager.getAll();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task1, tasks.get(0), "Задачи не совпадают.");

    }
    @Test
    void dasTaskUpdateCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));

        final int taskId = task1.getId();
        task1.setStatus(Status.IN_PROGRESS);
        final Task savedTask = task1;
        taskManager.update(task1);
        assertEquals(task1, savedTask, "Задача не не обновилась.");
    }
    @Test
    void dasTaskIdGetCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        final int taskId = task1.getId();
        assertNotNull(taskId, "Id не возвращаются.");

    }

    @Test
    void dasTaskGetCorrect(){
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        final int taskId = task1.getId();
        final Task savedTask = taskManager.get(taskId);
        assertNotNull(savedTask, "Задача не получена.");
    }
    @Test
    void dasTaskDeleteCorrect(){
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        final int taskId = task1.getId();
        taskManager.delete(taskId);
        final Task savedTask = taskManager.get(taskId);
        assertNull(savedTask, "Задача не удалена.");
    }
    @Test
    void dasAllTasksGetCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        Task task2=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        final List<Task> tasks = taskManager.getAll();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
    }
    @Test
    void dasDeleteAllTaskCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Task task1=taskManager.create(new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1"));
        Task task2=taskManager.create(new Task("Задача Тест 2", Status.NEW, "Это Задача Тест 2"));
        taskManager.deleteAllTasks();
        final List<Task> tasks = taskManager.getAll();
        assertNull(tasks, "Задачи не удаляются.");
    }
    @Test
    void dasEpicCreateCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        final int epicId = testEpic1.getId();
        final Epic savedEpic = taskManager.getEpic(epicId);
        assertNotNull(savedEpic, "Эпик не найдена.");
        final List<Epic> epics = taskManager.getAllEpics();
        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество эпиков.");
    }
    @Test
    void dasEpicUpdateCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        final int epicId = testEpic1.getId();
        testEpic1.setName("Тестовый эпик2");
        testEpic1.setDescription("Это второй Эпик");
        taskManager.updateEpic(testEpic1);
        Epic savedEpic = taskManager.getEpic(epicId);
        assertEquals(testEpic1.getName(), savedEpic.getName(), "Имя не обновилось");
        assertEquals(testEpic1.getName(), savedEpic.getName(), "Описание не обновилось");
    }
    @Test
    void dasEpicGetCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        final int epicId = testEpic1.getId();
        Epic savedEpic = taskManager.getEpic(epicId);
        assertNotNull(savedEpic, "Эпик не получен");
        assertEquals(testEpic1.getName(), savedEpic.getName(), "Имя не совпадает ");
        assertEquals(testEpic1.getName(), savedEpic.getName(), "Описание не совпадает");
    }
    @Test
    void dasEpicDeleteCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        final int epicId = testEpic1.getId();
        taskManager.deleteEpic(epicId);
        Epic savedEpic = taskManager.getEpic(epicId);
        assertNull(savedEpic, "Эпик не удалён");

    }
    @Test
    void dasGetAllEpicCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        Epic testEpic2 = taskManager.createEpic((new Epic("Тестовый эпик2", "Это второй Эпик")));
        final List<Epic> epics = taskManager.getAllEpics();
        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(2, epics.size(), "Неверное количество эпиков.");
    }
    @Test
    void dasDeleteAllEpicCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        Epic testEpic2 = taskManager.createEpic((new Epic("Тестовый эпик2", "Это второй Эпик")));
        taskManager.deleteAllEpic();
        final List<Epic> epics = taskManager.getAllEpics();
        final List<SubTask> subTasks = taskManager.getAllSubTasks();
        assertNull(epics, "Эпики не удалены.");
        assertNull(subTasks, "Подзадачи эпиков не удалены");
    }
    @Test
    void  dasSubTaskCreateCorrect() {
        TaskManager taskManager = Managers.getDefault();
        Epic testEpic1 = taskManager.createEpic((new Epic("Тестовый эпик1", "Это первый Эпик")));
        // final int epicId = testEpic1.getId();
        SubTask subTask1 = taskManager.createSubTask(new SubTask("Подзадача 1", Status.NEW, "Этоподзадача 1", testEpic1));
        final int subTaskId = subTask1.getId();
        final SubTask savedSubTask = taskManager.getSubTask(subTaskId);
        assertNotNull(savedSubTask, "Подзадача не найдена.");
        assertEquals(subTask1, savedSubTask, "Подзадачи не совпадают.");
        final List<SubTask> subTasks = taskManager.getAllSubTasks();
        assertNotNull(subTasks, "Подзадачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество подзадач.");
        assertEquals(subTask1, subTasks.get(0), "подзадачи не совпадают.");
    }
}