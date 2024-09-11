package com.online_academy.academy_service.dao;

import com.online_academy.academy_service.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long>{
    List<UserCourse> findByUserId(Long userId);
    void deleteByUserIdAndCourseId(Long userId, Long courseId);
}
