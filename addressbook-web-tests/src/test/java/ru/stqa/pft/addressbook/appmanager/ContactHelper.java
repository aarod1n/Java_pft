package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contact) {
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("address"), contact.getAddress());
    type(By.name("mobile"), contact.getMobile());
    type(By.name("email"), contact.getEMail());
  }

  public void selectContact(int index) {
    click(By.xpath("(//input[@name='selected[]'])[" + index + "]"));
  }

  public void deleteSelectContact() {
    click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContactForEdit(int index) {
    List<WebElement> elements = wd.findElements(By.cssSelector("a[href^='edit.php?']"));
    elements.get(index).click();
  }

  public void submitContactUpdate() {
    click(By.cssSelector("[value='Update']"));
  }
}
