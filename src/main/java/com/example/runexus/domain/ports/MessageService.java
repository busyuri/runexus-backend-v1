package com.example.runexus.domain.ports;

import com.example.runexus.application.dto.MessageInput;
import com.example.runexus.domain.models.Message;
import java.util.List;

public interface MessageService {

    Message createMessage(Message message);

    List<Message> getMessagesBetweenUsers(Long userId1, Long userId2);

    Message sendMessage(MessageInput input);
}
