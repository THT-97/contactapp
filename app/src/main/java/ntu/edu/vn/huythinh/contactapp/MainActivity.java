package ntu.edu.vn.huythinh.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntu.edu.vn.huythinh.controller.IContactController;
import ntu.edu.vn.huythinh.model.Contact;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvContacts;
    RecyclerView.Adapter adapter;
    List<Contact> contactList;
    IContactController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = (IContactController) getApplication();
        addViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnuAdd: addContact();
            case R.id.mnuExit: finish();
        }
        return false;
    }

    protected void addContact(){
        controller.setCurrent(contactList.size());
        Intent intentAdd = new Intent(this, ContactActivity.class);
        startActivity(intentAdd);
    }

    protected void addViews(){
        rvContacts = findViewById(R.id.rvContacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        IContactController controller = (IContactController) getApplication();
        contactList = controller.getContacts();
        adapter = new ContactAdapter(contactList);
        rvContacts.setAdapter(adapter);
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtDob, txtNumber;
        ImageView imgedit;
        Contact contact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtname);
            txtDob = this.itemView.findViewById(R.id.txtdob);
            txtNumber = this.itemView.findViewById(R.id.txtnumber);
            imgedit = this.itemView.findViewById(R.id.imgedit);
            imgedit.setOnClickListener(this);
        }


        public void bind(Contact contact){
            this.contact = contact;
            txtName.setText(contact.getName());
            txtDob.setText(contact.getDob());
            txtNumber.setText(contact.getNumber());
        }

        @Override
        public void onClick(View v) {
            controller.setCurrent(contactList.indexOf(contact));
            Intent intentView = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intentView);
        }
    }
    private class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{
        List<Contact> contactList;

        public ContactAdapter(List<Contact> contactList) {
            this.contactList = contactList;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.contacts, parent, false);
            return new ContactViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            holder.bind(contactList.get(position));
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }
    }
}
