package com.onlinetuition.services;

import com.onlinetuition.exceptions.CourseNotFoundException;
import com.onlinetuition.exceptions.NoDataFoundException;
import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> list() {
        var listOfCourses = (List<Course>) courseRepository.findAll();
        if (listOfCourses.isEmpty()) {
            throw new NoDataFoundException();
        }
        return listOfCourses;
    }

    public Course getById(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    public Course add(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Integer id){
        courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(id));
        courseRepository.deleteById(id);
    }
}

 /*public List<Course> list(){
        var listOfCourses = (List<Course>)courseRepository.findAll();
        if(listOfCourses.isEmpty()){
            throw new NoDataFoundException();
        }
        return listOfCourses;
    }

    public Course getById(Integer id) {
            return courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException(id));

  }*/
