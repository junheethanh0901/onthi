package vn.edu.ntu.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;

import vn.edu.ntu.onthi.controller.Icontroller;
import vn.edu.ntu.onthi.controller.controller;

public class MainActivity extends AppCompatActivity {

    Icontroller controller ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new controller();
        controller = (Icontroller) getApplication();
    }
}