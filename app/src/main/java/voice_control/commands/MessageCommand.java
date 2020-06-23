package voice_control.commands;

// This command is the default command used for message only commands.
public class MessageCommand implements ICommand {

    private String message;

    public MessageCommand(String message) {

        this.message = message;
    }

    @Override
    public void execute() {

    }

    @Override
    public String getMessage() {

        return this.message;
    }

    @Override
    public Object getParameter() {
        return null;
    }

    @Override
    public void setParameter(Object parameter) {

    }
}
