package voice_control.call_assistant.intents;

import android.content.Context;

import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.calltypes.WhatsAppCall;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class DuoCallIntent extends Intent {

    private Context context;

    private ICommand command;

    public DuoCallIntent(@NotNull ICommand command, @NotNull Context context) {
        super(command);

        this.context = context;
        this.command = command;
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[]{
                "duo",
        });
    }

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
