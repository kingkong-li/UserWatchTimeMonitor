package com.example.jingangli.userwatchtimemonitor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author jingang.li
 */
public class MyFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        FragmentManager manager =this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment=new MyFragment();
        transaction.add(R.id.fragment_container,fragment);
        transaction.commit();

    }
}
