package br.com.filipe1309.ichat.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import br.com.filipe1309.ichat.activity.MainActivity;
import br.com.filipe1309.ichat.event.MensagemEvent;
import br.com.filipe1309.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {
    private Context context;
    private EventBus eventBus;

    public OuvirMensagensCallback(EventBus eventBus, Context context) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()) {
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
    }
}
