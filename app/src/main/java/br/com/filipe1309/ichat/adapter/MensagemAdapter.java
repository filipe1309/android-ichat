package br.com.filipe1309.ichat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.filipe1309.ichat.R;
import br.com.filipe1309.ichat.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    List<Mensagem> mensagens;
    private Activity activity;
    private int idDoCliente;

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    public MensagemAdapter(int idDoCliente, List<Mensagem> mensagens, Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
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

        ButterKnife.bind(this, linha);

        Mensagem mensagem = getItem(i);

        if (idDoCliente != mensagem.getId()) {
            linha.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getTexto());

        return linha;
    }
}
