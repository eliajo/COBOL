package com.example.agileproject.ControlView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agileproject.R;
import com.example.agileproject.Utils.FileFormatter;
import com.example.agileproject.Utils.FileHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;


/**
 * Adapter-class for the contact page.
 *
 * @author Elin Berthag, Alva Leufstedt, Klara Jakobsson
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    List<Contact> contactList;
    Context context;
    int index = 0;
    FileHandler fileHandler =  new FileHandler();
    FileFormatter fileFormatter = new FileFormatter();

    public ContactAdapter(Context ct, List<Contact> cl){
        context = ct ;
        contactList = cl;

    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_card, parent, false);


        return new ContactAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.myText1.setText(contactList.get(position).name);
        holder.myText2.setText(contactList.get(position).contactWay);
        index++;
    }


    @Override
    public int getItemCount() {

        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        FloatingActionButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.text1);
            myText2 = itemView.findViewById(R.id.text2);
            delete = itemView.findViewById(R.id.deleteButton);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeContact(getAdapterPosition());
                    fileHandler.empty( context ,"Contacts.txt");
                   String contacts = fileFormatter.formatContact(contactList);
                   fileHandler.write(contacts,context,"Contacts.txt");

                }
            });
        }

        public void removeContact(int position){
                contactList.remove(position);
                notifyItemRemoved(position);

        }
    }



}
