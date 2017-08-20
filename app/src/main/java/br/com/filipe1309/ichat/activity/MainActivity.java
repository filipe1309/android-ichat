package br.com.filipe1309.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.filipe1309.ichat.R;
import br.com.filipe1309.ichat.adapter.MensagemAdapter;
import br.com.filipe1309.ichat.modelo.Mensagem;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);

        List<Mensagem> mensagens = Arrays.asList(new Mensagem(1, "Ola alunos de android"), new Mensagem(2, "oi"));


        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);
    }
}
