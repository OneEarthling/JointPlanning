package com.example.jointplanning;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab sTaskLab;

    private List<Task> mTasks;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }

        return sTaskLab;
    }

    private TaskLab(Context context) {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setTextTask("Задача #" + i);
            task.setPriority(i % 2 == 0? 1:4);
            task.setEstimate(i % 2 == 0? 5:13);

            mTasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public Task getTask(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }
}