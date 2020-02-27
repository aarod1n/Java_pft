package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String eMail;
  private final String lastName;
  private final String address;
  private final String mobile;
  private final String group;


  public ContactData(String group, String firstName, String eMail, String lastName, String address, String mobile ) {
    this.firstName = firstName;
    this.eMail = eMail;
    this.lastName = lastName;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getEMail() {
    return eMail;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }
}
