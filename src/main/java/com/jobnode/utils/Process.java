package com.jobnode.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * A Basic implementation of a Process
 * @param <T> type of the activity
 */
public class Process<T extends IActivity> implements IProcess<T> {
  private final List<T> activities;
  private final String name;

  /**
   * Construct a process with a unique name and initialize it with certain activities
   *
   * @param name       process name
   * @param activities initial activities
   */
  public Process(String name, Collection<T> activities) {
    this.name = name;
    this.activities = new ArrayList<>(activities);
  }

  public Process(String name) {
    this(name, List.of());
  }

  /**
   * @return process name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Add an activity to the process
   *
   * @param activity activity to add
   * @return true if added
   */
  public boolean add(T activity) {
    return activities.add(activity);
  }

  /**
   * Remove an activity from the process
   *
   * @param activity activity to remove
   * @return true if removed
   */
  public boolean remove(T activity) {
    return activities.remove(activity);
  }

  /**
   * Swap activities order
   *
   * @param first first activity
   * @param last  last activity
   * @return true if operation succeeded
   */
  public boolean swap(T first, T last) {
    int firstPosition = activities.indexOf(first);
    int lastPosition = activities.indexOf(last);

    if (firstPosition == -1 || lastPosition == -1)
      return false;

    activities.set(firstPosition, last);
    activities.set(lastPosition, first);
    return true;
  }

  @Override
  public int size() {
    return activities.size();
  }

  @Override
  public Stream<T> stream() {
    return activities.stream();
  }
}
