package data;

import data.model.Epic;
import data.model.Subtask;
import data.model.Task;
import domain.HistoryManager;
import domain.TaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int id;
    private Map<Integer, Task> taskMap;
    private Map<Integer, Subtask> subtaskMap;
    private Map<Integer, Epic> epicMap;
    private final HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.id = 0;
        this.taskMap = new HashMap<>();
        this.subtaskMap = new HashMap<>();
        this.epicMap = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    // data.model.Task
    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public void clearAllTasks() {
        taskMap.clear();
    }

    @Override
    public Task getTask(int id) {
        Task task = taskMap.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public void addTask(Task task) {
        task.setId(++id);
        taskMap.put(task.getId(), task);
    }

    @Override
    public void updateTask(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
        }
    }

    @Override
    public void removeTaskById(int id) {
        taskMap.remove(id);
        historyManager.remove(id);
    }

    // data.model.Epic
    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public void clearAllEpics() {
        subtaskMap.clear();
        epicMap.clear();
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epicMap.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(++id);
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicMap.containsKey(epic.getId())) {
            Epic savedEpic = epicMap.get(epic.getId());
            savedEpic.setName(epic.getName());
            savedEpic.setDescription(epic.getDescription());
        }
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = epicMap.remove(id);
        if (epic != null) {
            historyManager.remove(id);
            for (Subtask subtask : epic.getSubtaskList()) {
                subtaskMap.remove(subtask.getId());
                historyManager.remove(subtask.getId());
            }
        }
    }

    @Override
    public List<Subtask> getEpicSubtasks(int epicId) {
        Epic epic = epicMap.get(epicId);
        return epic != null ? epic.getSubtaskList() : new ArrayList<>();
    }

    // data.model.Subtask
    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    @Override
    public void clearAllSubtasks() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.clearSubtaskList();
        }
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtaskMap.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
    public void addSubtask(Subtask subtask) {
        Epic epic = epicMap.get(subtask.getEpicId());
        if (epic != null) {
            subtask.setId(++id);
            subtaskMap.put(subtask.getId(), subtask);
            epic.addSubtask(subtask);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtaskMap.containsKey(subtask.getId())) {
            Epic epic = epicMap.get(subtask.getEpicId());
            if (epic != null) {
                subtaskMap.put(subtask.getId(), subtask);
                epic.updateSubtask(subtask);
            }
        }
    }

    @Override
    public void removeSubtaskById(int id) {
        Subtask subtask = subtaskMap.remove(id);
        if (subtask != null) {
            historyManager.remove(id);
            Epic epic = epicMap.get(subtask.getEpicId());
            if (epic != null) {
                epic.removeSubtask(subtask);
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}