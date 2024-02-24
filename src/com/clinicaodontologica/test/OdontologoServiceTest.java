package com.clinicaodontologica.test;

import com.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.clinicaodontologica.dao.impl.OdontologoDaoMemoria;
import com.clinicaodontologica.entity.Odontologo;
import com.clinicaodontologica.service.impl.OdontologoService;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OdontologoServiceTest {

    private OdontologoService odontologoService;



    @Test
    void deberiaRegistrarUnOdontologoYRetornarUnNumeroDeMatriculaEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo(2, "Lucas", "perez");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        Assertions.assertTrue(odontologoRegistrado.getNumeroMatricula() != 0);
    }

    @Test
    void deberiaRegistrarUnOdontologoYRetornarUnNumeroDeMatriculaEnMemoria() {
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        Odontologo odontologo = new Odontologo(5, "Mario", "perez");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        Assertions.assertTrue(odontologoRegistrado.getNumeroMatricula() != 0);
    }

    @Test
    void deberiaListarOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());

        List<Odontologo> odontologos = odontologoService.listarOdontologos();

        Assertions.assertNotNull(odontologos);
        Assertions.assertFalse(odontologos.isEmpty());
    }

    @Test
    void deberiaListarOdontologosEnMemoria() {
        List<Odontologo> odontologoRepository = new ArrayList<>();
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(odontologoRepository));

        Odontologo odontologo1 = new Odontologo(2, "Nuria", "varela");
        Odontologo odontologo2 = new Odontologo(2, "lucas", "suculini");

        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarOdontologos();

        Assertions.assertNotNull(odontologos);
        Assertions.assertEquals(2, odontologos.size());
    }
}