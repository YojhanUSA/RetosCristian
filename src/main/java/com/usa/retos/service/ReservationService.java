package com.usa.retos.service;

import com.usa.retos.model.Reservation;
import com.usa.retos.reports.CounterClients;
import com.usa.retos.reports.StatusReservation;
import com.usa.retos.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
/**
 * Esta clase es el servicio de Reservation
 */
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Este metodo obtiene toda la lista de Reservaciones
     * @return
     */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    /**
     * Este metodo obtiene una reservacion por id
     * @param idReservation
     * @return
     */
    public Optional<Reservation> getReservation(int idReservation) {
        return reservationRepository.getReservation(idReservation);
    }

    /**
     * Este metodo guarda una reservacion
     * @param reservation
     * @return
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> tmpReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (tmpReservation.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    /**
     * Este metodo actualiza una reservacion
     * @param reservation
     * @return
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> tmpReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (!tmpReservation.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    tmpReservation.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    tmpReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    tmpReservation.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(tmpReservation.get());
                return tmpReservation.get();

            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * Este metodo elimina una reservacion
     * @param idReservation
     * @return
     */
    public boolean deleteReservation(int idReservation) {
        Boolean aBoolean = getReservation(idReservation).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     * Este metodo obtiene el estado de la reservacion
     * @return
     */
    public StatusReservation getReservationStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationByStatus("completed");
        List<Reservation>cancelled=reservationRepository.getReservationByStatus("cancelled");
        return new StatusReservation(completed.size(), cancelled.size());
    }

    /**
     * Este metodo obtiene la fecha de la reservacion
     * @param dateA
     * @param dateB
     * @return
     */
    public List<Reservation> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();

        try {
            aDate = parser.parse(dateA);
            bDate = parser.parse(dateB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }
        if(aDate.before(bDate)){
            return reservationRepository.getReservationPeriod(aDate, bDate);
        }else{
            return new ArrayList<>();
        }
    }

    /**
     * Esta clase obtiene los clientes
     * @return
     */
    public List<CounterClients> getTopClients(){
        return reservationRepository.getTopClients();
    }
}
