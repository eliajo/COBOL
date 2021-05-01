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

import com.example.agileproject.Model.Storable;
import com.example.agileproject.R;
import com.example.agileproject.Utils.ContactConverter;
import com.example.agileproject.Utils.FileFormatter;
import com.example.agileproject.Utils.FileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the view for adding a contacts
 * @author Elias Johansson ...
 */
public class AddContactPage extends Fragment {

    NavController navController;

    private EditText etContactName;

    private EditText etContactNumber;

    public AddContactPage() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        navController=Navigation.findNavController(this.getActivity(), R.id.main_pages_fragment);
         etContactName = v.findViewById(R.id.etContactName);
         etContactNumber = v.findViewById(R.id.etContactNumber );
        Button add = v.findViewById(R.id.addContactButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gets the info from inputboxes
                String name = etContactName.getText().toString();
                String phoneNumber = etContactNumber.getText().toString();

                Contact contact = new Contact(name,phoneNumber);
                FileFormatter fileFormatter = new FileFormatter();

                //Hack that is going to be changed. No use having a list for one contact.
                //Converts the contact into a saveable string
                List<Storable> storableList = new ArrayList<>();
                storableList.add(contact);
                String formattedContact = fileFormatter.format(storableList);

                //Saves the contact
                FileHandler fileHandler = new FileHandler();
                fileHandler.write(formattedContact,getContext(),"Contacts.txt");

                //Reads the contact from harddrive
                String readContact = fileHandler.read(getContext(),"Contacts.txt");

                //Converts all contacts again, maybe more obtimized to add a separate method for this
                ContactConverter.getInstance().convert(readContact);





                navController.navigate(R.id.switch_back_contact);
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



}
