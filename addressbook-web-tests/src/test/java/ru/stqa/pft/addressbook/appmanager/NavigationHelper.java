package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase {

  private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.xpath("\"//*[@id=\\\"content\\\"]/h1\""))
            && wd.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText().equals("Groups")) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void newContact() {
    if (isElementPresent(By.xpath("\"//*[@id=\\\"content\\\"]/h1\""))
            && wd.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText().equals("Edit / add address book entry")) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void editForms(int id){
    click(By.cssSelector("a[href='edit.php?id=" + id));
  }

}
