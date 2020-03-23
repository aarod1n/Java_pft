package ru.stqa.pft.rest;


import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class RestsTests extends TestBase {

  @Test
  public void createIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue 333333").withDescription("New test issue 333333");
    int issueId = createIssue(newIssue);
    oldIssues.add(newIssue.withId(issueId));
    Set<Issue> newIssues = getIssues();
    assertEquals(newIssues, oldIssues);
  }
}
