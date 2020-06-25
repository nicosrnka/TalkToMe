package voice_control.call_assistant.commands;

import voice_control.commands.ICommand;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.calltypes.CallRequester;
import com.example.talktome.calltypes.CallTypes;
import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.models.CaregiverModel;

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
        //generalCall.tryCallingName((String)this.getParameter());

        //this.message = this.parameter + " wird angerufen";
        this.message = " ";

        try{
            GeneralCall generalCall = new GeneralCall(this.context);
            generalCall.callCaregiver((CaregiverModel) this.getParameter());
        }
        catch (Exception e) {
            CallRequester callRequester = new CallRequester(this.context, this.workingSpace);
            callRequester.setCurrentCallType(CallTypes.General);
            callRequester.setCurrentContactName((String) this.getParameter());
            callRequester.requestCall();
        }
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
