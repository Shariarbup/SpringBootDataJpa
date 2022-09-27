package com.spring_boot_jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_boot_jpa.main.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
