package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Managers;
import service.TaskManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void sHouldEqualWithCopy() {
        Task task = new Task("Задача Тест 1", Status.NEW, "Это Задача Тест 1");
        Task taskCopy = task;
        assertEqualsTask(task, taskCopy, "Не совпадает ");
    }

    static void assertEqualsTask (Task expected, Task actual, String message) {
        assertEquals(expected.getId(), actual.getId(), message + " Id");
        assertEquals(expected.getName(), actual.getName(), message + " Name");
        assertEquals(expected.getDescription(), actual.getDescription(), message + " Description");
        assertEquals(expected.getStatus(), actual.getStatus(), message + " STATUS");
    }
}