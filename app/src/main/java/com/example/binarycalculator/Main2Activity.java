package com.example.binarycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private TextView Oktal1, Oktal2, Oktal3, Oktal4, Subnet, kelas, hosts;
    String stOktal1, stOktal2, stOktal3, stOktal4, stSubnet, sthasil;
    Integer sstOktal1, sstSubnet, kurang, kurang2, hasil;
    Bundle bundle;
    ArrayList<Integer> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        IP Atas
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

//        Class
        sstOktal1 = Integer.parseInt(stOktal1);
        kelas = findViewById(R.id.txtclass);
        if (sstOktal1<= 126) {
            kelas.setText("Class A");
        }
        else if (sstOktal1>=127 && sstOktal1<=191) {
            kelas.setText("Class B");
        }
        else if (sstOktal1>=192 && sstOktal1<=223) {
            kelas.setText("Class C");
        }
        else if (sstOktal1>=224 && sstOktal1<=239) {
            kelas.setText("Class D");
        }
        else if (sstOktal1>=240 && sstOktal1<=225) {
            kelas.setText("Class E");
        }

//        Hosts
        sstSubnet = Integer.parseInt(stSubnet);
        hosts = findViewById(R.id.host);
        kurang = 32 - sstSubnet;
        hasil = 1;
        for (int a = 1; a<=kurang; a++){
            hasil = hasil * 2;
        }
        kurang2 = hasil - 2;
        sthasil = kurang2.toString();
        hosts.setText(String.valueOf(sthasil));
    }
}
