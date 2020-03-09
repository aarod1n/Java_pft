package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(eMail, that.eMail) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(address, that.address) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(homePhone, that.homePhone);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", eMail='" + eMail + '\'' +
            ", eMail2='" + eMail2 + '\'' +
            ", eMail3='" + eMail3 + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", homePhone='" + homePhone + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, eMail, lastName, address, mobilePhone, workPhone, homePhone);
  }

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "email")
  @Type(type = "text")
  private String eMail;

  //Пропустить, не извлекать данное поле из БД
  @Transient
  private String eMail2;

  @Transient
  private String eMail3;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

 @Transient
  private String group;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Transient
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Transient
  private String allPhones;

  @Transient
  private String allEmail;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;


  public File getPhoto(){
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

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

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
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

}
