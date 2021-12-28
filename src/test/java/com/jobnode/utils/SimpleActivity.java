package com.jobnode.utils;

class SimpleActivity implements IActivity {
  final private String name;

  public SimpleActivity(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
