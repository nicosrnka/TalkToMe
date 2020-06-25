package voice_control.call_assistant.intents;

import android.content.Context;

import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.helper.GetCaregiverFromBackend;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class ImergencyIntent extends Intent {

    private Context context;

    public ImergencyIntent(@NotNull String[] keywords, @NotNull Object parameters, @NotNull ICommand command)
    {
        super(keywords, parameters, command);
    }

    public ImergencyIntent(@NotNull ICommand command, @NotNull Context context)
    {
        super(command);
        this.context = context;
    }

    @Override
    protected void setDefaultKeywords()
    {
        this.setKeywords(new String[]{
                "hilfe",
        });
    }

    // Extract name from formulation and give to command.
    @Override
    protected void extractParameters(String formulation) throws Exception {

        GetCaregiverFromBackend getCaregiverFromBackend = new GetCaregiverFromBackend(this.context);
        List<CaregiverModel> caregiverModels = getCaregiverFromBackend.GetCaregiver();

        for(CaregiverModel caregiverModel: caregiverModels) {
            if(formulation.contains(caregiverModel.getLastName().toLowerCase())){
                this.command.setParameter(caregiverModel);
                return;
            }
            else if(formulation.contains(caregiverModel.getFirstName().toLowerCase())){
                this.command.setParameter(caregiverModel);
                return;
            }
        }

        this.command.setParameter("144");
    }
}
