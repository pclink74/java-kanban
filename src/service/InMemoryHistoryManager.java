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

    HashMap<Integer, Node> history = new HashMap<>();
    Node first;
    Node last;

    void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(task, l, null);
        if (l == null) {
            first = newNode;
            last = newNode;
        } else {
            l.next = newNode;
            last = newNode;
        }
        history.put(task.getId(), newNode);
    }

    ArrayList<Task> getTask() {
        ArrayList<Task> list = new ArrayList<>();
        Node current = first;
        while (current != null) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    void removeNode(Node node) {
        if (node == null) {
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
        Node node = history.get(task.getId());
        if (node != null) {
            removeNode(node);
        }
        linkLast(task);
    }

    @Override
    public List<Task> getAllHistory() {
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
