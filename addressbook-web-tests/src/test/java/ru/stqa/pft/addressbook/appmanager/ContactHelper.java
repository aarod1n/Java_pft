package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
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

  private Contacts contactsCache = null;

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contact, boolean creation) {
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("address"), contact.getAddress());
    type(By.name("mobile"), contact.getMobilePhone());
    type(By.name("email"), contact.getEMail());
    attache(By.name("photo"), contact.getPhoto());

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
    contactsCache = null;
  }

  public void contactModification(ContactData contact, int index, boolean creation) {
    selectContactForEdit(index);
    fillContactForm(contact, false);
    submitContactUpdate();
    contactsCache = null;
  }

  public void contactModificationById(ContactData contact, int id, boolean creation) {
    selectContactForEditById(id);
    fillContactForm(contact, false);
    submitContactUpdate();
    contactsCache = null;
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
    contactsCache = null;
  }

  public void contactDeletionById(int id) {
    selectContactById(id);
    deleteSelectContact();
    contactsCache = null;
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

  //Получаем элементы из таблицы, парсим по нужным полям.
  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      List<WebElement> cell = element.findElements(By.tagName("td"));
      String name = cell.get(2).getText();
      String lastName = cell.get(1).getText();
      String address = cell.get(3).getText();
      String allEmail = cell.get(4).getText();
      String allPhone = cell.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName)
              .withAllPhone(allPhone).withAllEmail(allEmail).withAddress(address);
      contactsCache.add(contact);
    }
    return new Contacts(contactsCache);
  }

  public int count() {
    return wd.findElements(By.cssSelector("tr[name=entry]")).size();
  }

  public ContactData getInfoForEditForm(int id) {
    selectContactForEditById(id);
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String eMail = wd.findElement(By.name("email")).getAttribute("value");
    String eMail2 = wd.findElement(By.name("email2")).getAttribute("value");
    String eMail3 = wd.findElement(By.name("email3")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");

    return new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address)
            .withEMail(eMail).withEMail2(eMail2).withEMail3(eMail3)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
  }


}
