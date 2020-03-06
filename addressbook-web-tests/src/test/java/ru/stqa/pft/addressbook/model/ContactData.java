package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData extends Contacts {

  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String eMail;
  private String eMail2;
  private String eMail3;
  private String lastName;
  private String address;
  private String group;
  private String mobilePhone;
  private String workPhone;
  private String homePhone;
  private String allPhones;
  private String allEmail;

  public String getAllEmail() {
    return allEmail;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getEMail() {
    return eMail;
  }

  public String getEMail2() {
    return eMail2;
  }

  public String getEMail3() {
    return eMail3;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withEMail(String eMail) {
    this.eMail = eMail;
    return this;
  }

  public ContactData withEMail2(String eMail) {
    this.eMail2 = eMail;
    return this;
  }

  public ContactData withEMail3(String eMail) {
    this.eMail3 = eMail;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhone(String allPhone) {
    this.allPhones = allPhone;
    return this;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", homePhone='" + homePhone + '\'' +
            '}';
  }

}
