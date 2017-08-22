package br.com.filipe1309.ichat.callback;

import br.com.filipe1309.ichat.activity.MainActivity;
import br.com.filipe1309.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {
    private MainActivity activity;

    public OuvirMensagensCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()) {
            Mensagem mensagem = response.body();
            activity.colocaNaLista(mensagem);
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }
}
