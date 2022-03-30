package com.onlinetuition.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(Integer id){
        super(String.format("Course with Id %d not found",id));
    }
}
