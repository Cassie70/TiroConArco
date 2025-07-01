package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.services.PdfService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream generarPdfArquero(Arquero a) {
        Document document = new Document();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, salida);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
