package com.jobnode.utils;

/**
 * Progress of an activity
 *
 * @see IActivity
 */
public interface IProgress {
  /**
   * @return progress's activity
   */
  IActivity getActivity();

  /**
   * @return the progress of the activity
   */
  double getProgress();
}
