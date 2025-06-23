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

        System.out.println("1. Создаем задачи и эпики:");
        // Создаем две задачи
        manager.addTask(new Task("Задача 1", "Описание 1", Task.Status.NEW));
        manager.addTask(new Task("Задача 2", "Описание 2", Task.Status.NEW));

        // Создаем эпик с тремя подзадачами
        manager.addEpic(new Epic("Эпик 1", "Описание эпика 1", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "Подзадача 1", "Описание 1", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "Подзадача 2", "Описание 2", Task.Status.NEW));
        manager.addSubtask(new Subtask(3, "Подзадача 3", "Описание 3", Task.Status.NEW));

        // Создаем эпик без подзадач
        manager.addEpic(new Epic("Эпик 2", "Описание эпика 2", Task.Status.NEW));

        printLists(manager);
        System.out.println("\nТекущая история (должна быть пустой):");
        printHistory(manager.getHistory());

        System.out.println("\n2. Запрашиваем задачи в разном порядке:");
        // Запрашиваем задачи в разном порядке
        manager.getTask(1);
        manager.getEpic(3);
        manager.getSubtask(4);
        manager.getTask(2);
        manager.getSubtask(5);
        manager.getEpic(6);
        manager.getSubtask(6);
        manager.getSubtask(7); // Несуществующая подзадача (не должна добавиться)

        System.out.println("\nИстория после первого запроса:");
        printHistory(manager.getHistory());

        System.out.println("\n3. Запрашиваем некоторые задачи повторно:");
        manager.getEpic(3);
        manager.getTask(1);
        manager.getSubtask(4);

        System.out.println("\nИстория после повторных запросов (должна обновиться без дубликатов):");
        printHistory(manager.getHistory());

        System.out.println("\n4. Удаляем задачу 2, которая есть в истории:");
        manager.removeTaskById(2);
        System.out.println("\nИстория после удаления задачи 2:");
        printHistory(manager.getHistory());

        System.out.println("\n5. Удаляем эпик 3 с подзадачами:");
        manager.removeEpicById(3);
        System.out.println("\nИстория после удаления эпика 3:");
        printHistory(manager.getHistory());

        System.out.println("\n6. Финальное состояние всех задач:");
        printLists(manager);
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