package com.cjhm.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cjhm.entity.ChatRoom;
import com.cjhm.entity.Message;
import com.cjhm.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

	private final ObjectMapper objMapper;
	private final ChatService chatService;
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload {}",payload);
//		TextMessage txtMessage = new TextMessage("Welcome to Chatting server!");
//		session.sendMessage(txtMessage);
		 
		Message chatMessage = objMapper.readValue(payload, Message.class);
		log.debug(chatMessage.toString());
		ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
		room.handleActions(session, chatMessage, chatService);
		
	}

	
}
