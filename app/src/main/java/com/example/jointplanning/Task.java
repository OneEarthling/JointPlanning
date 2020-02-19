package com.example.jointplanning;

import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTextTask;
    private int mPriority;
    private int mEstimate;

    public Task(){
        mId = UUID.randomUUID();
    }
    public String getTextTask() {
        return mTextTask;
    }

    public void setTextTask(String textTask) {
        mTextTask = textTask;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public int getEstimate() {
        return mEstimate;
    }

    public void setEstimate(int estimate) {
        mEstimate = estimate;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

}
