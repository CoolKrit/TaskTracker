package domain;

import data.model.Epic;
import data.model.Subtask;
import data.model.Task;

import java.util.List;

public interface TaskManager {
    // Методы для data.model.Task
    List<Task> getAllTasks();
    void clearAllTasks();
    Task getTask(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void removeTaskById(int id);

    // Методы для data.model.Epic
    List<Epic> getAllEpics();
    void clearAllEpics();
    Epic getEpic(int id);
    void addEpic(Epic epic);
    void updateEpic(Epic epic);
    void removeEpicById(int id);
    List<Subtask> getEpicSubtasks(int epicId);

    // Методы для data.model.Subtask
    List<Subtask> getAllSubtasks();
    void clearAllSubtasks();
    Subtask getSubtask(int id);
    void addSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void removeSubtaskById(int id);

    // История просмотров
    List<Task> getHistory();
}