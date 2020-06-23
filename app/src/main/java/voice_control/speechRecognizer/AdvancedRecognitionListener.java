package voice_control.speechRecognizer;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.example.talktome.activities.WorkingSpace;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import voice_control.VoiceControl;

public class AdvancedRecognitionListener implements RecognitionListener {

    private WorkingSpace workingSpace;

    public  AdvancedRecognitionListener(@NotNull WorkingSpace workingSpace){
        this.workingSpace = workingSpace;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onResults(Bundle bundle) {

        ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        // Merge words to one string.
        String formulation = "";

        for (String word: matches) {
            formulation += (word + " ");
        }

        this.workingSpace.handleSpeechInput(formulation);
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
