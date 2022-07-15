package com.demo.data.data.services;

import com.demo.data.data.daointerfaces.IPublisherCache;
import com.demo.data.data.daointerfaces.IPublisherDao;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WriteDataToConsole {

    private final Gson gson = new Gson();

    private static final Logger logger = (Logger) LoggerFactory.getLogger(WriteDataToConsole.class);
    private final IPublisherDao publisherDao;
    private final IPublisherCache publisherCache;

    public WriteDataToConsole(IPublisherDao publisherDao, IPublisherCache publisherCache) {
        this.publisherDao = publisherDao;
        this.publisherCache = publisherCache;
    }

    public void Run() {

        var data = publisherDao.getAllPublishers();
        logger.info("All publishers {}", gson.toJson(data));
    }

    @Scheduled(fixedRate = 5000)
    public void updateCache() {
        publisherCache.addPublishersToCache();
    }
}
