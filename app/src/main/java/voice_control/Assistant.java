package voice_control;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import voice_control.commands.ICommand;

public abstract class Assistant implements IAssistant{

    protected IIntent[] intents;

    protected ICommand command;

    public ICommand getCommand()
    {
        return this.command;
    }

    protected void setCommand(@NotNull ICommand command)
    {
        this.command = command;
    }

    public Assistant()
    {
    }

    public Assistant(@NotNull IIntent[] intents)
    {
        this.intents = intents;
    }

    @Override
    public Boolean isMe(String voiceInput) throws Exception {
        for (IIntent intent : this.intents) {
            if (intent.isMeant(voiceInput)) {
                this.command = intent.getCommand();
                return true;
            }
        }

        return false;
    }
}
