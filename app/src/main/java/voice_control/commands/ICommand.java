package voice_control.commands;

public interface ICommand {

    void execute();

    String getMessage();

    Object getParameter();

    void setParameter(Object parameter);
}
