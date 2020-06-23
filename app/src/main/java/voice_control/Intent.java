package voice_control;

import android.telephony.IccOpenLogicalChannelResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import voice_control.commands.ICommand;

public abstract class Intent implements IIntent {

    private String[] keywords;

    @Override
    public String[] getKeywords() {

        return this.keywords;
    }

    protected void setKeywords(@NotNull String[] keywords) {

        this.keywords = keywords;
    }

    protected Object parameters;

    @Override
    public Object getParameters()
    {
        return this.parameters;
    }

    protected void setParameters(@NotNull Object parameters)
    {
        this.parameters = parameters;
    }

    protected ICommand command;

    @Override
    public ICommand getCommand() {

        return this.command;
    }

    protected void setCommand(@NotNull ICommand command) {

        this.command = command;
    }

    public Intent(@NotNull ICommand command)
    {
        this.parameters = new ArrayList<>();
        this.setDefaultKeywords();
        this.setCommand(command);
    }

    public Intent(@NotNull String[] keywords, @NotNull Object parameters, @NotNull ICommand command)
    {
        this.setKeywords(keywords);
        this.setParameters(parameters);
        this.setCommand(command);
    }

    // Compares voice input with keywords.
    // See if this is the intention of the user.
    @Override
    public Boolean isMeant(String voiceInput) throws Exception {

        for (String keyword : this.keywords) {

            if (voiceInput.contains(keyword)) {
                this.extractParameters(voiceInput);
                return true;
            }
        }

        return false;
    }

    protected void setDefaultKeywords()
    {
    }

    // Extracts parameters from formulation.
    protected void extractParameters(String formulation) throws Exception {

    }
}
