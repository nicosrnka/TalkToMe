package voice_control.call_assistant.intents;

import android.content.Context;

import com.example.talktome.calltypes.GeneralCall;

import org.jetbrains.annotations.NotNull;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class ImergencyIntent extends Intent {

    public ImergencyIntent(@NotNull String[] keywords, @NotNull Object parameters, @NotNull ICommand command)
    {
        super(keywords, parameters, command);
    }

    public ImergencyIntent(@NotNull ICommand command)
    {
        super(command);
    }

    @Override
    protected void setDefaultKeywords()
    {
        this.setKeywords(new String[]{
                "hilfe",
        });
    }

    // Extract name from formulation and give to command.
    @Override
    protected void extractParameters(String formulation) throws Exception {

    }
}
