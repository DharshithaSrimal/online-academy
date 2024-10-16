package com.online_academy.academy_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseDTO {
    private Long id;
    private UserDTO userDTO;
    private CourseDTO courseDTO;
}
