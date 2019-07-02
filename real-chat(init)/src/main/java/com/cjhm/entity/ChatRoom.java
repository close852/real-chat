package com.cjhm.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.cjhm.entity.enums.MessageType;
import com.cjhm.service.ChatService;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {

	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();

	@Builder
	public ChatRoom(String roomId, String name) {
		this.roomId = roomId;
		this.name = name;
	}
	
	public void handleActions(WebSocketSession session, Message message, ChatService chatService) {
		if(message.getType().equals(MessageType.ENTER)) {
			sessions.add(session);
			message.setMessage(message.getSender()+"님이 입장했습니다.");
		}
		sendMessage(message, chatService);
	}

	private void sendMessage(Message message, ChatService chatService)	 {
		sessions.parallelStream().forEach(s->chatService.sendMessage(s,message));
	}
}
