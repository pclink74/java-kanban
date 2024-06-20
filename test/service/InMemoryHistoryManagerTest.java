package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryHistoryManagerTest {

    @Test
    void shouldAddTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task(0, "Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task task2 = new Task(1, "Задача Тест 2", Status.NEW, "Это Задача Тест 2");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(task2);
        List<Task> savedHistory = inMemoryHistoryManager.getAllHistory();
        assertNotNull(savedHistory, "История не сохраняется");
        assertEquals(2, savedHistory.size(), "Число задач не совпадает");
    }

    @Test
    void shouldRemoveFirstTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task(0, "Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task task2 = new Task(1, "Задача Тест 2", Status.NEW, "Это Задача Тест 2");
        Task task3 = new Task(2, "Задача Тест 3", Status.NEW, "Это Задача Тест 3");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(task2);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.add(task3);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.remove(task1.getId());
        System.out.println(inMemoryHistoryManager.getAllHistory());
        assertEquals(inMemoryHistoryManager.getAllHistory(), List.of(task2, task3), "Задача в начале списка не удаляется");
    }

    @Test
    void shouldRemoveLastTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task(0, "Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task task2 = new Task(1, "Задача Тест 2", Status.NEW, "Это Задача Тест 2");
        Task task3 = new Task(2, "Задача Тест 3", Status.NEW, "Это Задача Тест 3");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(task2);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.add(task3);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.remove(task3.getId());
        System.out.println(inMemoryHistoryManager.getAllHistory());
        assertEquals(inMemoryHistoryManager.getAllHistory(), List.of(task1, task2), "Задача в конце сптска не удаляется");
    }

    @Test
    void shouldRemoveTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task(0, "Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task task2 = new Task(1, "Задача Тест 2", Status.NEW, "Это Задача Тест 2");
        Task task3 = new Task(2, "Задача Тест 3", Status.NEW, "Это Задача Тест 3");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(task2);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.add(task3);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.remove(task2.getId());
        System.out.println(inMemoryHistoryManager.getAllHistory());
        assertEquals(inMemoryHistoryManager.getAllHistory(), List.of(task1, task3), "Задача в середине списка не удаляется");
    }

    @Test
    void shouldRemoveAlTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task(0, "Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task task2 = new Task(1, "Задача Тест 2", Status.NEW, "Это Задача Тест 2");
        Task task3 = new Task(2, "Задача Тест 3", Status.NEW, "Это Задача Тест 3");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(task2);
        inMemoryHistoryManager.add(task3);
        System.out.println(inMemoryHistoryManager.getAllHistory());
        inMemoryHistoryManager.remove(task1.getId());
        inMemoryHistoryManager.remove(task2.getId());
        inMemoryHistoryManager.remove(task3.getId());
        System.out.println(inMemoryHistoryManager.getAllHistory());
        assertNull(inMemoryHistoryManager.getAllHistory(), "Задача в середине списка не удаляется");
    }
}