package voice_control.smallTalk_assistant.intents;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class JokeIntent extends Intent {

    public JokeIntent(@NotNull String[] keywords, @NotNull List<String> parameters, @NotNull ICommand command)
    {
        super(keywords, parameters, command);
    }

    public JokeIntent(@NotNull ICommand command)
    {
        super(command);
    }

    @Override
    protected void setDefaultKeywords()
    {
        String[] kw = new String[]{
                "witz"
        };

        this.setKeywords(kw);
    }
}
