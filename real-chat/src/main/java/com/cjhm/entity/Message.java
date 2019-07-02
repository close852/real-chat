package com.cjhm.entity;

import com.cjhm.entity.enums.MessageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
	@Override
	public String toString() {
		return "Message [type=" + type + ", roomId=" + roomId + ", sender=" + sender + ", message=" + message + "]";
	}
}
