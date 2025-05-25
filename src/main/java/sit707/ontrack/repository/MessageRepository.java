package sit707.ontrack.repository;

import sit707.ontrack.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query("SELECT m FROM Message m WHERE m.courseId = :courseId " +
            "AND m.assignmentId = :assignmentId AND " +
            "((m.userId = :userId AND m.receiverId = :receiverId) OR " +
            "(m.userId = :receiverId AND m.receiverId = :userId)) " +
            "ORDER BY m.messageTime DESC")
    List<Message> findUserMessage(@Param("userId") String userId,@Param("receiverId") String receiverId,
                                         @Param("courseId")String courseId, @Param("assignmentId")String assignmentId);

    @Query("SELECT m FROM Message m WHERE " +
            "LOWER(m.message) LIKE LOWER(CONCAT('%', :message, '%')) AND " +
            "((m.userId = :userId AND m.receiverId = :receiverId) OR " +
            "(m.userId = :receiverId AND m.receiverId = :userId))")
    List<Message> searchMessage(@Param("userId") String userId, @Param("receiverId") String receiverId, @Param("message") String message);
}
