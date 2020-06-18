package com.example.talktome.calltypes;

import android.content.Context;

public class CallFactory {
    public static GeneralCall getCall(CallTypes callType, Context context) {
        switch (callType) {
            case WhatsApp:
                return new WhatsAppCall(context);
            case Duo:
                return new DuoCall(context);
            default:
                return new GeneralCall(context);
        }
    }
}
