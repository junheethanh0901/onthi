package vn.edu.ntu.onthi;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.onthi.controller.Icontroller;
import vn.edu.ntu.onthi.model.contact;


public class dienthongtin_Fragment extends Fragment {
    EditText edtid, edtten, edtngaysinh, edtsdt, edtdiachi;
    Button btsave;
    Toolbar toolbar;
    NavController navController;
    Icontroller controller;
    int id, kt;
    contact contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dienthongtin_, container, false);
        addView(view);
        getdata();
        addEvent();

        return view;
    }

    private void addView(View view) {
        edtten = view.findViewById(R.id.txtname);
        edtngaysinh = view.findViewById(R.id.txtbirth);
        edtsdt = view.findViewById(R.id.txtphone);
        edtdiachi = view.findViewById(R.id.txtadd);
        edtid = view.findViewById(R.id.txtid);
        btsave = view.findViewById(R.id.btsave);
        toolbar = view.findViewById(R.id.tbdtt);
        navController = NavHostFragment.findNavController(dienthongtin_Fragment.this);
        controller = ((MainActivity)getActivity()).controller;

    }


    private void getdata() {
        Bundle data = new Bundle();
        if(data!=null)
        {
            kt = data.getInt("kt");
            id = data.getInt("id");
            if (kt==0)
            {
                edtid.setText(String.valueOf(id));
            }
            else
                {
                edtid.setText(String.valueOf(id));
                edtten.setText(data.getString("ten"));
                edtngaysinh.setText(data.getString("birth"));
                edtsdt.setText(data.getString("phone"));
                edtdiachi.setText(data.getString("address"));
                }
        }
    }

    private void addEvent() {
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_dienthongtin_Fragment_to_hienthiFragment);
            }
        });
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                contact = new contact(id,edtten.getText().toString(),edtngaysinh.getText().toString()
                ,edtsdt.getText().toString(),edtdiachi.getText().toString());
                if (kt == 0)
                    controller.addcontact(contact);
                else
                    controller.edit(id,contact);
                navController.navigate(R.id.action_dienthongtin_Fragment_to_hienthiFragment);
            }
        });
    }
}