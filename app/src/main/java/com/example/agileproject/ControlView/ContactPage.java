package com.example.agileproject.ControlView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agileproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    NavController navController;

    public ContactPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contact_page.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactPage newInstance(String param1, String param2) {
        ContactPage fragment = new ContactPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_page, container, false);

        String s1[], s2[];
        RecyclerView contactRecycleView = v.findViewById(R.id.ContactRecycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        contactRecycleView.setLayoutManager(layoutManager);

        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact("Klara", "0735241742"));

        s1 = getResources().getStringArray(R.array.ContactName);
        s2 = getResources().getStringArray(R.array.Telephonenumber);

        ContactAdapter c = new ContactAdapter(getContext(), contactList);

        contactRecycleView.setAdapter(c);

        Button add = v.findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch page here.
                switchContactPage();
            }
            public void switchContactPage() {
                //  Intent intent = new Intent(this, ContactAdapter.class);
                //startActivity(intent);
                navController.navigate(R.id.switch_contact_page);
            }

        });


        return v;
    }



}