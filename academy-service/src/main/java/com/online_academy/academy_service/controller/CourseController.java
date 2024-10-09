package com.online_academy.academy_service.controller;

import com.online_academy.academy_service.dto.CourseDTO;
import com.online_academy.academy_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") 
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    // Sample endpoint to get a list of courses
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses()).getBody();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{courseId}/register/{userId}")
//    public ResponseEntity<Void> registerUserToCourse(@PathVariable Long courseId, @PathVariable Long userId) {
//        courseService.registerUserToCourse(userId, courseId);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{courseId}/unregister/{userId}")
//    public ResponseEntity<Void> unregisterUserFromCourse(@PathVariable Long courseId, @PathVariable Long userId) {
//        courseService.unregisterUserFromCourse(userId, courseId);
//        return ResponseEntity.noContent().build();
//    }
}
