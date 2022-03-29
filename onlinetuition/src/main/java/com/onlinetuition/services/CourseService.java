package com.onlinetuition.services;

import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course add( Course course){
        return courseRepository.saveAndFlush(course);
    }

    public List<Course> list(){
        return courseRepository.findAll();

    }

    public Course getById(Integer id){
        return courseRepository.getById(id);
    }
}
