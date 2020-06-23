package voice_control;

import java.util.ArrayList;
import java.util.List;

import voice_control.commands.ICommand;

// There is an intention in every user input.
public interface IIntent
{
    String[] getKeywords();

    Object getParameters();

    ICommand getCommand();

    Boolean isMeant(String voiceInput) throws Exception;
}
