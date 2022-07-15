package com.demo.data.data.dao;

import com.demo.data.data.daointerfaces.IPublisherCache;
import com.demo.data.data.daointerfaces.IPublisherDao;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PublisherCacheDao implements IPublisherCache {

    private static final Logger logger = LoggerFactory.getLogger(PublisherCacheDao.class);
    private final Gson gson = new Gson();
    private final IPublisherDao publisherDao;

    @Resource(name = "publisher-cache")
    private HashOperations<String, String, String> hashOperations;

    public PublisherCacheDao(IPublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }


    @Override
    public void addPublishersToCache() {
        Map<String, String> publishersMap = new HashMap<>();
        for(var p : publisherDao.getAllPublishers()) {
            publishersMap.put(String.valueOf(p.getId()), gson.toJson(p));
        }
        hashOperations.putAll("publishers", publishersMap);
        logger.info("wrote {} publishers to cache", publishersMap.size());
    }
}
