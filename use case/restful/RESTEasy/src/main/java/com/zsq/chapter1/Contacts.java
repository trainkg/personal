package com.zsq.chapter1;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Contacts 
{
   
   private Collection<Contact> contacts;

   public Contacts()
   {
      this.contacts = new ArrayList<Contact>();
   }

   public Contacts(Collection<Contact> contacts)
   {
      this.contacts = contacts;
   }

   @XmlElement(name="contact")
   public Collection<Contact> getContacts()
   {
      return contacts;
   }

   public void setContacts(Collection<Contact> contact)
   {
      this.contacts = contact;
   }

   
   
}
