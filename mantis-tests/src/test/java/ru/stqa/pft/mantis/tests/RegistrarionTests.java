package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrarionTests extends TestBase {

  @Test
  public void registrationTest() {
    app.registration().start("user1", "user1@localhost.localdomian");
  }
}
