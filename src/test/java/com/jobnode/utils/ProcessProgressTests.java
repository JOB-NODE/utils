package com.jobnode.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessProgressTests {
  @Test
  @DisplayName("Progress of a process with one basic activity")
  void basicProgress() {
    SimpleActivity activity = new SimpleActivity("1");
    BasicProgress<SimpleActivity> activityBasicProgress = new BasicProgress<>(activity, false);

    Process<SimpleActivity> process = new Process<>("process", List.of(activity));

    ProcessProgress<SimpleActivity> progress = new ProcessProgress<>(process);

    assertEquals(0, progress.getProgress(), "Empty ProcessProgress's progress is 0");
    assertFalse(activityBasicProgress.isDone(), "Activity isn't done");

    progress.addProgress(activityBasicProgress);

    assertEquals(0, progress.getProgress(), "No activity is done yet");

    activityBasicProgress.setDone(true);
    assertTrue(activityBasicProgress.isDone(), "Activity is done");

    assertEquals(1, progress.getProgress(), "Process is done");
  }

  @Test
  @DisplayName("Progress of a process with two basic activities")
  void twoActivities() {
    SimpleActivity activity1 = new SimpleActivity("1"),
            activity2 = new SimpleActivity("2");
    BasicProgress<SimpleActivity> activityProgress1 = new BasicProgress<>(activity1, true),
            activityProgress2 = new BasicProgress<>(activity2, false);

    Process<SimpleActivity> process = new Process<>("process", List.of(activity1, activity2));
    ProcessProgress<SimpleActivity> progress = new ProcessProgress<>(process);
    progress.addProgress(activityProgress1);
    assertEquals(0.5, progress.getProgress(), "Half the process is done");
    progress.addProgress(activityProgress2);
    assertEquals(0.5, progress.getProgress(), "Half the process is still undone");

    activityProgress2.setDone(true);
    assertEquals(1, progress.getProgress(), "Process is done");
  }

  @Test
  @DisplayName("Progress of of nested process (process of processes)")
  void nestedProcess() {
    // activities
    List<SimpleActivity> activities = Stream.of("1", "2", "3", "4").map(SimpleActivity::new).collect(Collectors.toList());
    List<BasicProgress<SimpleActivity>> activitiesProgress = activities.stream().map(BasicProgress::new).collect(Collectors.toList());

    // sub processes
    Process<SimpleActivity> subProcess1 = new Process<>("1", activities.subList(0, 2)),
            subProcess2 = new Process<>("2", activities.subList(2, 4));
    ProcessProgress<SimpleActivity> subProgress1 = new ProcessProgress<>(subProcess1, activitiesProgress.subList(0, 2)),
            subProgress2 = new ProcessProgress<>(subProcess2, activitiesProgress.subList(2, 4));

    // process
    Process<IActivity> process = new Process<>("process", List.of(subProcess1, subProcess2));
    ProcessProgress<IActivity> progress = new ProcessProgress<>(process, List.of(subProgress1, subProgress2));

    assertEquals(0, progress.getProgress(), "No activity is done yet");

    // first activity is done
    activitiesProgress.get(0).setDone(true);

    assertEquals(0.25, progress.getProgress(), "1 activity is done out of 4");

    // first sub process is done
    activitiesProgress.get(1).setDone(true);

    assertEquals(0.5, progress.getProgress(), "1 sub process is done out of 2");

    // third activity is done
    activitiesProgress.get(2).setDone(true);

    assertEquals(0.75, progress.getProgress(), "3 activities are done out of 4");

    // all sub processes are done
    activitiesProgress.get(3).setDone(true);

    assertEquals(1, progress.getProgress(), "all activities are done");

  }
}
