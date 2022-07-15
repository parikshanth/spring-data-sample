package com.demo.data.data.dao;

import com.demo.data.data.constants.Queries;
import com.demo.data.data.daointerfaces.IPublisherDao;
import com.demo.data.data.models.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class PublisherDao implements IPublisherDao {

    private final JdbcTemplate template = new JdbcTemplate();

    public PublisherDao(@Qualifier("pubs-db") DataSource pubsDbDataSource ) {
        template.setDataSource(pubsDbDataSource);
    }

    private final RowMapper<Publisher> publisherRowMapper = (ResultSet rs, int rowNum) ->
            new Publisher(
                    rs.getInt("pub_id"),
                    rs.getString("pub_name"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("country")
            );

    @Override
    public List<Publisher> getAllPublishers() {
        return template.query(Queries.GET_ALL_PUBLISHERS, publisherRowMapper);
    }
}
