package br.com.filipe1309.ichat.app;

import android.app.Application;

import br.com.filipe1309.ichat.component.ChatComponent;
import br.com.filipe1309.ichat.component.DaggerChatComponent;

public class ChatApplication extends Application {

    private ChatComponent component;

    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent() {
        return component;
    }

}
