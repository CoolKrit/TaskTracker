package presentation;

import data.Managers;
import data.model.Epic;
import data.model.Subtask;
import data.model.Task;
import domain.TaskManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        // Задачи
        manager.addTask(new Task("First data.model.Task", "OK", Task.Status.NEW));
        manager.addTask(new Task("Second data.model.Task", "OK", Task.Status.NEW));

        // Эпики и подзадачи
        manager.addEpic(new Epic("First data.model.Epic", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "First data.model.Subtask", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "Second data.model.Subtask", "OK", Task.Status.NEW));

        manager.addEpic(new Epic("Second data.model.Epic", "OK", Task.Status.NEW));
        manager.addSubtask(new Subtask(6, "First data.model.Subtask", "OK", Task.Status.NEW));

        // Списки
        printLists(manager);

        // Смена статусов
        manager.updateTask(new Task(1, "First data.model.Task", "OK", Task.Status.DONE));
        manager.updateTask(new Task(2, "Second data.model.Task", "OK", Task.Status.DONE));
        manager.updateSubtask(new Subtask(4, 3, "First data.model.Subtask", "OK", Task.Status.DONE));
        manager.updateSubtask(new Subtask(7, 6, "First data.model.Subtask", "OK", Task.Status.DONE));

        // Обновленные списки
        System.out.println("\nПосле обновления статуса:");
        printLists(manager);

        // Удаление одной задачу и одного эпик
        manager.removeTaskById(1);
        manager.removeSubtaskById(7);
        manager.removeEpicById(3);

        // Финальные списки
        System.out.println("\nПосле удаления:");
        printLists(manager);

        manager.getTask(1);
        manager.getTask(2);
        manager.getEpic(3);

        System.out.println("\nИстория:");
        printHistory(manager.getHistory());
    }

    private static void printLists(TaskManager manager) {
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nВсе подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }

    private static void printHistory(List<Task> history) {
        if (history.isEmpty()) {
            System.out.println("История пуста");
            return;
        }

        for (Task task : history) {
            System.out.println(task);
        }
    }
}