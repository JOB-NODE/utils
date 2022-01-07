package com.jobnode.utils;

import java.util.stream.Stream;

/**
 * A Process
 * @param <T> type of process activities
 */
public interface IProcess<T extends IActivity> extends IActivity {
  /**
   * @return number of activities in the process
   */
  int size();

  /**
   * Stream process activities
   * @return Stream of activities in the process
   */
  Stream<T> stream();
}
