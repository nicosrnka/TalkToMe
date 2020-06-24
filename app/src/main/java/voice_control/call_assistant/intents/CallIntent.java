package voice_control.call_assistant.intents;

import android.content.Context;
import android.telecom.Call;

import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class CallIntent extends Intent {
    private Context context;

    public CallIntent(@NotNull String[] keywords, @NotNull Object parameters, @NotNull ICommand command) {
        super(keywords, parameters, command);
    }

    public CallIntent(@NotNull ICommand command, @NotNull Context context) {
        super(command);
        this.context = context;
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[]{
                "ruf",
                "ruf an",
                "erreiche",
                "kontaktiere"
        });
    }

    // Extract the name from the formulation and pass it on to the command.
    @Override
    protected void extractParameters(String formulation) throws Exception {
        formulation = formulation.toLowerCase();

        GeneralCall gc = new GeneralCall(this.context);

        for (String contact : gc.getContactNames()) {
            {
                contact = contact.toLowerCase();

                if (formulation.contains(contact)) {
                    this.command.setParameter(contact);
                    return;
                } else if (formulation.contains(contact.split(" ")[0])) {
                    this.command.setParameter(contact);
                    return;
                } else if ((contact.split(" ").length > 1) && (formulation.contains(contact.split(" ")[1]))) {
                    this.command.setParameter(contact);
                    return;
                }
            }
        }

        throw new Exception("Wen möchten Sie anrufen?");
    }
}