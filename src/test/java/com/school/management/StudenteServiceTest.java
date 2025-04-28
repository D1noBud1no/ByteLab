package com.school.management;

import com.school.management.dto.StudenteDto;
import com.school.management.model.Studente;
import com.school.management.repository.StudenteRepository;
import com.school.management.service.StudenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudenteServiceTest {

    private StudenteRepository studenteRepository;
    private StudenteService studenteService;

    @BeforeEach
    void setUp() {
        studenteRepository = Mockito.mock(StudenteRepository.class);
        studenteService = new StudenteService(studenteRepository);
    }

    @Test
    void creaStudente_deveRestituireDTOSalvato() {
        // Arrange
        StudenteDto dto = StudenteDto.builder()
                .nome("Luca")
                .cognome("Rossi")
                .email("luca.rossi@example.com")
                .dataNascita(LocalDate.of(2000, 5, 12))
                .build();

        Studente savedEntity = Studente.builder()
                .id(1L)
                .nome("Luca")
                .cognome("Rossi")
                .email("luca.rossi@example.com")
                .dataNascita(LocalDate.of(2000, 5, 12))
                .build();

        when(studenteRepository.save(any(Studente.class))).thenReturn(savedEntity);

        // Act
        StudenteDto result = studenteService.creaStudente(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Luca", result.getNome());
        assertEquals("Rossi", result.getCognome());
        assertEquals("luca.rossi@example.com", result.getEmail());
        assertEquals(LocalDate.of(2000, 5, 12), result.getDataNascita());
        verify(studenteRepository, times(1)).save(any(Studente.class));
    }

    @Test
    void getAll_deveRestituireListaDTO() {
        // Arrange
        List<Studente> studenti = List.of(
                Studente.builder().id(1L).nome("Luca").cognome("Rossi").email("luca@example.com").dataNascita(LocalDate.of(2000, 5, 12)).build(),
                Studente.builder().id(2L).nome("Giulia").cognome("Verdi").email("giulia@example.com").dataNascita(LocalDate.of(2001, 6, 10)).build()
        );

        when(studenteRepository.findAll()).thenReturn(studenti);

        // Act
        List<StudenteDto> result = studenteService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Luca", result.get(0).getNome());
        assertEquals("Giulia", result.get(1).getNome());
        verify(studenteRepository, times(1)).findAll();
    }

    @Test
    void getById_conIdEsistente_deveRestituireDTO() {
        // Arrange
        Studente studente = Studente.builder()
                .id(1L)
                .nome("Luca")
                .cognome("Rossi")
                .email("luca@example.com")
                .dataNascita(LocalDate.of(2000, 5, 12))
                .build();

        when(studenteRepository.findById(1L)).thenReturn(Optional.of(studente));

        // Act
        StudenteDto result = studenteService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Luca", result.getNome());
        verify(studenteRepository, times(1)).findById(1L);
    }

    @Test
    void getById_conIdInesistente_deveRestituireNull() {
        // Arrange
        when(studenteRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        StudenteDto result = studenteService.getById(99L);

        // Assert
        assertNull(result);
        verify(studenteRepository, times(1)).findById(99L);
    }

    @Test
    void aggiornaStudente_conIdValido_deveAggiornareERestituireDTO() {
        // Arrange
        StudenteDto dto = StudenteDto.builder()
                .nome("Marco")
                .cognome("Neri")
                .email("marco@example.com")
                .dataNascita(LocalDate.of(2001, 1, 1))
                .build();

        when(studenteRepository.existsById(1L)).thenReturn(true);
        when(studenteRepository.save(any(Studente.class))).thenAnswer(invocation -> {
            Studente arg = invocation.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        // Act
        StudenteDto result = studenteService.aggiornaStudente(1L, dto);

        // Assert
        assertNotNull(result);
        assertEquals("Marco", result.getNome());
        assertEquals("Neri", result.getCognome());
        assertEquals("marco@example.com", result.getEmail());
        verify(studenteRepository).existsById(1L);
        verify(studenteRepository).save(any(Studente.class));
    }

    @Test
    void aggiornaStudente_conIdNonEsistente_deveRestituireNull() {
        // Arrange
        StudenteDto dto = StudenteDto.builder().build();
        when(studenteRepository.existsById(100L)).thenReturn(false);

        // Act
        StudenteDto result = studenteService.aggiornaStudente(100L, dto);

        // Assert
        assertNull(result);
        verify(studenteRepository).existsById(100L);
        verify(studenteRepository, never()).save(any());
    }

    @Test
    void eliminaStudente_deveChiamareDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        studenteService.eliminaStudente(id);

        // Assert
        verify(studenteRepository, times(1)).deleteById(id);
    }
}
