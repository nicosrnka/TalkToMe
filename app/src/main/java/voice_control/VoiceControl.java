package voice_control;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import voice_control.commands.ICommand;
import voice_control.commands.MessageCommand;

public class VoiceControl
{
    private IAssistant[] assistants;
    
    public VoiceControl(@NotNull IAssistant[] assistants)
    {
        this.assistants = assistants;
    }
    
    public ICommand processSpeechInput(String formulation)
    {
        try {
            for (IAssistant assistant : this.assistants) {
                if (assistant.isMe(formulation.toLowerCase())) {
                    return assistant.getCommand();
                }
            }
        }
        catch (Exception e){
            return new MessageCommand(e.getMessage());
        }

        return new MessageCommand("Entschuldigung das habe ich nicht verstanden");
    }
}