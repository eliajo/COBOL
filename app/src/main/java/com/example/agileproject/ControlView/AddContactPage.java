package com.example.agileproject.ControlView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agileproject.R;

import java.util.ArrayList;
import java.util.List;

public class AddContactPage extends Fragment {

    NavController navController;

    public AddContactPage() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);

        RecyclerView contactRecycleView = v.findViewById(R.id.ContactRecycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        contactRecycleView.setLayoutManager(layoutManager);

        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact("Klara", "0735241742"));

        Button add = v.findViewById(R.id.addContactButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
            public void switchContactPage() {
                //  Intent intent = new Intent(this, ContactAdapter.class);
                //startActivity(intent);
            }
        });

        return v;
    }

    public void addContact(View view) {
        EditText etContactName = view.findViewById(R.id.etContactName);
        EditText etContactNumber = view.findViewById(R.id.etContactNumber );
        String name = etContactName.getText().toString() ;
        String phone = etContactNumber.getText().toString() ;
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION ) ;
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE ) ;
        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, name)
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone) ;
        startActivityForResult(contactIntent, 1 ) ;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.addContactButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  navController.navigate(R.id.switch_contact_page);
            }
        });
    }
}
