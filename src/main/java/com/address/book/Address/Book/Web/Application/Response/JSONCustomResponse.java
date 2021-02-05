package com.address.book.Address.Book.Web.Application.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JSONCustomResponse {
	private Map<String, List<? extends CustomResponseDTO>> data = new HashMap<String, List<? extends CustomResponseDTO>>();
	private CustomResponseStatus status;
	private String message;
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, List<? extends CustomResponseDTO>> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public CustomResponseStatus getStatus() {
		return status;
	}

	public void setData(Map<String, List<? extends CustomResponseDTO>> data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(CustomResponseStatus status) {
		this.status = status;
	}

}
