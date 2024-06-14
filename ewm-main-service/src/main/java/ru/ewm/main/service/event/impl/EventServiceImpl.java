package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.State;
import ru.ewm.main.repository.EventRepository;
import ru.ewm.main.service.event.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getByIdOrThrow(long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getEventNotFoundException(id));
    }

    @Override
    public Event getByIdAndInitiatorIdOrThrow(long id, long initiatorId) {
        return eventRepository
                .findByIdAndInitiatorId(id, initiatorId)
                .orElseThrow(() -> ExceptionUtil.getEventNotFoundException(id, initiatorId));
    }

    @Override
    public Page<Event> findAllByParams(
            List<Long> initiatorIds,
            List<State> states,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Pageable pageable
    ) {
        return eventRepository.findAllByParams(
                categoryIds,
                initiatorIds,
                rangeBegin,
                rangeEnd,
                states,
                pageable
        );
    }

}
