package com.clinicaodontologica;

import com.clinicaodontologica.dao.IDao;
import com.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.clinicaodontologica.dao.impl.OdontologoDaoMemoria;
import com.clinicaodontologica.dbconnection.H2Connection;
import com.clinicaodontologica.entity.Odontologo;
import com.clinicaodontologica.service.impl.OdontologoService;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        PropertyConfigurator.configure("log4j.properties");


        IDao<Odontologo> odontologoDaoH2 = new OdontologoDaoH2();
        IDao<Odontologo> odontologoDaoMemoria = new OdontologoDaoMemoria(new ArrayList<>());

        OdontologoService odontologoServiceH2 = new OdontologoService(odontologoDaoH2);
        OdontologoService odontologoServiceMemoria = new OdontologoService(odontologoDaoMemoria);


        try {
            H2Connection.getConnection().createStatement().execute("RUNSCRIPT FROM 'create.sql'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Odontologo odontologoH2 = new Odontologo( 2, "Lucas", "martinez");
        Odontologo odontologoRegistradoH2 = odontologoServiceH2.registrarOdontologo(odontologoH2);
        System.out.println("Odontólogo registrado en H2: " + odontologoRegistradoH2);

        List<Odontologo> odontologosH2 = odontologoServiceH2.listarOdontologos();
        System.out.println("Listado de odontólogos en H2: " + odontologosH2);


        Odontologo odontologoMemoria = new Odontologo(3, "Ramiro", "simon");
        Odontologo odontologoRegistradoMemoria = odontologoServiceMemoria.registrarOdontologo(odontologoMemoria);
        System.out.println("Odontólogo registrado en Memoria: " + odontologoRegistradoMemoria);

        List<Odontologo> odontologosMemoria = odontologoServiceMemoria.listarOdontologos();
        System.out.println("Listado de odontólogos en Memoria: " + odontologosMemoria);
    }
}