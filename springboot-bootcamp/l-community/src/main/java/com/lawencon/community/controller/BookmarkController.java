package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.bookmark.DeleteByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.community.service.BookmarkService;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
	private BookmarkService bookmarkService;

	@Autowired
	public void setBookmarkService(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}

	@GetMapping
	public ResponseEntity<GetAllBookmarkDtoRes> getAll() throws Exception {
		GetAllBookmarkDtoRes result = bookmarkService.findAll();
		return new ResponseEntity<GetAllBookmarkDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByBookmarkIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByBookmarkIdDtoRes data = bookmarkService.findById(id);
		return new ResponseEntity<GetByBookmarkIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetBookmarkByUserDtoRes> getByUser(@PathVariable("id") String id) throws Exception {
		GetBookmarkByUserDtoRes data = bookmarkService.findByUser(id);
		return new ResponseEntity<GetBookmarkByUserDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByBookmarkIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByBookmarkIdDtoRes data = bookmarkService.deleteById(id);
		return new ResponseEntity<DeleteByBookmarkIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertBookmarkDtoRes> insertData(@RequestBody @Valid InsertBookmarkDtoReq data) throws Exception {
		InsertBookmarkDtoRes save = bookmarkService.insert(data);
		return new ResponseEntity<InsertBookmarkDtoRes>(save, HttpStatus.CREATED);
	}
}
