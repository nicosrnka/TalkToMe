package voice_control.tvcontrol_assistant.intents;

import org.jetbrains.annotations.NotNull;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class VolumeDownIntent extends Intent {
    public VolumeDownIntent(@NotNull ICommand command) {
        super(command);
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[]{
                "leiser",
        });
    }
}
