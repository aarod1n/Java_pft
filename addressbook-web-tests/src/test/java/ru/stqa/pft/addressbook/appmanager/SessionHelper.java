package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{

  private WebDriver wd;

  public SessionHelper(WebDriver wd){
    super(wd);
  }

  public void login(String username, String password) {
    type(By.name("user"), username);
    type(By.name("pass"), password);
    click(By.cssSelector("#LoginForm > input[type=submit]:nth-child(7)"));
  }

}
