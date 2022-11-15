package com.consumer.feedme.repository;

import com.consumer.feedme.model.Event;
import com.consumer.feedme.model.FeedHeader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, FeedHeader> {
}
