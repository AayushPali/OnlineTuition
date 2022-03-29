package com.onlinetuition.repositories;

import com.onlinetuition.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {
}
