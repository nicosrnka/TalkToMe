package voice_control.call_assistant.commands;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

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

    public ImergencyCallCommand(@NotNull Context context, CaregiverModel caregiverForEmergency)
    {
        this.context = context;
        this.parameter = caregiverForEmergency;
    }

    @Override
    public void execute() {

        GeneralCall generalCall = new GeneralCall(this.context);

        try {
            generalCall.callCaregiver((CaregiverModel) this.getParameter());
        }
        catch (Exception e) {
            generalCall.callPerNumber((String) this.getParameter());
        }
    }

    @Override
    public String getMessage() {

        return "Hilferuf abgesetzt";
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
