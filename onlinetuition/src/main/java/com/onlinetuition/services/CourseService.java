package com.onlinetuition.services;

import com.onlinetuition.exceptions.CourseNotFoundException;
import com.onlinetuition.exceptions.NoDataFoundException;
import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course add( Course course){
        return courseRepository.save(course);
    }

    public List<Course> list(){
        var listOfCourses = (List<Course>)courseRepository.findAll();
        if(listOfCourses.isEmpty()){
            throw new NoDataFoundException();
        }
        return listOfCourses;
    }

    public Course getById(Integer id) {
            return courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException(id));

    }
}
