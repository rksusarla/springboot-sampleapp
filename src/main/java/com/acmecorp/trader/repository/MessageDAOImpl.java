package com.acmecorp.trader.repository;

import com.acmecorp.trader.domain.AuditRecord;
import com.acmecorp.trader.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@Repository
public class MessageDAOImpl implements MessageDAO {
    private Logger logger = LoggerFactory.getLogger(DbAuditTrailDAO.class);

    private final JdbcTemplate jdbcTemplate;

    public MessageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void storeMessage(Message msg) {
        logger.info("Storing message to DB");
        jdbcTemplate.update("insert into MESSAGE(MESSAGE_ID, FROM_USER, TO_USER, TIMESTMP, TEXT) values (?,?,?,?,?)",
                             msg.getId(), msg.getFromUser(), msg.getToUser(), msg.getTimestamp(), msg.getText());
    }

    @Override
    @Transactional
    public Message findMessageById(UUID id) {
        logger.info("Loading all messages from DB");
        List<Message> messages = jdbcTemplate.query("select * from MESSAGE where MESSAGE_ID = '"+id+"'", msgMapper);
        if (messages.isEmpty())   throw new MessageNotFoundException();
        return messages.get(0);
    }

    @Override
    @Transactional
    public void deleteMessage(UUID id) {
        logger.info("Deleting record {} from DB", id);
        jdbcTemplate.update("delete from MESSAGE where MESSAGE_ID = "+id);
    }

    @Override
    @Transactional
    public Message updateMessage(Message msg) {
        logger.info("Updating message {} in the DB", msg);
        jdbcTemplate.update("update MESSAGE set MESSAGE_ID = ?, FROM_USER = ?, TO_USER = ?, TIMESTMP = ?, TEXT = ? where MESSAGE_ID = ?",
                msg.getId(), msg.getFromUser(), msg.getToUser(), msg.getTimestamp(), msg.getText(), msg.getId());
        return msg;
    }

    @Override
    @Transactional
    public List<Message> getAllMessages() {
        logger.info("Loading all messages from DB");
        return jdbcTemplate.query("select * from MESSAGE", msgMapper);
    }

    //=====
    private static RowMapper<Message> msgMapper = (ResultSet rs, int rowNum) -> {
        Message msg = new Message();
        msg.setId(rs.getObject("MESSAGE_ID", UUID.class));
        msg.setText(rs.getString("TEXT"));
        msg.setFromUser(rs.getString("FROM_USER"));
        msg.setToUser(rs.getString("TO_USER"));
        msg.setTimestamp(rs.getLong("TIMESTMP"));
        return msg;
    };
}
