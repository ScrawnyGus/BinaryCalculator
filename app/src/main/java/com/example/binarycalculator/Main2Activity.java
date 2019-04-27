package com.example.binarycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private TextView Oktal1, Oktal2, Oktal3, Oktal4, Subnet, kelas, hosts, subBinLabel, netMaskLabel, netBinLabel, bcBinLabel, netIdLabel, bcIdLabel, ipBinLabel, ipIdLabel;
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
        subBinLabel = findViewById(R.id.subBinLabel);
        netMaskLabel = findViewById(R.id.netMaskLabel);
        netBinLabel = findViewById(R.id.netBinLabel);
        bcBinLabel = findViewById(R.id.bcBinLabel);
        netIdLabel = findViewById(R.id.netIdLabel);
        bcIdLabel = findViewById(R.id.bcIdLabel);
        ipIdLabel = findViewById(R.id.ipIdLabel);
        ipBinLabel = findViewById(R.id.ipBinLabel);

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
        ipIdLabel.setText(stOktal1+"."+stOktal2+"."+stOktal3+"."+stOktal4);

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

//        Binary codes
        int prefix = Integer.valueOf(stSubnet);
        int pos = prefix / 8; //important octet position
        int rem = prefix % 8; //binary digit of importan octet

        String binIpFull = "";
        String subBinary = "";
        String subBinaryFull = "";
        String binnetidFull = "";
        String binbcidFull = "";
        String netmaskFull = "";
        String netidFull = "";
        String bcidFull = "";

        //loop to generate binary mask from octet pos
        for (int i = 1; i <= 8; i++) {
            if (i <= rem) {
                subBinary += "1";
            } else {
                subBinary += "0";
            }
        };



        //netmask (integer version of subBinary
        int netmask = Integer.parseInt(String.valueOf(subBinary), 2);

        //ip octets array
        int[] oct = {
                Integer.valueOf(stOktal1),
                Integer.valueOf(stOktal2),
                Integer.valueOf(stOktal3),
                Integer.valueOf(stOktal4)
        };

        //binary of important octet
        String binOct = String.format("%8s", Integer.toBinaryString(oct[pos])).replace(' ', '0');

        String binNetId = "";
        String binBcId = "";

        for (int i = 0; i < 8; i++) {
            String binPointer = subBinary.substring(i, i + 1);
            if (binPointer.equals("1")) {
                binNetId += binOct.substring(i, i + 1);
                binBcId += binOct.substring(i, i + 1);
            } else {
                binNetId += "0";
                binBcId += "1";
            }
        }

        //loop to generate display-ready strings
        for (int i = 0; i <= 3; i++) {
            binIpFull += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
            if (i < pos) {
                subBinaryFull += "11111111";
                netmaskFull += "255";
                binnetidFull += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
                binbcidFull += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
                netidFull += oct[i];
                bcidFull += oct[i];
            } else if (i > pos) {
                subBinaryFull += "00000000";
                netmaskFull += "0";
                binnetidFull += "00000000";
                binbcidFull += "00000000";
                netidFull += "0";
                bcidFull += "1";

            } else {
                subBinaryFull += subBinary;
                netmaskFull += String.valueOf(netmask);
                binnetidFull += binNetId;
                binbcidFull += binBcId;
                netidFull += Integer.parseInt(String.valueOf(binNetId), 2);
                bcidFull += Integer.parseInt(String.valueOf(binBcId), 2);
            }
            if (i != 3) {
                subBinaryFull += ".";
                netmaskFull += ".";
                binnetidFull += ".";
                binbcidFull += ".";
                netidFull += ".";
                bcidFull += ".";
                binIpFull += ".";
            }
        }
        subBinLabel.setText(subBinaryFull);
        netMaskLabel.setText(netmaskFull);
        netBinLabel.setText(binnetidFull);
        bcBinLabel.setText(binbcidFull);
        netIdLabel.setText(netidFull);
        bcIdLabel.setText(bcidFull);
        ipBinLabel.setText(binIpFull);
    }
}
