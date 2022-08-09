package com.callor.todo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.callor.todo.model.MemoDTO;
import com.callor.todo.service.MemoService;

@Controller
@RequestMapping(value = "/memo")
public class TodoController {

	private final MemoService memoService;
	public TodoController(MemoService memoService) {
		this.memoService = memoService;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		
		List<MemoDTO> memoList = memoService.selectAllUser(principal.getName());
		model.addAttribute("MEMOS", memoList);
		model.addAttribute("MEMOS", memoService.selectAll());
		return "/memo/home";
	}
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(
			@ModelAttribute("memo") MemoDTO memo,  
			HttpSession httpSession) {
		String username = (String) httpSession.getAttribute("USERNAME");
		memo.setM_author(username);
		return "memo/insert";
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(
			@ModelAttribute("memo")
			MemoDTO memo, 
			MultipartFile file,HttpSession httpSession) {
		memoService.insert(memo);
		
		
		return "redirect:/memo/home";
	}
	@RequestMapping(value="/{seq}/update",method=RequestMethod.GET)
	public String update(@PathVariable("seq") String seq, Model model) {
	
		// 전달받은 seq 에 해당하는 데이터 select
		MemoDTO memo = memoService.findById(Long.valueOf(seq));
		model.addAttribute("MEMO",memo);
		return "memo/insert";
	}
	
	@RequestMapping(value="/{seq}/update",method=RequestMethod.POST) 
	public String update(
			@PathVariable("seq") String seq,
			@ModelAttribute("memo") MemoDTO memoDTO, 
			MultipartFile file,
			HttpSession httpSession) {

		
		String username = (String) httpSession.getAttribute("USERNAME");
		if(username == null) {
			return "redirect:/user/login";
		}
		// 로그인된 사용자의 이름을 memoDTO 에 setting 하기
				memoDTO.setM_author(username);
				
				// 주소에 따라온 seq 변수값을 추출하여 memoDTO 에 setting 하기
				long m_seq = Long.valueOf(seq);
				memoDTO.setM_seq(m_seq);
				
				memoService.insert(memoDTO);
				return String.format("redirect:/memo/%s/detail",seq);
			}
	@ModelAttribute("memo")
	private MemoDTO memoDTO() {
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat toDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat toTime = new SimpleDateFormat("HH:mm:SS");
		
		MemoDTO memo = MemoDTO.builder()
						.m_date(toDay.format(date))
						.m_time(toTime.format(date))
						.build();
		
		return memo;
	}
	}
