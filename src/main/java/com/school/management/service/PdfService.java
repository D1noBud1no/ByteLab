package com.school.management.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.school.management.model.Iscrizione;
import com.school.management.model.Studente;
import com.school.management.repository.IscrizioneRepository;
import com.school.management.repository.StudenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final StudenteRepository studenteRepository;
    private final IscrizioneRepository iscrizioneRepository;

    public void exportProfiloStudente(Long studenteId, HttpServletResponse response) throws IOException {
        Studente studente = studenteRepository.findById(studenteId).orElse(null);
        if (studente == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Studente non trovato");
            return;
        }

        List<Iscrizione> iscrizioni = iscrizioneRepository.findByStudenteId(studenteId);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=profilo-studente-" + studenteId + ".pdf");

        try (Document document = new Document()) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            generatePdfContent(document, studente, iscrizioni);
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la generazione del PDF: " + e.getMessage(), e);
        }
    }

    private void generatePdfContent(Document document, Studente studente, List<Iscrizione> iscrizioni) throws DocumentException {
        Font titoloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLUE);
        Font normaleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

        // Intestazione del PDF
        document.add(new Paragraph("Profilo Studente", titoloFont));
        document.add(new Paragraph(" "));

        // Dettagli dello studente
        document.add(new Paragraph("ID: " + studente.getId(), normaleFont));
        document.add(new Paragraph("Nome: " + studente.getNome(), normaleFont));
        document.add(new Paragraph("Cognome: " + studente.getCognome(), normaleFont));
        document.add(new Paragraph("Email: " + studente.getEmail(), normaleFont));
        document.add(new Paragraph("Data di nascita: " + studente.getDataNascita(), normaleFont));
        document.add(new Paragraph(" "));

        // Dettagli delle iscrizioni
        document.add(new Paragraph("Iscrizioni:", titoloFont));
        document.add(new Paragraph(" "));

        for (Iscrizione i : iscrizioni) {
            document.add(new Paragraph("- Corso: " + (i.getCorso() != null ? i.getCorso().getNome() : "N/A"), normaleFont));
            document.add(new Paragraph("  Data iscrizione: " + (i.getDataIscrizione() != null ? i.getDataIscrizione() : "N/A"), normaleFont));
            document.add(new Paragraph("  Stato: " + (i.getStato() != null ? i.getStato() : "N/A"), normaleFont));
            document.add(new Paragraph("  Voto: " + (i.getVoto() != null ? i.getVoto() : "N/A"), normaleFont));
            document.add(new Paragraph(" "));
        }
    }
}
