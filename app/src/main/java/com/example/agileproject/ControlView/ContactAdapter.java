package com.example.agileproject.ControlView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agileproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    List<Contact> contactList;
    Context context;

    public ContactAdapter(Context ct, List<Contact> cl){
        context = ct ;
        contactList = cl;
    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_contact_page, parent, false);

        return new ContactAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText((CharSequence) contactList.get(position));
    }


    @Override
    public int getItemCount() {

        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.text1);
            myText2 = itemView.findViewById(R.id.text2);

        }
    }



}
