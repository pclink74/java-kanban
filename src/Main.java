import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();
        Task testTask1 = taskManager.create(new Task("Первая задача", Status.NEW, "Это первая задача"));
        Task testTask2 = taskManager.create(new Task("Вторая задача", Status.NEW, "Это вторая задача"));
        Epic testEpic1 = taskManager.createEpic((new Epic("первый эпик", "Это первый Эпик")));
        SubTask testSubTask1 = taskManager.createSubTask(new SubTask("Первая подзадача", Status.NEW, "Это первая подзадача", testEpic1));
        SubTask testSubTask2 = taskManager.createSubTask(new SubTask("Вторая подзадача", Status.NEW, "Это вторая подзадача", testEpic1));
        Epic testEpic2 = taskManager.createEpic((new Epic("Второй эпик", "Это второй Эпик")));
        SubTask testSubTask3 = taskManager.createSubTask(new SubTask("Третья подзадача", Status.NEW, "Это третья подзадача", testEpic2));
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAll());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println("Обновляем статусы testTask1 и testSubTask1");
        testTask1.setStatus(Status.DONE);
        taskManager.update(testTask1);
        testSubTask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubTask(testSubTask1);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAll());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println("Удаляем testTask1  и testEpic2");
        taskManager.delete(testTask1.getId());
        taskManager.deleteEpic(testEpic2.getId());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAll());
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.get(1));
        System.out.println(taskManager.getSubTask(3));
        System.out.println("История - " + taskManager.getHistory());
    }
}
