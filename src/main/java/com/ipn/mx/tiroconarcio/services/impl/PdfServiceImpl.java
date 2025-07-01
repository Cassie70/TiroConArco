package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.Arquero;
import com.ipn.mx.tiroconarcio.domain.models.Arco;
import com.ipn.mx.tiroconarcio.domain.models.Competencia;
import com.ipn.mx.tiroconarcio.domain.models.Entrenamiento;
import com.ipn.mx.tiroconarcio.services.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream generarPdfArquero(Arquero a) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, salida);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.DARK_GRAY);
            Font subtituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            // Imagen si existe
            if (a.getImage() != null) {
                Image imagen = Image.getInstance(a.getImage());
                imagen.scaleToFit(150, 150);
                imagen.setAlignment(Element.ALIGN_CENTER);
                document.add(imagen);
                document.add(new Paragraph(" "));
            }

            // Título
            Paragraph titulo = new Paragraph("Reporte de Arquero", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" "));

            // Tabla de datos personales
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[]{1, 2});

            tabla.addCell(getCelda("Nombre:", subtituloFont));
            tabla.addCell(getCelda(a.getNombre(), normalFont));

            tabla.addCell(getCelda("Apellido:", subtituloFont));
            tabla.addCell(getCelda(a.getApellido(), normalFont));

            tabla.addCell(getCelda("Marca Personal:", subtituloFont));
            tabla.addCell(getCelda(a.getMarcaPersonal() != null ? a.getMarcaPersonal().toString() : "N/A", normalFont));

            tabla.addCell(getCelda("Categoría:", subtituloFont));
            tabla.addCell(getCelda(a.getCategoria(), normalFont));

            tabla.addCell(getCelda("Asociación:", subtituloFont));
            tabla.addCell(getCelda(a.getAsociación(), normalFont));

            document.add(tabla);

            document.add(Chunk.NEWLINE);

            // Sección Arcos
            Paragraph arcosTitulo = new Paragraph("Arcos", subtituloFont);
            document.add(arcosTitulo);
            document.add(new LineSeparator());

            if (a.getArcos().isEmpty()) {
                document.add(new Paragraph("Sin arcos registrados.", normalFont));
            } else {
                PdfPTable arcosTabla = new PdfPTable(4);
                arcosTabla.setWidthPercentage(100);
                arcosTabla.setWidths(new int[]{2, 2, 1, 1});

                arcosTabla.addCell(getCelda("Tipo", subtituloFont));
                arcosTabla.addCell(getCelda("Marca", subtituloFont));
                arcosTabla.addCell(getCelda("Libraje", subtituloFont));
                arcosTabla.addCell(getCelda("Apertura", subtituloFont));

                for (Arco arco : a.getArcos()) {
                    arcosTabla.addCell(getCelda(arco.getTipo(), normalFont));
                    arcosTabla.addCell(getCelda(arco.getMarca(), normalFont));
                    arcosTabla.addCell(getCelda(String.valueOf(arco.getLibraje()), normalFont));
                    arcosTabla.addCell(getCelda(String.valueOf(arco.getApertura()), normalFont));
                }
                document.add(arcosTabla);
            }

            document.add(Chunk.NEWLINE);

            // Sección Entrenamientos
            Paragraph entrenamientosTitulo = new Paragraph("Entrenamientos", subtituloFont);
            document.add(entrenamientosTitulo);
            document.add(new LineSeparator());

            if (a.getEntrenamientos().isEmpty()) {
                document.add(new Paragraph("Sin entrenamientos registrados.", normalFont));
            } else {
                PdfPTable entrenamientosTabla = new PdfPTable(1);
                entrenamientosTabla.setWidthPercentage(100);
                entrenamientosTabla.addCell(getCelda("Fecha", subtituloFont));

                for (Entrenamiento e : a.getEntrenamientos()) {
                    entrenamientosTabla.addCell(getCelda(e.getFecha().toString(), normalFont));
                }
                document.add(entrenamientosTabla);
            }

            document.add(Chunk.NEWLINE);

            // Sección Competencias
            Paragraph competenciasTitulo = new Paragraph("Competencias", subtituloFont);
            document.add(competenciasTitulo);
            document.add(new LineSeparator());

            if (a.getCompetencias().isEmpty()) {
                document.add(new Paragraph("Sin competencias registradas.", normalFont));
            } else {
                PdfPTable competenciasTabla = new PdfPTable(3);
                competenciasTabla.setWidthPercentage(100);
                competenciasTabla.setWidths(new int[]{3, 2, 2});

                competenciasTabla.addCell(getCelda("Nombre", subtituloFont));
                competenciasTabla.addCell(getCelda("Fecha Inicio", subtituloFont));
                competenciasTabla.addCell(getCelda("Fecha Término", subtituloFont));

                for (Competencia c : a.getCompetencias()) {
                    competenciasTabla.addCell(getCelda(c.getNombre(), normalFont));
                    competenciasTabla.addCell(getCelda(c.getFechaInicio().toString(), normalFont));
                    competenciasTabla.addCell(getCelda(c.getFechaTermino().toString(), normalFont));
                }
                document.add(competenciasTabla);
            }

            document.close();
            return new ByteArrayInputStream(salida.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF de arquero: " + e.getMessage(), e);
        }
    }

    private PdfPCell getCelda(String texto, Font fuente) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setPadding(5);
        return celda;
    }
}
