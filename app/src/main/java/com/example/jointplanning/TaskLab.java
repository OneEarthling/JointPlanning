package com.example.jointplanning;

import com.example.jointplanning.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskLab {
    private static TaskLab sTaskLab;

    private List<Task> mTasks;

    public static TaskLab get() {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab();
        }

        return sTaskLab;
    }

    private TaskLab() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task task = Task.getInstance(new Date().getTime(), "Задача #" + i, i % 2 == 0? 1:4, i % 2 == 0? 5:13);
            mTasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public Task getTask(long id) {
        for (Task task : mTasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}
