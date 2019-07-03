package com.cjhm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhm.entity.ChatRoom;
import com.cjhm.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

	private final ChatRoomRepository chatRoomRepository;

	@GetMapping("/room")
	public String room(Model model) {
		List<ChatRoom> rooms = chatRoomRepository.findAllRoom();
		model.addAttribute("rooms", rooms);
		model.addAttribute("a", "a");
		return "/chat/room";
	}

	@PostMapping("/room")
	@ResponseBody
	public ChatRoom ChatRoomcreatRoom(@RequestParam String name) {
		System.out.println("name :: " + name);
		return chatRoomRepository.createChatRoom(name);
	}

	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> rooms() {
		return chatRoomRepository.findAllRoom();
	}

	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {

		ChatRoom room = chatRoomRepository.findRoomById(roomId);
		model.addAttribute("roomId", roomId);
		model.addAttribute("room", room);
		log.info("roomId : {}", roomId);
		return "/chat/roomdetail";
	}

	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String roomId) {
		return chatRoomRepository.findRoomById(roomId);
	}
}
