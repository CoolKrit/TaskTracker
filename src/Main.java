public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        // Задачи
        manager.addTask(new Task("First Task", "OK", Task.Status.NEW));
        manager.addTask(new Task("Second Task", "OK", Task.Status.NEW));

        // Эпики и подзадачи
        manager.addEpic(new Epic("First Epic", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "First Subtask", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "Second Subtask", "OK", Task.Status.NEW));

        manager.addEpic(new Epic("Second Epic", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(6, "First Subtask", "OK", Task.Status.NEW));

        // Списки
        printLists(manager);

        // Смена статусов
        manager.updateTask(new Task(1, "First Task", "OK", Task.Status.DONE));
        manager.updateTask(new Task(2, "Second Task", "OK", Task.Status.DONE));
        manager.updateSubtask(new Subtask(4, 3, "First Subtask", "OK", Task.Status.DONE));
        manager.updateSubtask(new Subtask(7, 6, "First Subtask", "OK", Task.Status.DONE));

        // Обновленные списки
        System.out.println("\nAfter status update:");
        printLists(manager);

        // Удаление одной задачу и одного эпик
        manager.removeTaskById(1);
        manager.removeSubtaskById(7);
        manager.removeEpicById(3);

        // Финальные списки
        System.out.println("\nAfter deletion:");
        printLists(manager);
    }

    private static void printLists(Manager manager) {
        System.out.println("All tasks:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nAll epics:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nAll subtasks:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}