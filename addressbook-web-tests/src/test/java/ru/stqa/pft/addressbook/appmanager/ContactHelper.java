package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("address"), contact.getAddress());
    type(By.name("mobile"), contact.getMobile());
    type(By.name("email"), contact.getEMail());

    if (creation) {
      //Работа с данными в выпадающем списке
      Select select = new Select(wd.findElement(By.name("new_group")));
      List<WebElement> list = select.getOptions();

      //Если группы есть и есть равная переданной, берем ее
      //Если группы есть, но нету переданной, берем первую существующую
      //Если групп нет, ставим [none]
      if (list.size() > 1) {
        boolean itemFound = false;
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getText().equals(contact.getGroup())) {
            select.selectByVisibleText(contact.getGroup());
            itemFound = true;
            break;
          }
        }
        if (!itemFound)
          select.selectByVisibleText(list.get(1).getText());
      } else {
        select.selectByVisibleText(select.getFirstSelectedOption().getText());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact(int index) {
    index++;
    click(By.xpath("(//input[@name='selected[]'])[" + index + "]"));
  }

  public void deleteSelectContact() {
    click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactUpdate() {
    click(By.cssSelector("[value='Update']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void creation(ContactData contact, boolean creation) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void contactModification(ContactData contact, int index, boolean creation) {
    selectContactForEdit(index);
    fillContactForm(contact, false);
    submitContactUpdate();
  }

  public void contactModificationById(ContactData contact, int id, boolean creation) {
    selectContactForEditById(id);
    fillContactForm(contact, false);
    submitContactUpdate();
  }

  public void selectContactForEdit(int index) {
    List<WebElement> elements = wd.findElements(By.cssSelector("a[href^='edit.php?']"));
    elements.get(index).click();
  }

  private void selectContactForEditById(int id) {
    click(By.cssSelector("a[href='edit.php?id=" + id));
  }


  public void contactDeletion(int index) {
    selectContact(index);
    deleteSelectContact();
  }

  public void contactDeletionById(int id) {
    selectContactById(id);
    deleteSelectContact();
  }

  private void selectContactById(int id) {
    click(By.cssSelector("input[value='" + id + "']"));
  }

  public List<ContactData> getContactList() {

    //List - интерфейс, ArrayList - класс реализующий данный интерфейс.
    //Поэтому можем создать List ссылающийся на объекты ArrayList
    List<ContactData> contacts = new ArrayList<ContactData>();
    //Создаем список из строк контактов
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));

    //Парсим строки контактов на поля
    for (WebElement element : elements) {
      List<WebElement> cell = element.findElements(By.tagName("td"));
      //Из полей забераем имя и фамилию
      String name = cell.get(2).getText();
      String lastName = cell.get(1).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //Создаем контакт и кладем его в список
      contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
    }
    return contacts;
  }

  public Contacts all() {

    Contacts contacts = new Contacts();
    //Создаем список из строк контактов
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));

    //Парсим строки контактов на поля
    for (WebElement element : elements) {
      List<WebElement> cell = element.findElements(By.tagName("td"));
      //Из полей забераем имя и фамилию
      String name = cell.get(2).getText();
      String lastName = cell.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //Создаем контакт и кладем его в список
      ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName);
      contacts.add(contact);
      //contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
    }
    return contacts;
  }

}
