package com.online_academy.academy_service.service;

import com.online_academy.academy_service.dao.CourseRepository;
import com.online_academy.academy_service.dao.UserCourseRepository;
import com.online_academy.academy_service.dto.CourseDTO;
import com.online_academy.academy_service.exception.ResourceNotFoundException;
import com.online_academy.academy_service.model.Course;
import com.online_academy.academy_service.model.UserCourse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    private final RestTemplate restTemplate;
    private final CourseRepository courseRepository;
    private final UserCourse userCourse;
    private final UserCourseRepository userCourseRepository;

    public CourseServiceImpl(UserCourse userCourse, RestTemplate restTemplate, CourseRepository courseRepository, UserCourseRepository userCourseRepository){
        this.userCourse = userCourse;
        this.restTemplate = restTemplate;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;

    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        // convert DTO to entity, save, then return DTO
        // Assuming category has already been handled in service layer
        // Implementation omitted for brevity
        return null;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return new CourseDTO(course.getId(), course.getTitle(), course.getDescription(),
                course.getCategory().getName(), course.getPrice());
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(course ->
                        new CourseDTO(course.getId(), course.getTitle(), course.getDescription(),
                                course.getCategory().getName(), course.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        // similar to getCourseById, update and save
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void registerUserToCourse(Long userId, Long courseId) {
        // URL of the User Microservice
        String userServiceUrl = "http://localhost:8080/api/users/" + userId + "/courses/" + courseId;

        // Make the request to register the user
        restTemplate.postForObject(userServiceUrl, null, Void.class);
        userCourse.setUserId(userId);
        userCourse.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found")));
        userCourseRepository.save(userCourse);
    }

    @Override
    public void unregisterUserFromCourse(Long userId, Long courseId) {
        userCourseRepository.deleteByUserIdAndCourseId(userId, courseId);
    }
}
