package com.backend.clinicaodontologica.service.impl;


import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;

import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest{

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnOdontologoDeNombreLuciano_yRetornarSuId() {
        //arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12345", "Luciano", "Reyes");

        //act
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        //assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Luciano", odontologoSalidaDto.getNombre());

    }

    @Test
    @Order(2)
    void deberiaEliminarseElOdontologoConId1() {


        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }


    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDeOdontologos() {
        List<OdontologoSalidaDto> odontologo = odontologoService.listarOdontologos();

        assertTrue(odontologo.isEmpty());
    }


}