package br.com.filipe1309.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.filipe1309.ichat.app.ChatApplication;
import br.com.filipe1309.ichat.R;
import br.com.filipe1309.ichat.adapter.MensagemAdapter;
import br.com.filipe1309.ichat.callback.EnviarMensagemCallback;
import br.com.filipe1309.ichat.callback.OuvirMensagensCallback;
import br.com.filipe1309.ichat.component.ChatComponent;
import br.com.filipe1309.ichat.modelo.Mensagem;
import br.com.filipe1309.ichat.service.ChatService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;
    private EditText editText;
    private Button button;
    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);

        //mensagens = Arrays.asList(new Mensagem(1, "Ola alunos de android"), new Mensagem(2, "oi"));
        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.et_texto);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        ouvirMensagem();

        button = (Button) findViewById(R.id.btn_enviar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
            }
        });
    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        ouvirMensagem();
    }

    public void ouvirMensagem() {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(this));
    }
}
