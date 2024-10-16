package com.online_academy.academy_service.service;

import com.online_academy.academy_service.dao.CourseRepository;
import com.online_academy.academy_service.dao.UserCourseRepository;
import com.online_academy.academy_service.dto.UserCourseDTO;
import com.online_academy.academy_service.model.Course;
import com.online_academy.academy_service.model.UserCourse;
import com.online_academy.academy_service.model.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class UserCourseServiceImpl implements UserCourseService{
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RestTemplate template;

    @Override
    public UserCourseDTO createUserCourse(UserCourseDTO userCourseDTO) {
        // Check if UserDTO or its ID is null
        if (userCourseDTO.getUserDTO() == null || userCourseDTO.getUserDTO().getId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        // Check if CourseDTO or its ID is null
        if (userCourseDTO.getCourseDTO() == null || userCourseDTO.getCourseDTO().getId() == null) {
            throw new IllegalArgumentException("Course ID must not be null");
        }
        Long userId = userCourseDTO.getUserDTO().getId();

        User user = template.getForObject("http://USER-SERVICE/users/{id}", User.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        //Set UserDTO
        userCourseDTO.getUserDTO().setName(user.getName());
        userCourseDTO.getUserDTO().setRole(user.getRole());
        //Set CourseDTO
        Optional<Course> course = courseRepository.findById(userCourseDTO.getCourseDTO().getId());
        userCourseDTO.getCourseDTO().setTitle(course.get().getTitle());
        // Save the user-course relation
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(user.getId());
        userCourse.getCourse().setId(userCourseDTO.getCourseDTO().getId());
        userCourseRepository.save(userCourse);
        return userCourseDTO;
    }

//    public List<UserCourseDTO> getAllUserCourses(){
//
//    }
}
