package com.lawencon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.service.AttachmentService;

@RestController
@RequestMapping("attachments")
public class AttachmentController {

	private AttachmentService attachmentService;

	@Autowired
	public void setAttachService(AttachmentService attachService) {
		this.attachmentService = attachService;
	}

	@GetMapping("{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) throws Exception {
		GetByAttachmentIdDtoRes findById = attachmentService.findById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + findById.getData().getAttachmentExtension());

		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(findById.getData().getAttachmentContent());
	}
}
