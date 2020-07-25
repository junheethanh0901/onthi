package vn.edu.ntu.onthi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.onthi.controller.Icontroller;
import vn.edu.ntu.onthi.model.contact;


public class hienthiFragment extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;

    NavController navController;
    Icontroller controller;

    List<contact> contactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hienthi, container, false);
        addview(view);
        getData();
        addEvent();
        return view;
    }

    private void addview(View view) {
        toolbar = view.findViewById(R.id.tbht);
        toolbar.inflateMenu(R.menu.menu);
        recyclerView = view.findViewById(R.id.rcvlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(hienthiFragment.this.getActivity()));

        navController = NavHostFragment.findNavController(hienthiFragment.this);
        controller = ((MainActivity)getActivity()).controller;
    }

    private void getData() {
        contactList = controller.GetAllContact();
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);

    }

    private void addEvent() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Bundle data = new Bundle();
                data.putInt("kt",0);
                data.putInt("id",contactList.size() + 1);
                navController.navigate(R.id.action_hienthiFragment_to_dienthongtin_Fragment,data);
                return false;
            }
        });

    }

    private class ContactViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtten, txtngaysinh, txtsdt;
        ImageView imgedit;

        //khai bao cac bien trong 1 thanh phan viewholder(phan tu lap di lap lai) cua adapter

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtngaysinh = itemView.findViewById(R.id.txtngaysinh);
            txtsdt = itemView.findViewById(R.id.txtsdt);
            imgedit = itemView.findViewById(R.id.edit);
            //anh xa cac bien vao itemView (View duoc tao rieng cho viewholder)-

        }

        public void bind(contact p)
        {
            //truyen du lieu vao cac thanh phan trong viewholder tu list trong adater--
            txtten.setText(p.getName());
            txtngaysinh.setText(p.getBirth());
            txtsdt.setText(p.getPhone());
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>
    {
        List<contact> listContacts;
        //danh sach cac phan tu can truyen vao viewholder

        public ContactAdapter(List<contact> listProducts) {
            this.listContacts = listProducts;
        }


        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact, parent, false);
            //lay view tu thu muc Res (product) de gan vao cho cac viewholder
            return new ContactViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
            holder.bind(listContacts.get(position));
            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle data = new Bundle();
                    data.putInt("kt",1);
                    data.putInt("id",listContacts.get(position).getId());
                    data.putString("ten",listContacts.get(position).getName());
                    data.putString("birth",listContacts.get(position).getBirth());
                    data.putString("phone",listContacts.get(position).getPhone());
                    data.putString("address",listContacts.get(position).getAddress());
                    navController.navigate(R.id.action_hienthiFragment_to_dienthongtin_Fragment,data);
                }
            });
            //goi ham bind da viet ben viewholder truyen du lieu vao tung viewholder


            //bat cac su kien co cac thanh phan (neu co)--
        }

        @Override
        public int getItemCount() {
            return listContacts.size();
        }
    }
}