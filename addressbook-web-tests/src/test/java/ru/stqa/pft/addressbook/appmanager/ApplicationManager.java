package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private WebDriver wd;
  private GroupHelper groupHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  //Инициализация драйверов и переменных для запуска тестов
  public void init() {
    //Выбираем какой браузер использовть в тестах
    if(browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public void logOut() {
    wd.findElement(By.linkText("Logout")).click();
  }

  //Возвращаем хелперы для использования их методов
  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper(){
    return navigationHelper;
  }

  public ContactHelper getContactHelper(){
    return contactHelper;
  }

  public SessionHelper getSessionHelper(){
    return sessionHelper;
  }
  //

  //Для закрытия диалогового окна (alert), которое появляется при удалении контакта, нужно использовать такую команду драйвера:
  public void alertAccept(){
    wd.switchTo().alert().accept();
  }

}
