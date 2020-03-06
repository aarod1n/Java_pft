package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  private Groups groupCache = null;

  public void create(GroupData group){
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
  }

  public void modification(GroupData group, int index){
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    initUpdateGroup();
    groupCache = null;
  }

  public void deletionById(GroupData group){
    selectGroupById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
  }

  public void modificationById(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    initUpdateGroup();
    groupCache = null;
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  /*
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
  */

  public Groups all(){
    if(groupCache != null){
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Пробегаем по коллекции elements
    for(WebElement element : elements){
      String text = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(text);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }
}
