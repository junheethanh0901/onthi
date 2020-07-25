package vn.edu.ntu.onthi.controller;

import java.util.List;

import vn.edu.ntu.onthi.model.contact;

public interface Icontroller {
    public List<contact> GetAllContact();
    public void addcontact(contact p);
    public void edit(int id, contact p);
}
