package voice_control.call_assistant.commands;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.calltypes.CallRequester;
import com.example.talktome.calltypes.CallTypes;
import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;

import voice_control.commands.ICommand;

public class ImergencyCallCommand implements ICommand {

    private Object parameter;

    private Context context;

    private WorkingSpace workingSpace;

    public ImergencyCallCommand(@NotNull Context context, @NotNull CaregiverModel caregiverForEmergency)
    {
        this.context = context;
        this.parameter = caregiverForEmergency;
    }

    @Override
    public void execute() {

        GeneralCall generalCall = new GeneralCall(this.context);
        generalCall.callCaregiver((CaregiverModel) this.getParameter());
    }

    @Override
    public String getMessage() {

        return "Hilferuf";
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
