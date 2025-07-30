package com.example.runexus.infrastructure.persistence.repository;

import com.example.runexus.infrastructure.persistence.entity.MessageEntity;
import com.example.runexus.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findBySenderAndReceiverOrReceiverAndSenderOrderBySentAtAsc(
            UserEntity sender1, UserEntity receiver1, UserEntity sender2, UserEntity receiver2
    );
}

