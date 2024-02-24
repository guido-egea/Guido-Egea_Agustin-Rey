package com.clinicaodontologica.dao.impl;

import com.clinicaodontologica.dao.IDao;
import com.clinicaodontologica.entity.Odontologo;
import org.apache.log4j.Logger;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologoRepository;

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        int matricula = odontologoRepository.size() + 1;
        odontologo.setNumeroMatricula(matricula);

        Odontologo odontologoGuardado = new Odontologo(matricula, odontologo.getNombre(), odontologo.getApellido());
        odontologoRepository.add(odontologoGuardado);

        LOGGER.info("Odontólogo guardado en memoria: " + odontologoGuardado);
        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de odontólogos en memoria: " + odontologoRepository);
        return odontologoRepository;
    }
}