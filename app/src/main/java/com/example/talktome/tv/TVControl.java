package com.example.talktome.tv;

public interface TVControl {
    public boolean VolumeUp();
    public boolean VolumeDown();
    public boolean ChannelUp();
    public boolean ChannelDown();
    public boolean Mute();
    public boolean Home();
    public boolean Options();
    public boolean Shutdown();
    public boolean Info();

    public boolean sendCommand(String command);
}
