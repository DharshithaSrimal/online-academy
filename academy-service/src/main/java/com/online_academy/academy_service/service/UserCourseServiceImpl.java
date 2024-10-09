package com.online_academy.academy_service.service;

import com.online_academy.academy_service.dao.UserCourseRepository;
import com.online_academy.academy_service.dto.UserCourseDTO;
import com.online_academy.academy_service.model.UserCourse;
import com.online_academy.academy_service.model.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserCourseServiceImpl implements UserCourseService{
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private RestTemplate template;

//    @Autowired
//    private UserCourse userCourse;
    @Override
    public UserCourseDTO createUserCourse(UserCourseDTO userCourseDTO) {
        Long userId = userCourseDTO.getUserId();
        User user = template.getForObject("http://localhost:8080/users/{id}", User.class, userId);
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(user.getId());
        userCourse.setUserId(userCourseDTO.getCourseId());
        // Save the user-course relation
        userCourseRepository.save(userCourse);
        return userCourseDTO;
    }
}
