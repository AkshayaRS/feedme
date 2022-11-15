package com.consumer.feedme.repository;

import com.consumer.feedme.model.FeedHeader;
import com.consumer.feedme.model.Outcome;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutcomeRepository extends MongoRepository<Outcome, FeedHeader> {

}
