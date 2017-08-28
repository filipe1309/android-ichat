package br.com.filipe1309.ichat.component;

import br.com.filipe1309.ichat.activity.MainActivity;
import br.com.filipe1309.ichat.adapter.MensagemAdapter;
import br.com.filipe1309.ichat.module.ChatModule;
import dagger.Component;

@Component(modules= ChatModule.class)
public interface ChatComponent {
    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);
}
