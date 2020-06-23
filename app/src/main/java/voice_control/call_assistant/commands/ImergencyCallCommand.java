package voice_control.call_assistant.commands;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;

import voice_control.commands.ICommand;

public class ImergencyCallCommand implements ICommand {

    private Object caregiverModel;

    private Context context;

    public ImergencyCallCommand(@NotNull Context context, @NotNull CaregiverModel caregiverForEmergency)
    {
        this.context = context;
        this.caregiverModel = caregiverForEmergency;
    }

    @Override
    public void execute() {
        GeneralCall generalCall = new GeneralCall(this.context);
        generalCall.callCaregiver((CaregiverModel) this.caregiverModel);
    }

    @Override
    public String getMessage() {

        return "Hilferuf";
    }

    @Override
    public Object getParameter() {
        return this.caregiverModel;
    }

    @Override
    public void setParameter(Object parameter) {
        this.caregiverModel = parameter;
    }
}
