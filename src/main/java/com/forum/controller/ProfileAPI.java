package com.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.forum.domain.Profile;
import com.forum.service.profile.ProfileService;
import com.forum.util.Jackson;
import com.forum.util.validation.RepositoryStatus;
import com.forum.util.view.Private;

/**
 * @author Giang Nguyen
 *
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileAPI {

	@Autowired
	ProfileService service;

	@GetMapping(value = "/view/{userId}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getProfile(@PathVariable Long userId) {
		RepositoryStatus<Profile> status = service.findByUserId(userId);
		return status.isSuccess() ?
				new ResponseEntity<String>(Jackson.java2Json(status, Private.class), status.getHttpStatus()) :
					new ResponseEntity<String>(Jackson.java2Json(status), status.getHttpStatus());
	}

	@PostMapping(value="/save",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> saveProfile(RequestEntity<Profile> request) {
RepositoryStatus<Profile> status = 		service.save(request.getBody());
return status.isSuccess() ?
		new ResponseEntity<String>(Jackson.java2Json(status, Private.class), status.getHttpStatus()) :
			new ResponseEntity<String>(Jackson.java2Json(status), status.getHttpStatus());
	}
}
