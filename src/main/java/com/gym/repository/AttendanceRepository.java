package com.gym.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Attendance;
import com.gym.model.Member;

@Repository
public interface AttendanceRepository 
	extends JpaRepository<Attendance, Long> {
	
	List<Attendance> findByMember(Member member);
	List<Attendance> findByAttendanceDate(LocalDate attendanceDate);
	List<Attendance> findByMemberOrderByAttendanceDateDesc(Member member);

}
