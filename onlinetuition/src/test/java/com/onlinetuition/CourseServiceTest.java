package com.onlinetuition;

import com.onlinetuition.exceptions.CourseNotFoundException;
import com.onlinetuition.exceptions.NoDataFoundException;
import com.onlinetuition.models.Course;
import com.onlinetuition.repositories.CourseRepository;
import com.onlinetuition.services.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Optional;

import static com.onlinetuition.services.CourseService.getNullPropertyNames;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    Course course;

    CourseServiceTest() {

        course = new Course(6, "sa", "as", "5hr");
    }

    @Test
    public void whenNoDataFound_thenThrowException() {
        when(courseRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> courseService.list());
    }

    @Test
    public void whenClassNotFound_thenThrowException() {
        doThrow(new CourseNotFoundException(6)).when(courseRepository).findById(6);
        assertThrows(CourseNotFoundException.class, () -> courseService.update(course));
    }

    @Test
    public void whenCourseFoundById_thenCorrect() {
        when(courseRepository.findById(any())).thenReturn(Optional.ofNullable(course));
        Course newCourse = courseService.getById(2);
        verify(courseRepository).findById(any());
        assertEquals(newCourse.getDuration(), "5hr");
        assertEquals(newCourse.getCourse_id(), 6);
    }

    @Test
    void whenUpdate_thenCorrect() {
        Course updateCourse = new Course(6, null, "as", "1hr");
        when(courseRepository.findById(6)).thenReturn(Optional.of(course));
        when(courseRepository.getById(6)).thenReturn(course);
        BeanUtils.copyProperties(updateCourse, course, getNullPropertyNames(updateCourse));
        when(courseRepository.saveAndFlush(any())).thenReturn(course);
        Course updatedCourse = courseService.update(updateCourse);
        assertEquals(updatedCourse.getName(), "sa");
        assertEquals(updatedCourse.getDuration(), "1hr");
        assertEquals(updateCourse.getDescription(), "as");
    }
}


