package data.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtaskList;

    public Epic(String name, String description, Status status) {
        super(name, description, status);
        this.subtaskList = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        if (subtask.getEpicId() == this.id) {
            subtaskList.add(subtask);
            updateStatus();
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtask.getEpicId() == this.id && subtaskList.contains(subtask)) {
            int index = subtaskList.indexOf(subtask);
            subtaskList.set(index, subtask);
            updateStatus();
        }
    }

    public void removeSubtask(Subtask subtask) {
        if (subtaskList.remove(subtask)) {
            updateStatus();
        }
    }

    public void clearSubtaskList() {
        subtaskList.clear();
        updateStatus();
    }

    public List<Subtask> getSubtaskList() {
        return new ArrayList<>(subtaskList);
    }

    private void updateStatus() {
        if (subtaskList.isEmpty()) {
            status = Status.NEW;
            return;
        }

        int newCount = 0;
        int doneCount = 0;

        for (Subtask subtask : subtaskList) {
            if (subtask.getStatus() == Status.NEW) newCount++;
            else if (subtask.getStatus() == Status.DONE) doneCount++;
        }

        if (newCount == subtaskList.size()) {
            status = Status.NEW;
        } else if (doneCount == subtaskList.size()) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }
    }

    @Override
    public String toString() {
        return "data.model.Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtaskList=" + subtaskList +
                '}';
    }
}