package com.gym.service;

import com.gym.dto.AttendanceRequest;
import com.gym.exception.ResourceNotFoundException;
import com.gym.model.Attendance;
import com.gym.model.Member;
import com.gym.repository.AttendanceRepository;
import com.gym.repository.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	private final MemberRepository memberRepository;

	public AttendanceService(
			AttendanceRepository attendanceRepository,
			MemberRepository memberRepository) {

		this.attendanceRepository = attendanceRepository;
		this.memberRepository = memberRepository;
	}

	public List<Attendance> getAllAttendance() {
		return attendanceRepository.findAll();
	}

	public Attendance getAttendanceById(Long id) {

		return attendanceRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Attendance record not found "
							+ "with ID: " + id));
	}

	public Attendance createAttendance(
			AttendanceRequest request) {

		Member member =
				memberRepository.findById(
						request.getMemberId())
					.orElseThrow(() ->
						new ResourceNotFoundException(
								"Member not found with ID: "
								+ request.getMemberId()));

		Attendance attendance = new Attendance();

		attendance.setAttendanceDate(
				request.getAttendanceDate());

		attendance.setCheckInTime(
				request.getCheckInTime());

		attendance.setCheckOutTime(
				request.getCheckOutTime());

		attendance.setMember(member);

		return attendanceRepository.save(attendance);
	}

	public Attendance updateAttendance(
			Long id,
			AttendanceRequest request) {

		Attendance existing = getAttendanceById(id);

		Member member =
				memberRepository.findById(
						request.getMemberId())
					.orElseThrow(() ->
						new ResourceNotFoundException(
								"Member not found with ID: "
								+ request.getMemberId()));

		existing.setAttendanceDate(
				request.getAttendanceDate());

		existing.setCheckInTime(
				request.getCheckInTime());

		existing.setCheckOutTime(
				request.getCheckOutTime());

		existing.setMember(member);

		return attendanceRepository.save(existing);
	}

	public Attendance checkOut(
			Long id,
			LocalTime checkOutTime) {

		Attendance attendance = getAttendanceById(id);
		attendance.setCheckOutTime(checkOutTime);

		return attendanceRepository.save(attendance);
	}

	public List<Attendance> getMemberAttendance(
			Long memberId) {

		Member member =
				memberRepository.findById(memberId)
					.orElseThrow(() ->
						new ResourceNotFoundException(
								"Member not found with ID: "
								+ memberId));

		return attendanceRepository
				.findByMemberOrderByAttendanceDateDesc(member);
	}

	public void deleteAttendance(Long id) {

		Attendance attendance = getAttendanceById(id);
		attendanceRepository.delete(attendance);
	}
}