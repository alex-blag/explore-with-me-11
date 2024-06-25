package ru.ewm.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.Request;
import ru.ewm.main.model.RequestStatus;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    boolean existsByEventIdAndRequesterId(long eventId, long requesterId);

    long countAllByEventIdAndStatus(long eventId, RequestStatus requestStatus);

}
