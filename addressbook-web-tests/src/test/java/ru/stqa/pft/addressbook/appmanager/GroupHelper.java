package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd){
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void selectGroup(){
    click(By.xpath(("//*[@id=\"content\"]/form/span[1]/input")));
  }

  public void deleteSelectedGroup(){
    click(By.xpath("//*[@id='content']/form/input[2]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void initUpdateGroup() {
    click(By.name("update"));
  }

}