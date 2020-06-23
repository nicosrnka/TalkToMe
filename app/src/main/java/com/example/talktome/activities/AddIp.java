package com.example.talktome.activities;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Toast;

import com.example.talktome.R;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class AddIp extends AppCompatActivity {

    private TextInputEditText ipField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ip);
        ipField = findViewById(R.id.ip_text_input_edit_text);
    }

    public void automaticIp(final View view) {
        Toast.makeText(getApplicationContext(), "Bitte warten Sie kurz.", Toast.LENGTH_SHORT).show();

        final WifiManager manager = (WifiManager) super.getSystemService(WIFI_SERVICE);
        final DhcpInfo dhcp = manager.getDhcpInfo();
        final String gateway = Formatter.formatIpAddress(dhcp.gateway);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String iplocal = portScan(gateway);
                if (iplocal.isEmpty()) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "TV konnte nicht gefunden werden.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            ipField.setText(iplocal);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    private String portScan(String gateway) {
        for (int i = 255; i > 0; i--) {
            String ip = "192.168.0." + i;
            if (!ip.equals(gateway)) {
                Socket socket = new Socket();
                SocketAddress address = new InetSocketAddress(ip, 1925);
                try {
                    socket.connect(address, 10);
                    socket.close();
                    return ip;
                } catch (Exception ex) {
                }
            }
        }
        return "";
    }
}