package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.domain.models.*;
import com.ipn.mx.tiroconarcio.services.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream generarPdfArquero(Arquero a) {
        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, salida);
            document.open();

            // Colores suaves
            BaseColor headerColor = new BaseColor(173, 216, 230); // Azul claro
            BaseColor rowColor = new BaseColor(240, 240, 240); // Gris claro

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
            Font subtituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            if (a.getImage() != null) {
                Image imagen = Image.getInstance(a.getImage());
                imagen.scaleToFit(120, 120);
                imagen.setAlignment(Element.ALIGN_CENTER);
                document.add(imagen);
                document.add(new Paragraph(" "));
            }

            Paragraph titulo = new Paragraph("Reporte de Arquero", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            // Datos personales
            PdfPTable tablaDatos = new PdfPTable(2);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.setSpacingBefore(10f);
            tablaDatos.setWidths(new int[]{1, 3});

            addRow(tablaDatos, "Nombre", a.getNombre(), subtituloFont, normalFont, headerColor, rowColor);
            addRow(tablaDatos, "Apellido", a.getApellido(), subtituloFont, normalFont, headerColor, rowColor);
            addRow(tablaDatos, "Marca Personal", a.getMarcaPersonal() != null ? a.getMarcaPersonal().toString() : "N/A", subtituloFont, normalFont, headerColor, rowColor);
            addRow(tablaDatos, "Categoría", a.getCategoria(), subtituloFont, normalFont, headerColor, rowColor);
            addRow(tablaDatos, "Asociación", a.getAsociación(), subtituloFont, normalFont, headerColor, rowColor);

            document.add(tablaDatos);
            document.add(Chunk.NEWLINE);

            // Arcos
            agregarArcos(document, a, subtituloFont, normalFont, headerColor);

            // Competencias
            agregarCompetencias(document, a, subtituloFont, normalFont, headerColor);

            // Entrenamientos con distancias y series
            agregarEntrenamientos(document, a, subtituloFont, normalFont, headerColor);

            document.close();
            return new ByteArrayInputStream(salida.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF: " + e.getMessage(), e);
        }
    }

    private void agregarArcos(Document document, Arquero a, Font subtituloFont, Font normalFont, BaseColor headerColor) throws DocumentException {
        Paragraph arcosTitulo = new Paragraph("Arcos", subtituloFont);
        document.add(arcosTitulo);
        document.add(new LineSeparator());

        if (a.getArcos().isEmpty()) {
            document.add(new Paragraph("Sin arcos registrados.", normalFont));
        } else {
            PdfPTable arcosTabla = new PdfPTable(4);
            arcosTabla.setWidthPercentage(100);
            arcosTabla.setSpacingBefore(5f);
            arcosTabla.setWidths(new float[]{2, 2, 1, 1});

            addHeader(arcosTabla, new String[]{"Tipo", "Marca", "Libraje", "Apertura"}, headerColor);

            for (Arco arco : a.getArcos()) {
                arcosTabla.addCell(new PdfPCell(new Phrase(arco.getTipo(), normalFont)));
                arcosTabla.addCell(new PdfPCell(new Phrase(arco.getMarca(), normalFont)));
                arcosTabla.addCell(new PdfPCell(new Phrase(String.valueOf(arco.getLibraje()), normalFont)));
                arcosTabla.addCell(new PdfPCell(new Phrase(String.valueOf(arco.getApertura()), normalFont)));
            }
            document.add(arcosTabla);
        }
        document.add(Chunk.NEWLINE);
    }

    private void agregarCompetencias(Document document, Arquero a, Font subtituloFont, Font normalFont, BaseColor headerColor) throws DocumentException {
        Paragraph competenciasTitulo = new Paragraph("Competencias", subtituloFont);
        document.add(competenciasTitulo);
        document.add(new LineSeparator());

        if (a.getCompetencias().isEmpty()) {
            document.add(new Paragraph("Sin competencias registradas.", normalFont));
        } else {
            PdfPTable compTabla = new PdfPTable(3);
            compTabla.setWidthPercentage(100);
            compTabla.setSpacingBefore(5f);
            compTabla.setWidths(new float[]{3, 2, 2});

            addHeader(compTabla, new String[]{"Nombre", "Fecha Inicio", "Fecha Término"}, headerColor);

            for (Competencia c : a.getCompetencias()) {
                compTabla.addCell(new PdfPCell(new Phrase(c.getNombre(), normalFont)));
                compTabla.addCell(new PdfPCell(new Phrase(c.getFechaInicio().toString(), normalFont)));
                compTabla.addCell(new PdfPCell(new Phrase(c.getFechaTermino().toString(), normalFont)));
            }
            document.add(compTabla);
        }
        document.add(Chunk.NEWLINE);
    }

    private void agregarEntrenamientos(Document document, Arquero a, Font subtituloFont, Font normalFont, BaseColor headerColor) throws DocumentException {
        Paragraph entrenamientosTitulo = new Paragraph("Entrenamientos", subtituloFont);
        document.add(entrenamientosTitulo);
        document.add(new LineSeparator());

        if (a.getEntrenamientos().isEmpty()) {
            document.add(new Paragraph("Sin entrenamientos registrados.", normalFont));
        } else {
            for (Entrenamiento e : a.getEntrenamientos()) {
                Paragraph entrenamientoFecha = new Paragraph("Fecha: " + e.getFecha(), normalFont);
                entrenamientoFecha.setSpacingBefore(5f);
                document.add(entrenamientoFecha);

                if (e.getDistancias().isEmpty()) {
                    document.add(new Paragraph("  Sin distancias registradas.", normalFont));
                } else {
                    for (Distancia d : e.getDistancias()) {
                        Paragraph distanciaParrafo = new Paragraph("  Distancia: " + d.getMetros() + " metros", normalFont);
                        distanciaParrafo.setSpacingBefore(3f);
                        document.add(distanciaParrafo);

                        if (d.getSeries().isEmpty()) {
                            document.add(new Paragraph("    Sin series registradas.", normalFont));
                        } else {
                            PdfPTable serieTabla = new PdfPTable(6);
                            serieTabla.setWidthPercentage(100);
                            serieTabla.setWidths(new float[]{1, 1, 1, 1, 1, 1});
                            serieTabla.setSpacingBefore(2f);

                            addHeader(serieTabla, new String[]{"F1", "F2", "F3", "F4", "F5", "F6"}, headerColor);

                            for (Serie s : d.getSeries()) {
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha1()), normalFont)));
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha2()), normalFont)));
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha3()), normalFont)));
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha4()), normalFont)));
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha5()), normalFont)));
                                serieTabla.addCell(new PdfPCell(new Phrase(String.valueOf(s.getFlecha6()), normalFont)));
                            }
                            document.add(serieTabla);
                        }
                    }
                }
                document.add(Chunk.NEWLINE);
            }
        }
    }

    private void addHeader(PdfPTable table, String[] headers, BaseColor bgColor) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK)));
            headerCell.setBackgroundColor(bgColor);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(5);
            table.addCell(headerCell);
        }
    }

    private void addRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont, BaseColor labelColor, BaseColor valueColor) {
        PdfPCell cellLabel = new PdfPCell(new Phrase(label, labelFont));
        cellLabel.setBackgroundColor(labelColor);
        cellLabel.setPadding(5);
        table.addCell(cellLabel);

        PdfPCell cellValue = new PdfPCell(new Phrase(value, valueFont));
        cellValue.setBackgroundColor(valueColor);
        cellValue.setPadding(5);
        table.addCell(cellValue);
    }
}
