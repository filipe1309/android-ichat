package br.com.filipe1309.ichat.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.filipe1309.ichat.R;
import br.com.filipe1309.ichat.modelo.Mensagem;

public class MensagemAdapter extends BaseAdapter {

    List<Mensagem> mensagens;
    private Activity activity;

    public MensagemAdapter(List<Mensagem> mensagens, Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha =  activity.getLayoutInflater().inflate(R.layout.mensagem, viewGroup, false);

        TextView texto = (TextView) linha.findViewById(R.id.tv_texto);

        Mensagem mensagem = getItem(i);

        texto.setText(mensagem.getTexto());

    }
}
