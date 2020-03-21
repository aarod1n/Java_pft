package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {

  private int id;
  private String subject;
  private String description;
  private String stateName;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String sabject) {
    this.subject = sabject;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String discription) {
    this.description = discription;
    return this;
  }

  public String getStateName() {
    return stateName;
  }

  public Issue withStateName(String state_name) {
    this.stateName = state_name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return id == issue.id &&
            Objects.equals(subject, issue.subject) &&
            Objects.equals(description, issue.description) &&
            Objects.equals(stateName, issue.stateName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, description, stateName);
  }

}
