package cristinamastellaro.BE_U2_S3_Test.repositories;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
