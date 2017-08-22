package br.com.filipe1309.ichat.service;

import br.com.filipe1309.ichat.modelo.Mensagem;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatService {

    @POST("polling")
    void enviar(@Body Mensagem mensagem);

    @GET("polling")
    void ouvirMensagens();
}
