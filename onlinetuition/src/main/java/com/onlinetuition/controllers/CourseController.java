package com.onlinetuition.controllers;

import com.onlinetuition.exceptions.CourseNotFoundException;
import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import com.onlinetuition.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.persistence.Id;
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
    private CourseService courseService;

    @PostMapping
    public Course add(@RequestBody final Course course){
        return courseService.add(course);
    }

    @RequestMapping("/list")
    @GetMapping
    public List<Course> list(){
        return courseService.list();

    }

    @RequestMapping("{id}")
    @GetMapping()
    public Course getById(@PathVariable Integer id){
            return courseService.getById(id);
    }

}