package voice_control.smallTalk_assistant;


import org.jetbrains.annotations.NotNull;

import voice_control.Assistant;
import voice_control.IIntent;
import voice_control.commands.MessageCommand;
import voice_control.smallTalk_assistant.intents.AskForTimeIntent;
import voice_control.smallTalk_assistant.intents.GreetingIntent;
import voice_control.smallTalk_assistant.intents.JokeIntent;

import java.util.Calendar;

public class SmallTalkAssistant extends Assistant
{
    public SmallTalkAssistant()
    {
        this.intents = this.getDefaultIntents();
    }

    public SmallTalkAssistant(@NotNull IIntent[] intents)
    {
        super(intents);
    }

    private IIntent[] getDefaultIntents()
    {
        return new IIntent[] {
                new GreetingIntent(new MessageCommand("hallo")),
                new JokeIntent(new MessageCommand("Warum musste der Bäcker ins Gefängnis? Er hat zu viele Eier geschlagen.")),
                new AskForTimeIntent(new MessageCommand(Calendar.getInstance().getTime().toString()))
        };
    }
}
