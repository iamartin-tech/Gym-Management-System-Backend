package com.gym.service;

import java.time.LocalDate;
import java.util.List;

import com.gym.dto.AppointmentRequest;
import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.Appointment;
import com.gym.model.AppointmentStatus;
import com.gym.model.Member;
import com.gym.model.Trainer;
import com.gym.repository.AppointmentRepository;
import com.gym.repository.MemberRepository;
import com.gym.repository.TrainerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final MemberRepository memberRepository;
	private final TrainerRepository trainerRepository;

	public AppointmentService(
			AppointmentRepository appointmentRepository,
			MemberRepository memberRepository,
			TrainerRepository trainerRepository) {

		this.appointmentRepository = appointmentRepository;
		this.memberRepository = memberRepository;
		this.trainerRepository = trainerRepository;
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment getAppointmentById(Long id) {
		return appointmentRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Appointment not found with ID: "
							+ id));
	}

	public Appointment createAppointment(
			AppointmentRequest request) {

		Member member = memberRepository
				.findById(request.getMemberId())
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Member not found with ID: "
							+ request.getMemberId()));

		Trainer trainer = trainerRepository
				.findById(request.getTrainerId())
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Trainer not found with ID: "
							+ request.getTrainerId()));

		boolean trainerAlreadyBooked =
				appointmentRepository
					.existsByTrainerAndAppointmentDateAndAppointmentTime(
							trainer,
							request.getAppointmentDate(),
							request.getAppointmentTime());

		if (trainerAlreadyBooked) {
			throw new DuplicateResourceException(
					"Trainer already has an appointment "
					+ "at that date and time");
		}

		Appointment appointment = new Appointment();

		appointment.setAppointmentDate(
				request.getAppointmentDate());

		appointment.setAppointmentTime(
				request.getAppointmentTime());

		appointment.setMember(member);
		appointment.setTrainer(trainer);

		if (request.getStatus() != null) {
			appointment.setStatus(request.getStatus());
		} else {
			appointment.setStatus(
					AppointmentStatus.SCHEDULED);
		}

		return appointmentRepository.save(appointment);
	}

	public Appointment updateAppointment(
			Long id,
			AppointmentRequest request) {

		Appointment existing = getAppointmentById(id);

		Member member = memberRepository
				.findById(request.getMemberId())
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Member not found with ID: "
							+ request.getMemberId()));

		Trainer trainer = trainerRepository
				.findById(request.getTrainerId())
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Trainer not found with ID: "
							+ request.getTrainerId()));

		existing.setAppointmentDate(
				request.getAppointmentDate());

		existing.setAppointmentTime(
				request.getAppointmentTime());

		existing.setMember(member);
		existing.setTrainer(trainer);

		if (request.getStatus() != null) {
			existing.setStatus(request.getStatus());
		}

		return appointmentRepository.save(existing);
	}

	public Appointment updateStatus(
			Long id,
			AppointmentStatus status) {

		Appointment appointment = getAppointmentById(id);
		appointment.setStatus(status);

		return appointmentRepository.save(appointment);
	}

	public List<Appointment> getAppointmentsByMember(
			Long memberId) {

		Member member = memberRepository.findById(memberId)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Member not found with ID: "
							+ memberId));

		return appointmentRepository.findByMember(member);
	}

	public List<Appointment> getAppointmentsByTrainer(
			Long trainerId) {

		Trainer trainer =
				trainerRepository.findById(trainerId)
					.orElseThrow(() ->
						new ResourceNotFoundException(
								"Trainer not found with ID: "
								+ trainerId));

		return appointmentRepository.findByTrainer(trainer);
	}

	public List<Appointment> getAppointmentsByDate(
			LocalDate date) {

		return appointmentRepository
				.findByAppointmentDate(date);
	}

	public void deleteAppointment(Long id) {
		Appointment appointment = getAppointmentById(id);
		appointmentRepository.delete(appointment);
	}
}
