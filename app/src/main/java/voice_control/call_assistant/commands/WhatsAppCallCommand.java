package voice_control.call_assistant.commands;

import android.content.Context;
import android.view.contentcapture.ContentCaptureCondition;

import com.example.talktome.calltypes.WhatsAppCall;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import voice_control.commands.ICommand;

public class WhatsAppCallCommand implements ICommand {
    private String message;

    private Object parameter;

    private Context context;

    public WhatsAppCallCommand(@NotNull Context context)
    {
        this.context = context;
    }

    @Override
    public void execute() {
        WhatsAppCall whatsAppCall = new WhatsAppCall(this.context);
        whatsAppCall.callContact((ContactModel) this.getParameter());
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
