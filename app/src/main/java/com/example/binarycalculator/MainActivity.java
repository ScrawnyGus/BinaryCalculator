package com.example.binarycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText Oktal1, Oktal2, Oktal3, Oktal4, Subnet;
    String stOktal1, stOktal2, stOktal3, stOktal4, stSubnet;
    Integer intOktal1, intOktal2, intOktal3, intOktal4, intSubnet;
    Intent impl;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Oktal1 = findViewById(R.id.edtOktal1);
        Oktal2 = findViewById(R.id.edtOktal2);
        Oktal3 = findViewById(R.id.edtOktal3);
        Oktal4 = findViewById(R.id.edtOktal4);
        Subnet = findViewById(R.id.edtSubnet);

        Oktal1.setFilters(new InputFilter[] {new CustomRangeInputFilter(0, 255)});
        Oktal2.setFilters(new InputFilter[] {new CustomRangeInputFilter(0, 255)});
        Oktal3.setFilters(new InputFilter[] {new CustomRangeInputFilter(0, 255)});
        Oktal4.setFilters(new InputFilter[] {new CustomRangeInputFilter(0, 255)});
        Subnet.setFilters(new InputFilter[] {new CustomRangeInputFilter(0, 31)});
    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void Submit(View v) {
        if (Oktal1.getText().toString().length()==0)
        {
            Oktal1.setError("Oktet 1 tidak boleh kosong");
        }
        if (Oktal2.getText().toString().length()==0)
        {
            Oktal2.setError("Oktet 2 tidak boleh kosong");
        }
        if (Oktal3.getText().toString().length()==0)
        {
            Oktal3.setError("Oktet 3 tidak boleh kosong");
        }
        if (Oktal4.getText().toString().length()==0)
        {
            Oktal4.setError("Oktet 4 tidak boleh kosong");
        }
        if (Subnet.getText().toString().length()==0)
        {
            Subnet.setError("Subnet tidak boleh kosong");
        }
        else
        {
            stOktal1 = Oktal1.getText() + "";
            stOktal2 = Oktal2.getText() + "";
            stOktal3 = Oktal3.getText() + "";
            stOktal4 = Oktal4.getText() + "";
            stSubnet = Subnet.getText() + "";

            toastMsg(stOktal1+"."+stOktal2+"."+stOktal3+"."+stOktal4+"/"+stSubnet);

            bundle = new Bundle();
            bundle.putString("Okt1", stOktal1);
            bundle.putString("Okt2", stOktal2);
            bundle.putString("Okt3", stOktal3);
            bundle.putString("Okt4", stOktal4);
            bundle.putString("Subn", stSubnet);

            impl = new Intent(MainActivity.this,Main2Activity.class);
            impl.putExtras(bundle);
            startActivity(impl);
        }
    }
}