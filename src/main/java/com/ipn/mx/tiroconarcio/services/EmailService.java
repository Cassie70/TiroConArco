package com.ipn.mx.tiroconarcio.services;

public interface EmailService {
    void enviarEmail(String destinatario, String asunto, String mensaje);
    void enviarNotificacion(String texto);
}
