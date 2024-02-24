package com.clinicaodontologica.service;

import com.clinicaodontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> listarOdontologos();
}