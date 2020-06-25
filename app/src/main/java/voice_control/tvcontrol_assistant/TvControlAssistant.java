package voice_control.tvcontrol_assistant;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import voice_control.Assistant;
import voice_control.IIntent;
import voice_control.tvcontrol_assistant.commands.ChannelDownCommand;
import voice_control.tvcontrol_assistant.commands.ChannelUpCommand;
import voice_control.tvcontrol_assistant.commands.HomeCommand;
import voice_control.tvcontrol_assistant.commands.InfoCommand;
import voice_control.tvcontrol_assistant.commands.MuteCommand;
import voice_control.tvcontrol_assistant.commands.OptionsCommand;
import voice_control.tvcontrol_assistant.commands.ShutdownCommand;
import voice_control.tvcontrol_assistant.commands.VolumeDownCommand;
import voice_control.tvcontrol_assistant.commands.VolumeUpCommand;
import voice_control.tvcontrol_assistant.intents.ChannelDownIntent;
import voice_control.tvcontrol_assistant.intents.ChannelUpIntent;
import voice_control.tvcontrol_assistant.intents.HomeIntent;
import voice_control.tvcontrol_assistant.intents.InfoIntent;
import voice_control.tvcontrol_assistant.intents.MuteIntent;
import voice_control.tvcontrol_assistant.intents.OptionsIntent;
import voice_control.tvcontrol_assistant.intents.ShutdownIntent;
import voice_control.tvcontrol_assistant.intents.VolumeDownIntent;
import voice_control.tvcontrol_assistant.intents.VolumeUpIntent;

public class TvControlAssistant extends Assistant {

    public TvControlAssistant(@NotNull Context context){
        this.intents = this.getDefaultIntents(context);
    }

    private IIntent[] getDefaultIntents(Context context) {
        return new IIntent[]{
                new ChannelDownIntent(new ChannelDownCommand(context)),
                new ChannelUpIntent(new ChannelUpCommand(context)),
                new HomeIntent(new HomeCommand(context)),
                new InfoIntent(new InfoCommand(context)),
                new MuteIntent(new MuteCommand(context)),
                new OptionsIntent(new OptionsCommand(context)),
                new ShutdownIntent(new ShutdownCommand(context)),
                new VolumeDownIntent(new VolumeDownCommand(context)),
                new VolumeUpIntent(new VolumeUpCommand(context)),
        };
    }
}
