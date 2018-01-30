package com.example.demo.utils.response;

import java.util.List;

public class ResponseListOfObjects extends ResponseAbstract {

	public ResponseListOfObjects(List<Object> data) {
		super(true, data);
	}
}
