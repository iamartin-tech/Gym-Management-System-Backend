package com.gym.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Appointment;
import com.gym.model.Member;
import com.gym.model.Trainer;

@Repository
public interface AppointmentRepository
		extends JpaRepository<Appointment, Long> {

	List<Appointment> findByMember(Member member);

	List<Appointment> findByTrainer(Trainer trainer);

	List<Appointment> findByAppointmentDate(
			LocalDate appointmentDate);

	List<Appointment> findByTrainerAndAppointmentDate(
			Trainer trainer,
			LocalDate appointmentDate);

	boolean existsByTrainerAndAppointmentDateAndAppointmentTime(
			Trainer trainer,
			LocalDate appointmentDate,
			LocalTime appointmentTime);
}