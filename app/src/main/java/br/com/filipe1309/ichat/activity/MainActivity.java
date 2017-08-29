package br.com.filipe1309.ichat.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.filipe1309.ichat.app.ChatApplication;
import br.com.filipe1309.ichat.R;
import br.com.filipe1309.ichat.adapter.MensagemAdapter;
import br.com.filipe1309.ichat.callback.EnviarMensagemCallback;
import br.com.filipe1309.ichat.callback.OuvirMensagensCallback;
import br.com.filipe1309.ichat.component.ChatComponent;
import br.com.filipe1309.ichat.event.FailureEvent;
import br.com.filipe1309.ichat.event.MensagemEvent;
import br.com.filipe1309.ichat.modelo.Mensagem;
import br.com.filipe1309.ichat.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;
    @BindView(R.id.et_texto)
    EditText editText;
    @BindView(R.id.btn_enviar)
    Button button;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;
    @BindView(R.id.iv_avatar_usuario)
    ImageView avatar;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    @Inject
    InputMethodManager inputMethodManager;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        picasso.with(this).load("http://api.adorable.io/avatars/285/" + idDoCliente + ".png").into(avatar);

        if (savedInstanceState != null) {
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            mensagens = new ArrayList<>();
        }

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        ouvirMensagem(null);

        eventBus.register(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("mensagens", (ArrayList<Mensagem>) mensagens);
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem() {
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());

        editText.getText().clear();
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);

        listaDeMensagens.setAdapter(adapter);
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(eventBus, this));
    }

    @Subscribe
    public void lidarCom(FailureEvent event) {
        ouvirMensagem(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
