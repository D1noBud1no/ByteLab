package com.school.management.service;

import com.school.management.model.Corso;
import com.school.management.model.Esame;
import com.school.management.model.Percorso;
import com.school.management.repository.PercorsoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final PercorsoRepository percorsoRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void exportPercorsiEdEsami(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=percorsi_esami.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Percorsi & Esami");

            // Creazione dell'header
            createHeader(sheet);

            int rowNum = 1;

            List<Percorso> percorsi = percorsoRepository.findAll();
            for (Percorso percorso : percorsi) {
                if (percorso.getCorsi() != null) {
                    for (Corso corso : percorso.getCorsi()) {
                        if (corso.getEsami() != null) {
                            for (Esame esame : corso.getEsami()) {
                                Row row = sheet.createRow(rowNum++);
                                populateRow(row, percorso, corso, esame);
                            }
                        }
                    }
                }
            }

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la generazione del file Excel: " + e.getMessage(), e);
        }
    }

    private void createHeader(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Percorso");
        header.createCell(1).setCellValue("Corso");
        header.createCell(2).setCellValue("Esame");
        header.createCell(3).setCellValue("Data");
    }

    private void populateRow(Row row, Percorso percorso, Corso corso, Esame esame) {
        row.createCell(0).setCellValue(percorso.getNome() != null ? percorso.getNome() : "N/A");
        row.createCell(1).setCellValue(corso.getNome() != null ? corso.getNome() : "N/A");
        row.createCell(2).setCellValue(esame.getNome() != null ? esame.getNome() : "N/A");
        row.createCell(3).setCellValue(esame.getData() != null ? DATE_FORMATTER.format(esame.getData()) : "N/A");
    }
}
