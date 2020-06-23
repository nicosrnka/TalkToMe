package voice_control;

import java.util.List;

import voice_control.commands.ICommand;

public interface IAssistant
{
    Boolean isMe(String voiceInput) throws Exception;

    ICommand getCommand();
}
