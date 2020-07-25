package ntu.edu.vn.huythinh.contactapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ntu.edu.vn.huythinh.controller.IContactController;
import ntu.edu.vn.huythinh.model.Contact;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtID, edtName, edtNumber, edtDob, edtFrom;
    Button btnConfirm, btnCancel;
    List<Contact> contactList;
    Contact contact;
    IContactController controller;
    int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        controller = (IContactController) getApplication();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addviews();
    }

    protected void addviews(){
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtDob = findViewById(R.id.edtDob);
        edtNumber = findViewById(R.id.edtNumber);
        edtFrom = findViewById(R.id.edtFrom);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        showContact();
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    protected void showContact(){
        contactList = controller.getContacts();
        current = controller.getCurrent();
        if(current<contactList.size()){
            contact = contactList.get(current);
            String strID;
            if(current<10) strID = "0" + (current+1);
            else strID = Integer.toString(current);
            edtID.setText(strID);
            edtName.setText(contact.getName());
            edtDob.setText(contact.getDob());
            edtNumber.setText(contact.getNumber());
            edtFrom.setText(contact.getFrom());
        }
        else addContact();
    }
    private void addContact(){
        contact = new Contact();
        controller.addContact(contact);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnConfirm: saveChanges();
            case R.id.btnCancel: finish();
        }
    }

    private void saveChanges(){
        contact.setName(edtName.getText().toString());
        contact.setDob(edtDob.getText().toString());
        contact.setNumber(edtNumber.getText().toString());
        contact.setFrom(edtFrom.getText().toString());
        contactList.set(current, contact);
    }
}
