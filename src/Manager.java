import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private int id;
    private Map<Integer, Task> taskMap;
    private Map<Integer, Subtask> subtaskMap;
    private Map<Integer, Epic> epicMap;

    public Manager() {
        this.id = 0;
        this.taskMap = new HashMap<>();
        this.subtaskMap = new HashMap<>();
        this.epicMap = new HashMap<>();
    }

    // Task
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public void clearAllTasks() {
        taskMap.clear();
    }

    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    public void addTask(Task task) {
        task.setId(++id);
        taskMap.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
        }
    }

    public void removeTaskById(int id) {
        taskMap.remove(id);
    }

    // Epic
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicMap.values());
    }

    public void clearAllEpics() {
        subtaskMap.clear();
        epicMap.clear();
    }

    public Epic getEpicById(int id) {
        return epicMap.get(id);
    }

    public void addEpic(Epic epic) {
        epic.setId(++id);
        epicMap.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        if (epicMap.containsKey(epic.getId())) {
            Epic savedEpic = epicMap.get(epic.getId());
            savedEpic.setName(epic.getName());
            savedEpic.setDescription(epic.getDescription());
        }
    }

    public void removeEpicById(int id) {
        Epic epic = epicMap.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtaskList()) {
                subtaskMap.remove(subtask.getId());
            }
        }
    }

    public List<Subtask> getEpicSubtasks(int epicId) {
        Epic epic = epicMap.get(epicId);
        return epic != null ? epic.getSubtaskList() : new ArrayList<>();
    }

    // Subtask
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    public void clearAllSubtasks() {
        subtaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.clearSubtaskList();
        }
    }

    public Subtask getSubtaskById(int id) {
        return subtaskMap.get(id);
    }

    public void addSubtask(Subtask subtask) {
        Epic epic = epicMap.get(subtask.getEpicId());
        if (epic != null) {
            subtask.setId(++id);
            subtaskMap.put(subtask.getId(), subtask);
            epic.addSubtask(subtask);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtaskMap.containsKey(subtask.getId())) {
            Epic epic = epicMap.get(subtask.getEpicId());
            if (epic != null) {
                subtaskMap.put(subtask.getId(), subtask);
                epic.updateSubtask(subtask);
            }
        }
    }

    public void removeSubtaskById(int id) {
        Subtask subtask = subtaskMap.remove(id);
        if (subtask != null) {
            Epic epic = epicMap.get(subtask.getEpicId());
            if (epic != null) {
                epic.removeSubtask(subtask);
            }
        }
    }
}