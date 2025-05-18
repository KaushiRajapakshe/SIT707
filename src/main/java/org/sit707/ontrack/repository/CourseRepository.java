package org.sit707.ontrack.repository;

import org.sit707.ontrack.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository  extends JpaRepository<Course, String> {

}

