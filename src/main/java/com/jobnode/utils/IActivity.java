package com.jobnode.utils;

/**
 * An activity in a Process
 *
 * @see Process
 */
public interface IActivity {
  /**
   * returns activity name, meant to be used as activity unique name in a process
   *
   * @return activity name
   */
  String getName();
}
