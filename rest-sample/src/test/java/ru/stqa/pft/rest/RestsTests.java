package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import org.apache.http.message.BasicNameValuePair;


import static org.testng.Assert.assertEquals;

public class RestsTests {

  @Test
  public void createIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue 333333").withDescription("New test issue 333333");
    int issueId = createIssue(newIssue);
    oldIssues.add(newIssue.withId(issueId));
    Set<Issue> newIssues = getIssues();
    assertEquals(newIssues, oldIssues);
  }

  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=1000")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json?limit=1000")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public static Executor getExecutor(){
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }
}