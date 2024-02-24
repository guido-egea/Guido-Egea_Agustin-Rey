package com.clinicaodontologica.service.impl;

import com.clinicaodontologica.dao.IDao;
import com.clinicaodontologica.entity.Odontologo;
import com.clinicaodontologica.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoDao.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoDao.listarTodos();
    }
}