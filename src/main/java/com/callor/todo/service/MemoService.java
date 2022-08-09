package com.callor.todo.service;

import java.util.List;

import com.callor.todo.model.MemoDTO;

public interface MemoService {


	public List<MemoDTO> selectAll();
	public List<MemoDTO> selectAllUser(String username);
	public MemoDTO findById(Long seq);
	public List<MemoDTO> findByAuthor(String username);
	
	public int insert(MemoDTO memo);
	public int update(MemoDTO memo);
	public int delete(Long seq);
	
}
