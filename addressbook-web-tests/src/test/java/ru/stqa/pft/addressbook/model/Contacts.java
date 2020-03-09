package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contact) {
    this.delegate = new HashSet<ContactData>(contact.delegate);
  }

  public Contacts(){
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection<ContactData> contacrs) {
    this.delegate = new HashSet<ContactData>(contacrs);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAddet(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts withOut(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
