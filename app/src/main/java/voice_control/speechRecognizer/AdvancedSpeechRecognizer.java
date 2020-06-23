package voice_control.speechRecognizer;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import com.example.talktome.activities.WorkingSpace;

import org.jetbrains.annotations.NotNull;

public class AdvancedSpeechRecognizer {

    private SpeechRecognizer speechRecognizer;

    private Intent intent;

    private Context context;

    private WorkingSpace workingSpace;

    // This class must only be instantiated from main thread.
    public AdvancedSpeechRecognizer(@NotNull Context context, @NotNull WorkingSpace workingSpace){

        this.context = context;
        this.workingSpace = workingSpace;
        this.init();
    }

    // This method must only be invoked from main thread.
    private void init(){

        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.context);
        this.intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        this.intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        this.intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());

        AdvancedRecognitionListener listener = new AdvancedRecognitionListener(this.workingSpace);
        speechRecognizer.setRecognitionListener(listener);

    }

    public void start(){

        if(this.speechRecognizer != null) {
            this.speechRecognizer.startListening(this.intent);
        }
    }

    public void stop()
    {
        if(this.speechRecognizer != null) {
            this.speechRecognizer.destroy();
        }
    }
}
