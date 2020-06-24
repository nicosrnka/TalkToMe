package voice_control.call_assistant.commands;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.calltypes.CallRequester;
import com.example.talktome.calltypes.CallTypes;
import com.example.talktome.calltypes.DuoCall;
import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;

import voice_control.commands.ICommand;

public class DuoCallCommand implements ICommand {
    private String message;

    private Object parameter;

    private Context context;

    private WorkingSpace workingSpace;

    public DuoCallCommand(@NotNull Context context, @NotNull WorkingSpace workingSpace){
        this.context = context;
        this.workingSpace = workingSpace;
    }

    @Override
    public void execute() {
        this.message = " ";

        try{
            DuoCall duoCall = new DuoCall(this.context);
            duoCall.callCaregiver((CaregiverModel) this.getParameter());
        }
        catch (Exception e) {
            CallRequester callRequester = new CallRequester(this.context, this.workingSpace);
            callRequester.setCurrentCallType(CallTypes.Duo);
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
    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
