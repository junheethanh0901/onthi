package vn.edu.ntu.onthi.controller;

import android.app.Application;
import android.companion.WifiDeviceFilter;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.onthi.model.contact;

public class controller extends Application implements Icontroller {
    List<contact> contactList;

    public controller() {
        contactList = new ArrayList<>();
        contactList.add(new contact(1,"Huỳnh Nhật Thanh","09/01/1999","0389237643","Nha Trang"));
        contactList.add(new contact(2,"Phan Tấn Huy","03/01/1999","0123456789","Phú Yên"));
        contactList.add(new contact(3,"Lương Tấn Thi","03/10/1999","0212145664","Quy Nhơn"));
    }

    @Override
    public List<contact> GetAllContact() {
        return contactList;
    }

    @Override
    public void addcontact(contact p) {
        contactList.add(p);

    }

    @Override
    public void edit(int id, contact p) {
        for (contact c: contactList)
        {
            if (c.getId() == id)
            {
                c.setName(p.getName());
                c.setBirth(p.getBirth());
                c.setPhone(p.getPhone());
                c.setAddress(p.getAddress());
            }
        }
    }
}
