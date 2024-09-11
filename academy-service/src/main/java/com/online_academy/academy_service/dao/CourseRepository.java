package com.online_academy.academy_service.dao;

import com.online_academy.academy_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
