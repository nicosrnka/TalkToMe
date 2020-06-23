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
    public boolean VolumeUp() {
        return sendCommand("VolumeUp");
    }

    @Override
    public boolean VolumeDown() {
        return sendCommand("VolumeDown");
    }

    @Override
    public boolean ChannelUp() {
        return sendCommand("ChannelStepUp");
    }

    @Override
    public boolean ChannelDown() {
        return sendCommand("ChannelStepDown");
    }

    @Override
    public boolean Mute() {
        return sendCommand("Mute");
    }

    public boolean Home() {
        return sendCommand("Home");
    }

    @Override
    public boolean Options() {
        return sendCommand("Options");
    }

    @Override
    public boolean Shutdown() {
        return sendCommand("Standby");
    }

    @Override
    public boolean Info() {
        return sendCommand("Info");
    }

    @Override
    public boolean sendCommand(String command){
        boolean result = false;
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

            if (conn.getResponseCode() == 200) {
                result = true;
            }

            Log.i("MSG", conn.getResponseMessage());

            conn.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
