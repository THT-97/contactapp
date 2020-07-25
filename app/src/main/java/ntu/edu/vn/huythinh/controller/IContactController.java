package ntu.edu.vn.huythinh.controller;

import java.util.List;

import ntu.edu.vn.huythinh.model.Contact;

public interface IContactController {
    public List<Contact> getContacts();
    public boolean addContact(Contact contact);
    public boolean setCurrent(Integer index);
    public int getCurrent();
}
