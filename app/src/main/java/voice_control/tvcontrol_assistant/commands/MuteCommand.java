package voice_control.tvcontrol_assistant.commands;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.talktome.tv.Philips;

import org.jetbrains.annotations.NotNull;

import voice_control.commands.ICommand;

public class MuteCommand implements ICommand {
    private Context context;

    public MuteCommand(@NotNull Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = pref.getString("ip", "");

        Philips philips = new Philips(ip);
        philips.Mute();
    }

    @Override
    public String getMessage() {
        return " ";
    }

    @Override
    public Object getParameter() {
        return null;
    }

    @Override
    public void setParameter(Object parameter) {

    }
}
