package com.onlinetuition.controllers;

import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
/*
Service to add a course. (POST /courses)
Service to retrieve the list of available courses. (GET /courses/list)
Service to retrieve details of a course using course id (GET /courses/{course_id})
*/

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Course add(@RequestBody final Course course){
        return courseRepository.saveAndFlush(course);
    }

    @RequestMapping("/list")
    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();

    }

    @RequestMapping("{id}")
    @GetMapping()
    public Course getById(@PathVariable Integer id){
        return courseRepository.getById(id);
    }

}