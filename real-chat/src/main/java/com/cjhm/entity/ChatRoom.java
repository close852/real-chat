package com.cjhm.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom implements Comparable<ChatRoom>{

	private String roomId;
	private String name;
	private LocalDateTime createDate;

	@Builder
	public ChatRoom(String roomId, String name,LocalDateTime createDate) {
		this.roomId = roomId;
		this.name = name;
		this.createDate = createDate;
	}
	public static ChatRoom create(String name) {
		return ChatRoom.builder()
					.name(name)
					.roomId(UUID.randomUUID().toString())
					.createDate(LocalDateTime.now())
					.build();
	}
	
	@Override
	public int compareTo(ChatRoom o) {
		return createDate.compareTo(o.getCreateDate());
	}
}
