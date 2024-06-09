package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {

    @Test
    void shouldEqualWithCopy() {
        Epic epic = new Epic("Эпик 1", Status.NEW, "Это первый эпик");
        SubTask subTask = new SubTask("Задача Тест 1", Status.NEW, "Это Задача Тест 1", epic);
        SubTask subTaskCopy = subTask;
        assertEqualsSubTask(subTask, subTaskCopy, "Не совпадает ");
    }

    private static void assertEqualsSubTask (SubTask expected, SubTask actual, String message) {
        assertEquals(expected.getId(), actual.getId(), message + " Id");
        assertEquals(expected.getName(), actual.getName(), message + " Name");
        assertEquals(expected.getDescription(), actual.getDescription(), message + " Description");
        assertEquals(expected.getStatus(), actual.getStatus(), message + " STATUS");
    }
}