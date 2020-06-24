package voice_control.call_assistant.commands;

import voice_control.commands.ICommand;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.calltypes.GeneralCall;

import org.jetbrains.annotations.NotNull;

public class CallCommand implements ICommand {

    private String message;

    private Object parameter;

    private Context context;

    private WorkingSpace workingSpace;

    public CallCommand(@NotNull Context context, @NotNull WorkingSpace workingSpace)
    {
        this.context = context;
        this.workingSpace = workingSpace;
    }

    @Override
    public void execute() {
        //GeneralCall generalCall = new GeneralCall(this.context);
        //generalCall.tryCallingName(this.getParameter());

        //this.message = this.parameter + " wird angerufen";
        this.message = "";

        this.workingSpace.setCurrentContactName((String) this.getParameter());
        this.workingSpace.requestCall();

    }

    @Override
    public String getMessage() {

        return this.message;
    }

    @Override
    public Object getParameter() {
        return this.parameter;
    }

    @Override
    public void setParameter(Object entity) {
        this.parameter = entity;
    }
}
