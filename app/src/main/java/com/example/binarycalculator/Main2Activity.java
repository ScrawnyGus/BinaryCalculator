package com.example.binarycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private TextView Oktal1, Oktal2, Oktal3, Oktal4, Subnet;
    String stOktal1, stOktal2, stOktal3, stOktal4, stSubnet;
    Bundle bundle;
    ArrayList<Integer> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Oktal1 = findViewById(R.id.txtOktal1);
        Oktal2 = findViewById(R.id.txtOktal2);
        Oktal3 = findViewById(R.id.txtOktal3);
        Oktal4 = findViewById(R.id.txtOktal4);
        Subnet = findViewById(R.id.txtSubnet);

        bundle = getIntent().getExtras();
        stOktal1 = bundle.getString("Okt1");
        stOktal2 = bundle.getString("Okt2");
        stOktal3 = bundle.getString("Okt3");
        stOktal4 = bundle.getString("Okt4");
        stSubnet = bundle.getString("Subn");

        Oktal1.setText(String.valueOf(stOktal1));
        Oktal2.setText(String.valueOf(stOktal2));
        Oktal3.setText(String.valueOf(stOktal3));
        Oktal4.setText(String.valueOf(stOktal4));
        Subnet.setText(String.valueOf(stSubnet));
    }
}
