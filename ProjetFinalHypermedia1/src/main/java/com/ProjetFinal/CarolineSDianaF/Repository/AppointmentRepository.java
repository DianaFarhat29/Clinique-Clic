package com.ProjetFinal.CarolineSDianaF.Repository;

import com.ProjetFinal.CarolineSDianaF.Models.AppointmentModel;
import com.ProjetFinal.CarolineSDianaF.Models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    // Standard methods of the JpaRepository already included (save, findById, deleteById, etc.)

    // Method to find appointements by patient id
    @Query("SELECT a FROM AppointmentModel a WHERE a.patient.id = :patientId AND a.dateTime > :currentDateTime")
    List<AppointmentModel> findUpcomingAppointmentsByPatientId(Long patientId, LocalDateTime currentDateTime);

    // Find all appointments for a given doctor on a given date
    @Query("SELECT a FROM AppointmentModel a WHERE a.doctor.id = :doctorId AND DATE(a.dateTime) = :date")
    List<AppointmentModel> findByDoctorIdAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    // Find all appointments for a given patient
    List<AppointmentModel> findByPatientId(Long patientId);

    // Find all appointments for a given doctor
    List<AppointmentModel> findByDoctorId(Long doctorId);


}
