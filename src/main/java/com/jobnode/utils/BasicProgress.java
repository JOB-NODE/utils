package com.jobnode.utils;

/**
 * Basic progress of an activity, either done or undone
 *
 * @param <T> an activity type
 */
public class BasicProgress<T extends IActivity> implements IProgress {
  final private T activity;
  private boolean isDone;

  public BasicProgress(T activity, boolean isDone) {
    this.activity = activity;
    this.isDone = isDone;
  }

  public BasicProgress(T activity) {
    this(activity, false);
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean done) {
    isDone = done;
  }

  @Override
  public IActivity getActivity() {
    return activity;
  }

  @Override
  public double getProgress() {
    return isDone ? 1 : 0;
  }
}
