package com.cjhm.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.cjhm.entity.Message;
import com.cjhm.entity.enums.MessageType;

@Controller
public class ChatController {

	private final SimpMessageSendingOperations messagingTemplate;

	public ChatController(SimpMessageSendingOperations messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/chat/message")
	public void message(Message message) {
		if (MessageType.JOIN.equals(message.getType())) {
			message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		}
		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}

}