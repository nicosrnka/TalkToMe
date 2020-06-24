package voice_control.tvcontrol_assistant.intents;

import org.jetbrains.annotations.NotNull;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class ChannelUpIntent extends Intent {
    public ChannelUpIntent(@NotNull ICommand command) {
        super(command);
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[]{
                "weiter",
        });
    }
}
