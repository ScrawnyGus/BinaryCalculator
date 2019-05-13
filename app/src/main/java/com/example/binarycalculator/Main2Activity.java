package com.example.binarycalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView Oktal1, Oktal2, Oktal3, Oktal4, Subnet, kelas, hosts, subBinLabel, netMaskLabel, wildCardLabel, wildBinLabel, netBinLabel, bcBinLabel, netIdLabel, bcIdLabel, ipBinLabel, ipIdLabel, hostMinIdLabel, hostMaxIdLabel, hostMinBinLabel, hostMaxBinLabel, netTypeLabel;
    String stOktal1, stOktal2, stOktal3, stOktal4, stSubnet, sthasil, gg;
    Integer sstOktal1, sstSubnet, kurang, kurang2, hasil;
    Bundle bundle;
    SeekBar sbSubnet;

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
        wildCardLabel = findViewById(R.id.wildCardLabel);
        wildBinLabel = findViewById(R.id.wildBinLabel);
        netBinLabel = findViewById(R.id.netBinLabel);
        bcBinLabel = findViewById(R.id.bcBinLabel);
        netIdLabel = findViewById(R.id.netIdLabel);
        bcIdLabel = findViewById(R.id.bcIdLabel);
        ipIdLabel = findViewById(R.id.ipIdLabel);
        ipBinLabel = findViewById(R.id.ipBinLabel);
        hostMinIdLabel = findViewById(R.id.hostMinIdLabel);
        hostMaxIdLabel = findViewById(R.id.hostMaxIdLabel);
        hostMinBinLabel = findViewById(R.id.hostMinBinLabel);
        hostMaxBinLabel = findViewById(R.id.hostMaxBinLabel);
        netTypeLabel = findViewById(R.id.netTypeLabel);

        sbSubnet = findViewById(R.id.sbSubnet);
        sbSubnet.setMax(32);
        sbSubnet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int seekBarProgress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        String subBinaray = "";
        String subBinaryFull = "";
        String binnetidFull = "";
        String binbcidFull = "";
        String netmaskFull = "";
        String wildcard = "";
        String netidFull = "";
        String bcidFull = "";
        String hostmin = "";
        String hostmax = "";
        String binhostmin = "";
        String binhostmax = "";
        String wildbin = "";
        String nettype = "(Public Network)";

        //loop to generate binary mask from octet pos
        //biner netmask
        for (int i = 1; i <= 8; i++) {
            if (i <= rem) {
                subBinary += "1";
                subBinaray += "0";
            } else {
                subBinary += "0";
                subBinaray += "1";
            }
        };

        //netmask (integer version of subBinary
        //ip netmask
        int netmask = Integer.parseInt(String.valueOf(subBinary), 2);

        //ip octets array
        //penarikan oktet dengan array
        Integer[] oct = {
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
//            if (3 == i) {
//                binNetId += " ";
//                binBcId += " ";
//            }
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
                wildcard += "0";
                wildbin += "00000000";
                binnetidFull += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
                binbcidFull += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
                netidFull += oct[i];
                bcidFull += oct[i];
                hostmin += oct[i];
                hostmax += oct[i];
                binhostmin += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');
                binhostmax += String.format("%8s", Integer.toBinaryString(oct[i])).replace(' ', '0');

            } else if (i > pos) {
                subBinaryFull += "00000000";
                netmaskFull += "0";
                wildcard += "255";
                wildbin += "11111111";
                binnetidFull += "00000000";
                binbcidFull += "11111111";
                netidFull += "0";
                bcidFull += "255";
                if(i == 3) {
//                    hostmin += Integer.parseInt(String.valueOf(binNetId), 2)+1;
//                    hostmax += Integer.parseInt(String.valueOf(binBcId), 2)-1;
//                    binhostmin += binNetId.substring(0, 7) + "1";
//                    binhostmax += binBcId.substring(0, 7) + "0";
                    hostmin += "1";
                    hostmax += "254";
                    binhostmin += "00000001";
                    binhostmax += "11111110";
                }
                else {
//                    hostmin += Integer.parseInt(String.valueOf(binNetId), 2);
//                    hostmax += Integer.parseInt(String.valueOf(binBcId), 2);
//                    binhostmin += binNetId;
//                    binhostmax += binBcId;
                    hostmin += "0";
                    hostmax += "255";
                    binhostmin += "00000000";
                    binhostmax += "11111111";
                }
            } else {
                subBinaryFull += subBinary;
                netmaskFull += String.valueOf(netmask);
                wildcard += 255 - netmask;
                wildbin += subBinaray;
                binnetidFull += binNetId;
                binbcidFull += binBcId;
                netidFull += Integer.parseInt(String.valueOf(binNetId), 2);
                bcidFull += Integer.parseInt(String.valueOf(binBcId), 2);
                hostmin += Integer.parseInt(String.valueOf(binNetId), 2);
                hostmax += Integer.parseInt(String.valueOf(binBcId), 2);
                binhostmin += binNetId;
                binhostmax += binBcId;
            }


            if (i != 3) {
                subBinaryFull += ".";
                netmaskFull += ".";
                wildcard += ".";
                wildbin += ".";
                binnetidFull += ".";
                binbcidFull += ".";
                netidFull += ".";
                bcidFull += ".";
                binIpFull += ".";
                hostmin += ".";
                hostmax += ".";
                binhostmin += ".";
                binhostmax += ".";
            }
        }

        if (oct[0] == 10) {
            nettype = "(Private Network)";
        }

        if (oct[0] == 172) {
            if (oct[1] >= 16 && oct[1] <= 32) {
                nettype = "(Private Network)";
            }
        }

        if (oct[0] == 192) {
            if (oct[1] == 168) {
                nettype = "(Private Network)";
            }
        }

        if (prefix<=8){
            subBinLabel.setText(subBinaryFull.substring(0,prefix) + " " + subBinaryFull.substring(prefix,35));
            wildBinLabel.setText(wildbin.substring(0,prefix) + " " + wildbin.substring(prefix,35));
            netBinLabel.setText(binnetidFull.substring(0,prefix) + " " + binnetidFull.substring(prefix,35));
            bcBinLabel.setText(binbcidFull.substring(0,prefix) + " " + binbcidFull.substring(prefix,35));
            ipBinLabel.setText(binIpFull.substring(0,prefix) + " " + binIpFull.substring(prefix,35));
            hostMinBinLabel.setText(binhostmin.substring(0,prefix) + " " + binhostmin.substring(prefix,35));
            hostMaxBinLabel.setText(binhostmax.substring(0,prefix) + " " + binhostmax.substring(prefix,35));
        }
        else if (prefix<=16){
            subBinLabel.setText(subBinaryFull.substring(0,prefix+1) + " " + subBinaryFull.substring(prefix+1,35));
            wildBinLabel.setText(wildbin.substring(0,prefix+1) + " " + wildbin.substring(prefix+1,35));
            netBinLabel.setText(binnetidFull.substring(0,prefix+1) + " " + binnetidFull.substring(prefix+1,35));
            bcBinLabel.setText(binbcidFull.substring(0,prefix+1) + " " + binbcidFull.substring(prefix+1,35));
            ipBinLabel.setText(binIpFull.substring(0,prefix+1) + " " + binIpFull.substring(prefix+1,35));
            hostMinBinLabel.setText(binhostmin.substring(0,prefix+1) + " " + binhostmin.substring(prefix+1,35));
            hostMaxBinLabel.setText(binhostmax.substring(0,prefix+1) + " " + binhostmax.substring(prefix+1,35));
        }
        else if (prefix<=24){
            subBinLabel.setText(subBinaryFull.substring(0,prefix+2) + " " + subBinaryFull.substring(prefix+2,35));
            wildBinLabel.setText(wildbin.substring(0,prefix+2) + " " + wildbin.substring(prefix+2,35));
            netBinLabel.setText(binnetidFull.substring(0,prefix+2) + " " + binnetidFull.substring(prefix+2,35));
            bcBinLabel.setText(binbcidFull.substring(0,prefix+2) + " " + binbcidFull.substring(prefix+2,35));
            ipBinLabel.setText(binIpFull.substring(0,prefix+2) + " " + binIpFull.substring(prefix+2,35));
            hostMinBinLabel.setText(binhostmin.substring(0,prefix+2) + " " + binhostmin.substring(prefix+2,35));
            hostMaxBinLabel.setText(binhostmax.substring(0,prefix+2) + " " + binhostmax.substring(prefix+2,35));
        }
        else if (prefix<=32){
            subBinLabel.setText(subBinaryFull.substring(0,prefix+3) + " " + subBinaryFull.substring(prefix+3,35));
            wildBinLabel.setText(wildbin.substring(0,prefix+3) + " " + wildbin.substring(prefix+3,35));
            netBinLabel.setText(binnetidFull.substring(0,prefix+3) + " " + binnetidFull.substring(prefix+3,35));
            bcBinLabel.setText(binbcidFull.substring(0,prefix+3) + " " + binbcidFull.substring(prefix+3,35));
            ipBinLabel.setText(binIpFull.substring(0,prefix+3) + " " + binIpFull.substring(prefix+3,35));
            hostMinBinLabel.setText(binhostmin.substring(0,prefix+3) + " " + binhostmin.substring(prefix+3,35));
            hostMaxBinLabel.setText(binhostmax.substring(0,prefix+3) + " " + binhostmax.substring(prefix+3,35));
        }

        netMaskLabel.setText(netmaskFull);
        wildCardLabel.setText(wildcard);
        netIdLabel.setText(netidFull);
        bcIdLabel.setText(bcidFull);
        hostMinIdLabel.setText(hostmin);
        hostMaxIdLabel.setText(hostmax);
        netTypeLabel.setText(nettype);
    }

    private void getText(TextView subnet) {

    }
}
