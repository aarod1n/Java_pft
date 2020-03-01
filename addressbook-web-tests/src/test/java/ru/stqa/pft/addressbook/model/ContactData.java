package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String eMail;
  private final String lastName;
  private final String address;
  private final String mobile;
  private final String group;

  public ContactData(String firstName, String lastName) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.eMail = null;
    this.lastName = lastName;
    this.address = null;
    this.mobile = null;
    this.group = null;
  }

  public ContactData(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.eMail = null;
    this.lastName = lastName;
    this.address = null;
    this.mobile = null;
    this.group = null;
  }

  public ContactData(int id, String group, String firstName, String eMail, String lastName, String address, String mobile) {
    this.id = id;
    this.firstName = firstName;
    this.eMail = eMail;
    this.lastName = lastName;
    this.address = address;
    this.mobile = mobile;
    this.group = group;
  }

  public ContactData(String group, String firstName, String eMail, String lastName, String address, String mobile) {
    this.id = Integer.MAX_VALUE;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
