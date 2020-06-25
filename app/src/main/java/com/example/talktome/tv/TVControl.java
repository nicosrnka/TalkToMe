package com.example.talktome.tv;

public interface TVControl {
    public void VolumeUp();
    public void VolumeDown();
    public void ChannelUp();
    public void ChannelDown();
    public void Mute();
    public void Home();
    public void Options();
    public void Shutdown();
    public void Info();

    public void sendCommand(String command);
}
