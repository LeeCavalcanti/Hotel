/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.servlets;

import Hotel.beans.Apartamento;
import Hotel.beans.Estacionamento;
import Hotel.beans.Hospede;
import Hotel.beans.Recepcionista;
import Hotel.beans.Reserva;
import Hotel.dao.GenericDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marciano
 */
public class NovaReserva implements Tarefa{

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        Long idApt = Long.parseLong(req.getParameter("apartamento"));
        Long idHospede = Long.parseLong(req.getParameter("hospede"));
        Long idEst = Long.parseLong(req.getParameter("estacionamento"));
        Long idRecep = Long.parseLong(req.getParameter("recepcionista"));
        
        Apartamento apt = new GenericDAO<Apartamento>(Apartamento.class).getById(idApt);
        Hospede hospede = new GenericDAO<Hospede>(Hospede.class).getById(idHospede);
        Estacionamento est = new GenericDAO<Estacionamento>(Estacionamento.class).getById(idEst);
        Recepcionista recep = new GenericDAO<Recepcionista>(Recepcionista.class).getById(idRecep);
        
        Reserva reserva = new Reserva();
        reserva.setApartamento(apt);
        reserva.setHospede(hospede);
        reserva.setEstacionamento(est);
        reserva.setRecepcionista(recep);
        reserva.setStatus(true);
        
        try {
            new GenericDAO<Reserva>(Reserva.class).adiciona(reserva);
        } catch (SQLException ex) {
            Logger.getLogger(NovaReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "WEB-INF/paginas/dashboard.jsp";
    }
    
}
