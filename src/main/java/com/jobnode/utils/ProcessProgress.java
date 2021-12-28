package com.jobnode.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Process progress, calculated from the progress of each activity
 *
 * @param <T> type of activity
 */
public class ProcessProgress<T extends IActivity> implements IProgress {
  final private Process<T> process;
  final private Map<String, IProgress> progressMap;

  /**
   * Construct ProcessProgress from a given process, which cannot be changed after
   *
   * @param process the process
   */
  public ProcessProgress(Process<T> process, Collection<? extends IProgress> progresses) {
    this.process = process;
    this.progressMap = new HashMap<>();
    progresses.forEach(this::addProgress);
  }

  public ProcessProgress(Process<T> process) {
    this(process, List.of());
  }

  /**
   * Add the progress of an activity
   *
   * @param progress progress of an activity in the process
   */
  public void addProgress(IProgress progress) {
    this.progressMap.put(progress.getActivity().getName(), progress);
  }

  /**
   * @return the activity of the progress, which is the actual process
   */
  @Override
  public IActivity getActivity() {
    return process;
  }

  /**
   * calculates the progress from activities progress map
   *
   * @return the calculated progress
   */
  @Override
  public double getProgress() {
    final double ratio = 1.0 / process.size();
    return process.stream()
            .map(IActivity::getName)
            .filter(progressMap::containsKey)
            .mapToDouble(activity -> progressMap.get(activity).getProgress() * ratio)
            .sum();
  }
}
