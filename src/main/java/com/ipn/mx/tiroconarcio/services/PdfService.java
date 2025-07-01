package com.ipn.mx.tiroconarcio.services;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;

import java.io.ByteArrayInputStream;

public interface PdfService {
    public ByteArrayInputStream generarPdfArquero(Arquero a);
}
