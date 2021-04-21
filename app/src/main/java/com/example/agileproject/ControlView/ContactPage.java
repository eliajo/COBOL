package com.example.agileproject.ControlView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.agileproject.ControlView.ContactAdapter;

import com.example.agileproject.R;

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


        s1 = getResources().getStringArray(R.array.Contact);
        s2 = getResources().getStringArray(R.array.Telephonenumber);

        ContactAdapter c = new ContactAdapter(getContext(), s1, s2);

        contactRecycleView.setAdapter(c);




        return v;
    }
}