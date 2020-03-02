package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

import java.util.ArrayList;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void create(GroupData group){
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
  }

  public void modification(GroupData group, int index){
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    initUpdateGroup();
  }

  public void deletion(int index){
    selectGroup(index);
    deleteSelectedGroup();
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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedGroup() {
    click(By.xpath("//*[@id='content']/form/input[2]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void initUpdateGroup() {
    click(By.name("update"));
  }


  public boolean isThereAGroup(){
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list(){

    //List - интерфейс, ArrayList - класс реализующий данный интерфейс.
    //Поэтому можем создать List ссылающийся на объекты ArrayList
    List<GroupData> groups = new ArrayList<GroupData>();
    //Список веб элементов всех групп
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

    //Пробегаем по коллекции elements
    for(WebElement element : elements){
      String text = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(text);
      groups.add(group);
    }
    return groups;
  }
}
