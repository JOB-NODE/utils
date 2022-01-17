package com.jobnode.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Process progress implementation, using a Map to store current progresses
 * @param <T> type of activity
 */
public class ProcessProgress<T extends IActivity> implements IProcessProgress<T> {
  private final Process<T> process;
  private final Map<String, IProgress> progressMap;

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
   * @return stream of the progresses collected
   */
  @Override
  public Stream<IProgress> stream() {
    return progressMap.values().stream();
  }

  /**
   * @return the process of this progress
   */
  @Override
  public IProcess<T> getProcess() {
    return process;
  }
}
