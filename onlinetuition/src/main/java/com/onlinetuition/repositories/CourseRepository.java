package com.onlinetuition.repositories;

import com.onlinetuition.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
/*public interface CourseRepository extends CrudRepository<Course,Integer> {
}*/
