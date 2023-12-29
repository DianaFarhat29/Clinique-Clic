package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.AppointmentService;
import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import com.ProjetFinal.CarolineSDianaF.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentModel> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }
}
