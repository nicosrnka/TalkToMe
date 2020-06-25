package com.example.talktome.tv;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Philips implements TVControl{
    private String address;

    public Philips(String address) {
        this.address = address;
    }

    @Override
    public void VolumeUp() {
        sendCommand("VolumeUp");
    }

    @Override
    public void VolumeDown() {
        sendCommand("VolumeDown");
    }

    @Override
    public void ChannelUp() {
        sendCommand("ChannelStepUp");
    }

    @Override
    public void ChannelDown() {
        sendCommand("ChannelStepDown");
    }

    @Override
    public void Mute() {
        sendCommand("Mute");
    }

    @Override
    public void Home() {
        sendCommand("Home");
    }

    @Override
    public void Options() {
        sendCommand("Options");
    }

    @Override
    public void Shutdown() {
        sendCommand("Standby");
    }

    @Override
    public void Info() {
        sendCommand("Info");
    }

    @Override
    public void sendCommand(String command){

        Runnable runnable = () -> {
            try {
                URL url = new URL("http://" + address + ":1925/1/input/key");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("key", command);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
}
