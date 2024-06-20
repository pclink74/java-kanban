package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static class Node {
        Task item;
        Node prev;
        Node next;

        Node(Task element, Node prev, Node next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private final HashMap<Integer, Node> history = new HashMap<>();
    private Node first;
    private Node last;

    void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(task, l, null);
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        last = newNode;
        history.put(task.getId(), newNode);
    }

    void removeNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.prev == node.next) {
            first = null;
            last = null;
            history.clear();
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
            if (node.next == null) {
                last = node.prev;
            }
        }
        if (node.next != null) {
            node.next.prev = node.prev;
            if (node.prev == null) {
                first = node.next;
            }
        }
        history.remove(node.item.getId());
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        Node node = history.get(task.getId());
        if (node != null) {
            removeNode(node);
        }
        linkLast(task);
    }

    @Override
    public List<Task> getAllHistory() {
        if (first == null) {
            return null;
        }
        ArrayList<Task> historyList = new ArrayList<>();
        Node current = first;
        while (current != null) {
            historyList.add(current.item);
            current = current.next;
        }
        return historyList;
    }

    @Override
    public void remove(int id) {
        removeNode(history.get(id));
    }
}
