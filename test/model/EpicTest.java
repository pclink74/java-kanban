package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EpicTest {

    @Test
    void sHouldEqualWithCopy() {
        Epic epic = new Epic("Эпик 1", Status.NEW, "Это первый эпик");
        Epic epicCopy = epic;
        assertEqualsEpic(epicCopy, epic, "Не совпадает");
    }

    static void assertEqualsEpic (Epic expected, Epic actual, String message) {
        assertEquals(expected.getId(), actual.getId(), message + " Id");
        assertEquals(expected.getName(), actual.getName(), message + " Name");
        assertEquals(expected.getDescription(), actual.getDescription(), message + " Description");
        assertEquals(expected.getStatus(), actual.getStatus(), message + " STATUS");
    }

}