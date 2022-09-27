package com.spring_boot_jpa.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot_jpa.main.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByFirstName(String firstName);
	Optional<Student> findByFirstNameContaining(String str);
	Optional<Student> findByLastNameNotNull();
	Optional<Student> findByGurdianName(String str);
	Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
	
	//JPQL
	@Query("select s from Student s where s.emailId=?1")
	Student getByEmailAddress(String emailId);
	
	//JPQL
	@Query("select s.firstName from Student s where s.emailId=?1")
	String getFirstNameByEmailAddress(String emailId);
	
	//Native
	@Query(
			value = "select * from student s where s.email_address=?1",
			nativeQuery =true
			)
	Student getStudentByEmailAddressNativeQuery(String emailId);
	
	//Native with param
	@Query(
			value = "select * from student s where s.email_address=:emailId",
			nativeQuery =true
			)
	Student getStudentByEmailAddressNativeQueryWithParam(@Param("emailId") String emailId);
	
	
	@Modifying
	@Transactional
	@Query(
			value = "update student s set s.gurdian_mobile=?1 where s.email_address=?2",
			nativeQuery =true
			)
	void updateStudentNameByEmailId(String gurdianMobile, String emailId);
	

}
