package voice_control.call_assistant.intents;

import android.content.Context;

import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.calltypes.WhatsAppCall;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import voice_control.Intent;
import voice_control.commands.ICommand;

public class WhatsAppCallIntent extends Intent {

    private Context context;

    private ICommand command;

    public WhatsAppCallIntent(@NotNull ICommand command, @NotNull Context context) {
        super(command);
        this.context = context;
        this.command = command;
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[] {
                "whatsapp",
        });
    }

    @Override
    protected void extractParameters(String formulation) throws Exception {

        formulation = formulation.toLowerCase();

        GeneralCall gc = new GeneralCall(this.context);
        WhatsAppCall whatsAppCall = new WhatsAppCall(this.context);

        for (String contact : gc.getContactNames()) {
            {
                contact = contact.toLowerCase();

                if (formulation.contains(contact)) {
                    ContactModel contactToCall = whatsAppCall.getContactsByName(contact).get(0);
                    this.command.setParameter(contactToCall);
                    return;
                } else if (formulation.contains(contact.split(" ")[0])) {
                    ContactModel contactToCall = whatsAppCall.getContactsByName(contact).get(0);
                    this.command.setParameter(contactToCall);
                    return;
                } else if ((contact.split(" ").length > 1) && (formulation.contains(contact.split(" ")[1]))) {
                    ContactModel contactToCall = whatsAppCall.getContactsByName(contact).get(0);
                    this.command.setParameter(contactToCall);
                    return;
                }
            }
        }

        throw new Exception("Wen möchten Sie anrufen?");
    }
}
