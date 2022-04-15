package com.onlinetuition.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.onlinetuition.exceptions.CourseNotFoundException;
import com.onlinetuition.exceptions.NoDataFoundException;
import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /*public CourseService(CourseRepository cr){
        this.courseRepository = cr;
    }

    public CourseRepository returnRepository(){
        return this.courseRepository;
    }*/

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

    public Course update(Course course) {
        var id = course.getCourse_id();
        courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        Course existingCourse = courseRepository.getById(id);
        BeanUtils.copyProperties(course, existingCourse, getNullPropertyNames(course));
        //TA(Trivial Approach)- overwrite those attributes of existingCourse for which course doesn't have a null value; !!lots of If-Else statements
        return courseRepository.saveAndFlush(existingCourse);
    }

    public void deleteById(Integer id) {
        courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.deleteById(id);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
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
