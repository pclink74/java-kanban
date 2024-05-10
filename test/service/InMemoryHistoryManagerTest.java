package service;

import org.junit.jupiter.api.Test;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryHistoryManagerTest {

//    int historyLength =10;
 //   private List<Task> history = new ArrayList<>(historyLength);

    @Test
    void shouldAddTaskInHistory() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        Task task1 = new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Epic epic1 = new Epic("Эпик Тест 1", Status.NEW, "Это Эпик Тест 2");
        inMemoryHistoryManager.add(task1);
        inMemoryHistoryManager.add(epic1);
        List<Task> savedHistory = inMemoryHistoryManager.getAllHistory();
        assertNotNull(savedHistory, "История не сохраняется");
        assertEquals(2, savedHistory.size(), "Число задач не совпадает");
    }
}