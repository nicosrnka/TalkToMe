package com.example.talktome;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MessageSpeaker implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private String message;

    public MessageSpeaker(Context context, String message) {
        tts = new TextToSpeech(context, this);
        this.message = message;
    }

    @Override
    public void onInit(int i) {
        tts.setLanguage(Locale.GERMAN);
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "1");
    }
}
