package com.consumer.feedme.repository;

import com.consumer.feedme.model.FeedHeader;
import com.consumer.feedme.model.Market;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkerRepository extends MongoRepository<Market, FeedHeader> {
}
