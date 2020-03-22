package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;
import java.io.IOException;


public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("bac05c09197251e6f4050fd7c33c9584a5e81d27");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("aarod1n", "Java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
