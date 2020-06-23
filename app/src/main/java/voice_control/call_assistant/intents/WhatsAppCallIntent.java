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

    public WhatsAppCallIntent(@NotNull ICommand command, @NotNull Context context) {
        super(command);
        this.context = context;
    }

    @Override
    protected void setDefaultKeywords() {
        this.setKeywords(new String[] {
                "whatsapp",
        });
    }

    @Override
    protected void extractParameters(String formulation) throws Exception {

        // In order to get searched contact.
        GeneralCall gc = new GeneralCall(this.context);

        // Get contact to call from formulation.
        for (String contact: gc.getContactNames()) {
            if(formulation.contains(contact)){

                WhatsAppCall whatsAppCall = new WhatsAppCall(this.context);

                ContactModel contactToCall = whatsAppCall.getContactsByName(contact).get(0);

                this.setParameters(contactToCall);

                return;
            }
        }

        throw new Exception("Wen möchten Sie anrufen?");
    }
}
