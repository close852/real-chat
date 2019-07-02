package com.cjhm.service;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.cjhm.entity.ChatRoom;
import com.cjhm.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@Service
public class ChatService {

	private final ObjectMapper objMapper;

	private Map<String, ChatRoom> chatRooms;

	public ChatService(ObjectMapper objMapper, Map<String, ChatRoom> chatRooms) {
		this.objMapper = objMapper;
		this.chatRooms = chatRooms;
	}

	@PostConstruct
	public void init() {
		chatRooms = new LinkedHashMap<String, ChatRoom>();
	}

	public void sendMessage(WebSocketSession s, Message message) {
		try {
			s.sendMessage(new TextMessage(objMapper.writeValueAsString(message)));
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

	public ChatRoom createRoom(String name) {
		String roomId = UUID.randomUUID().toString().replace("-", "");
		ChatRoom chatRoom = ChatRoom.builder().roomId(roomId).name(name).build();

		chatRooms.put(roomId, chatRoom);
		return chatRoom;

	}

	public List<ChatRoom> findAllRoom() {
		return new ArrayList<ChatRoom>(chatRooms.values());
	}

	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}

}
