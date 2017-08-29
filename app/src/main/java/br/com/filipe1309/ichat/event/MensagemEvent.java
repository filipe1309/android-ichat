package br.com.filipe1309.ichat.event;

import br.com.filipe1309.ichat.modelo.Mensagem;

public class MensagemEvent {
    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
