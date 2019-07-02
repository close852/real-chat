package com.cjhm.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.cjhm.entity.ChatRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ChatRoomRepository {

	private Map<String, ChatRoom> chatRoomMap;

	@PostConstruct
	private void init() {
		chatRoomMap = new LinkedHashMap<String, ChatRoom>();
	}

	public List<ChatRoom> findAllRoom() {
		List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
		Collections.reverse(chatRooms);
		return chatRooms;
	}

	public ChatRoom findRoomById(String id) {
		return chatRoomMap.get(id);
	}

	public ChatRoom createChatRoom(String name) {
		ChatRoom room = ChatRoom.create(name);
		chatRoomMap.put(room.getRoomId(), room);
		log.debug("create room {}",room);
		return room;
	}
}
