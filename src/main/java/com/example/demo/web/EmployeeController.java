package com.example.demo.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.file.SaveFilesHandler;
import com.example.demo.utils.response.ResponseAbstract;
import com.example.demo.utils.response.ResponseFactory;


@RestController()
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<ResponseAbstract> save(@RequestBody @Validated Employee request) {
		Employee response = employeeService.save(request);
		return ResponseFactory.buildResponse(response);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/", method = RequestMethod.PUT, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody
    ResponseEntity<ResponseAbstract> update(@PathVariable("id") Long id, @RequestBody @Validated Employee request) {
		Employee oldEmployee = employeeService.findById(id);
		Employee response = employeeService.update(request,  oldEmployee);
        return ResponseFactory.buildResponse(response);		
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody ResponseEntity<ResponseAbstract> findById(@PathVariable("id") Long id){
		return ResponseFactory.buildResponse(employeeService.findById(id));		
	}

	@CrossOrigin
	@RequestMapping(value = "/{name}/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody  ResponseEntity<ResponseAbstract> findByName(@PathVariable("name") String name) {
		return ResponseFactory.buildResponse(employeeService.findByName(name));
	}

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody  ResponseEntity<ResponseAbstract> findAll() {
		return ResponseFactory.buildResponse(employeeService.findAll());
	}
	
	
	
    @PostMapping("/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {

        //logger.debug("Multiple file upload!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

        	SaveFilesHandler.saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }

	public void delete(Long id) {
	}

}
