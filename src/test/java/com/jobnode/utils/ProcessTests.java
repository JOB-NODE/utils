package com.jobnode.utils;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessTests {

  @Test
  public void oneActivity() {
    Process<SimpleActivity> process = new Process<>("process");
    assertEquals("process", process.getName());
    SimpleActivity activity1 = new SimpleActivity("activity-1"),
            activity2 = new SimpleActivity("activity-2");

    assertEquals(0, process.size(), "Process is empty");
    process.add(activity1);
    assertEquals(1, process.size(), "Process has one activity");
    process.remove(activity1);
    assertEquals(0, process.size(), "Process is empty again");
  }

  @Test
  public void swapActivities() {
    Process<SimpleActivity> process = new Process<>("process");
    SimpleActivity activity1 = new SimpleActivity("1"),
            activity2 = new SimpleActivity("2");

    process.add(activity1);
    process.add(activity2);
    assertEquals(2, process.size(), "Process has two activities");

    assertEquals("12", process.stream().map(IActivity::getName).collect(Collectors.joining()));

    process.swap(activity1, activity2);
    assertEquals("21", process.stream().map(IActivity::getName).collect(Collectors.joining()));

    process.remove(activity1);
    process.remove(activity2);
    assertEquals(0, process.size(), "Process is empty again");
  }
}