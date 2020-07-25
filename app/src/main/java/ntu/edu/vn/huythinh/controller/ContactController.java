package ntu.edu.vn.huythinh.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ntu.edu.vn.huythinh.model.Contact;

public class ContactController extends Application implements IContactController {
    List<Contact> contactList;
    int current;
    public ContactController() {
        contactList = new ArrayList<>();
        contactList.add(new Contact("Thu Cuc", "14/2/1990","090123456","Ha Noi"));
        contactList.add(new Contact("Thu Du", "8/3/1991","090123456","Nha Trang"));
        contactList.add(new Contact("Thu Tien", "30/4/1990","090123789","HCM"));
        contactList.add(new Contact("Minh Thao", "08/03/1999","123456789","Da Nang"));
    }

    @Override
    public List<Contact> getContacts() { return contactList; }

    @Override
    public boolean addContact(Contact contact) {
        if(contactList.contains(contact)) return false;
        contactList.add(contact);
        return true;
    }

    @Override
    public boolean setCurrent(Integer index) {
        current = index;
        return current>=0?true:false;
    }

    @Override
    public int getCurrent() {
        return current;
    }
}
