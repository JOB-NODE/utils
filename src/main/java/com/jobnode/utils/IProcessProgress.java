package com.jobnode.utils;

import java.util.stream.Stream;

/**
 * Process Progress with default implementation to calculate progress
 * @param <T> type of activity in the process
 */
public interface IProcessProgress<T extends IActivity> extends IProgress {
  /**
   * Stream progresses of the process, leaving the storage implementation details to the user
   * @return stream of available progresses
   */
  Stream<IProgress> stream();

  /**
   * Get the process of this progress
   * @return Process of the progress
   */
  IProcess<T> getProcess();

  /**
   * @return the activity of the progress, which is the actual process
   */
  @Override
  default IActivity getActivity(){
    return getProcess();
  }

  /**
   * calculates the progress from progresses stream
   *
   * @return the calculated progress
   */
  @Override
  default double getProgress(){
    return stream()
            .mapToDouble(IProgress::getProgress)
            .sum()/getProcess().size();
  }
}
