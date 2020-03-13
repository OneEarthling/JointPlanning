package com.example.jointplanning;

import com.example.jointplanning.model.TaskOld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskLab {
    private static TaskLab sTaskLab;

    private List<TaskOld> mTasks;

    public static TaskLab get() {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab();
        }

        return sTaskLab;
    }

    private TaskLab() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TaskOld task = TaskOld.getInstance(new Date().getTime(), "Задача #" + i, i % 2 == 0? 1:4, i % 2 == 0? 5:13);
            mTasks.add(task);
        }
    }

    public List<TaskOld> getTasks() {
        return mTasks;
    }

    public TaskOld getTask(long id) {
        for (TaskOld task : mTasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}
