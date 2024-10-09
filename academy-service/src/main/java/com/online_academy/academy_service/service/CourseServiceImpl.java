package com.online_academy.academy_service.service;

import com.online_academy.academy_service.dao.CategoryRepository;
import com.online_academy.academy_service.dao.CourseRepository;
import com.online_academy.academy_service.dao.UserCourseRepository;
import com.online_academy.academy_service.dto.CourseDTO;
import com.online_academy.academy_service.exception.ResourceNotFoundException;
import com.online_academy.academy_service.model.Category;
import com.online_academy.academy_service.model.Course;
import com.online_academy.academy_service.model.UserCourse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate template;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Category category = categoryRepository.findById(courseDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Course course = modelMapper.map(courseDTO, Course.class);
        course.setCategory(category);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        // Map Course entity to CourseDTO
        CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
        courseDTO.setCategoryId(course.getCategory().getId()); // Set the category ID

        return courseDTO;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses =  courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
        return courses;
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Category category = categoryRepository.findById(courseDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setCategory(category);
        course = courseRepository.save(course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
