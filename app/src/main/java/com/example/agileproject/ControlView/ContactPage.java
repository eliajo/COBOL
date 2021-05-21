package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agileproject.R;
import com.example.agileproject.Utils.ContactConverter;
import com.example.agileproject.Utils.FileHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a contact page.
 *
 * @author Elin Berthag, Alva Leufstedt, Klara Jakobsson
 */
public class ContactPage extends Fragment {

    NavController navController;

    public ContactPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_page, container, false);

        RecyclerView contactRecycleView = v.findViewById(R.id.ContactRecycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        contactRecycleView.setLayoutManager(layoutManager);

        FileHandler fileHandler = new FileHandler();

        String readContact = fileHandler.read(getContext(),"Contacts.txt");

        //Converts all contacts again, maybe more obtimized to add a separate method for this
        ContactConverter.getInstance().convert(readContact);

        //Gets the contacts that are already loaded in (either at startup or after contact addition)
        List<Contact> contactList = ContactConverter.getInstance().getContactList();

        ContactAdapter c = new ContactAdapter(getContext(), contactList);
        contactRecycleView.setAdapter(c);

        // main_pages_fragment defines the navigation between pages.
        navController = Navigation.findNavController(this.getActivity(), R.id.main_pages_fragment);

        // addButton is the blue button on the contact page. When clicking it, the add contact page opens.
        FloatingActionButton add = v.findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch from contact page to add contact page
                navController.navigate(R.id.switch_contact_page22);
            }

        });


        return v;
    }



}