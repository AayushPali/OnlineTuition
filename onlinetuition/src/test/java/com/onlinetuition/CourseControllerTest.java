package com.onlinetuition;

import com.onlinetuition.controllers.CourseController;
import com.onlinetuition.models.Course;
import com.onlinetuition.services.CourseService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @InjectMocks
    CourseController courseController;

    @Mock
    CourseService courseService ;

    @Test
    void passWhenListReturns(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(1,"Name 1","desc 1","2hr"));
        courses.add(new Course(2,"Name 2","desc 2","2hr"));

        when(courseService.list()).thenReturn(courses);
        assertNotNull(courseController.list());

        System.out.println(courseController.list());
    }

    @Test
    void passWhenAddReturns(){
        Course course = new Course();
        when(courseService.add(any())).thenReturn(new Course());
        assertNotNull(courseController.add(course));

        System.out.println(course.getCourse_id());
        System.out.println(course.getName());
        System.out.println(course);
    }

    @Test
    void passWhenUpdateReturns(){
        Course course = new Course();
        when(courseService.update(any())).thenReturn(new Course());
        assertNotNull(courseController.update(course));
    }
}
