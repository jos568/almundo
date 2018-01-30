package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.IncomingCall;
import com.example.demo.service.IncomingCallService;
import com.example.demo.utils.response.ResponseAbstract;
import com.example.demo.utils.response.ResponseFactory;


@RestController()
@RequestMapping("/movies")
public class IncomingCallController {

	@Autowired
	IncomingCallService incomingCallService;

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<ResponseAbstract> save(@RequestBody @Validated IncomingCall request) {
		IncomingCall response = incomingCallService.save(request);
		return ResponseFactory.buildResponse(response);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/", method = RequestMethod.PUT, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody
    ResponseEntity<ResponseAbstract> update(@PathVariable("id") Long id, @RequestBody @Validated IncomingCall request) {
		IncomingCall updateMovies = incomingCallService.findById(id);
		IncomingCall response = incomingCallService.update(request,  updateMovies);
        return ResponseFactory.buildResponse(response);		
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<ResponseAbstract> findById(@PathVariable("id") Long id){
		return ResponseFactory.buildResponse(incomingCallService.findById(id));		
	}

	@CrossOrigin
	@RequestMapping(value = "/{name}/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody  ResponseEntity<ResponseAbstract> findByName(@PathVariable("name") String name) {
		return ResponseFactory.buildResponse(incomingCallService.findByName(name));
	}

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody  ResponseEntity<ResponseAbstract> findAll() {
		return ResponseFactory.buildResponse(incomingCallService.findAll());
	}

	public void delete(Long id) {
	}

}
