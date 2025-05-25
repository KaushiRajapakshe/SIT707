package sit707.ontrack.repository;

import sit707.ontrack.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository  extends JpaRepository<Course, String> {
    @Query("SELECT m FROM Course m WHERE m.courseId = :courseId " +
            "ORDER BY m.assignmentOrderNumber DESC"
    )
    public List<Course> findAssignment(@Param("courseId")String courseId);
}

