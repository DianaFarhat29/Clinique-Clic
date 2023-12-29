package com.ProjetFinal.CarolineSDianaF.Interface;

import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentModel> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date);
}
