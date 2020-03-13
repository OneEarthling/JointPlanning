package com.example.jointplanning.model;

public class TaskOld {

    public static TaskOld getInstance(long id, String text, int priority, int estimate) {
        return new TaskOld(id, text, priority, estimate);
    }

    /**
     * Идентификатор
     */
    private long mId;

    /**
     * Текст
     */
    private String mText;

    /**
     * приоритет
     */
    private int mPriority;

    /**
     * оценка
     */
    private int mEstimate;

    private TaskOld(long id, String text, int priority, int estimate) {
        mId = id;
        mText = text;
        mPriority = priority;
        mEstimate = estimate;
    }

    public String getText() {
        return mText;
    }

    public int getPriority() {
        return mPriority;
    }

    public int getEstimate() {
        return mEstimate;
    }

    public void setEstimate(int estimate) {
        mEstimate = estimate;
    }

    public long getId() {
        return mId;
    }
}
