package voice_control.call_assistant;

import android.graphics.drawable.Icon;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import voice_control.Assistant;
import voice_control.IAssistant;
import voice_control.IIntent;
import voice_control.call_assistant.commands.CallCommand;
import voice_control.call_assistant.commands.ImergencyCallCommand;
import voice_control.call_assistant.commands.WhatsAppCallCommand;
import voice_control.call_assistant.intents.CallIntent;
import voice_control.call_assistant.intents.ImergencyIntent;
import voice_control.call_assistant.intents.WhatsAppCallIntent;
import voice_control.commands.ICommand;

import android.content.Context;

import com.example.talktome.activities.WorkingSpace;
import com.example.talktome.models.CaregiverModel;

// This assistant processes telephone user intentions.
public class CallAssistant extends Assistant
{
    private WorkingSpace workingSpace;

    private CaregiverModel emergencyCaregiver;

    public CaregiverModel getEmergencyCaregiver(){
        return this.emergencyCaregiver;
    }

    public void setEmergencyCaregiver(@NotNull CaregiverModel caregiverModel){
        this.emergencyCaregiver = caregiverModel;
    }

    public CallAssistant(@NotNull Context context, @NotNull WorkingSpace workingSpace, CaregiverModel emergencyCaregiver)
    {
        this.intents = this.getDefaultIntents(context);
        this.workingSpace = workingSpace;
        this.emergencyCaregiver = emergencyCaregiver;
    }

    public CallAssistant(@NotNull IIntent[] intents)
    {
        super(intents);
    }

    private IIntent[] getDefaultIntents(Context context)
    {
        return new IIntent[] {
                new WhatsAppCallIntent(new WhatsAppCallCommand(context), context),
                new CallIntent(new CallCommand(context, workingSpace), context),
                new ImergencyIntent(new ImergencyCallCommand(context, this.getEmergencyCaregiver()))
        };
    }
}
