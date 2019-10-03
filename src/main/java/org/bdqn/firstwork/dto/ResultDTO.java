package org.bdqn.firstwork.dto;

import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.utils.ControllerError;

import com.alibaba.fastjson.JSON;
/**
 *封装接口的返回值
 * @author Administrator
 *
 */
public class ResultDTO {

	private String message;
	private Integer code;
	private String jsonModel;
	
	
	
	public Object getModel() {
		return jsonModel;
	}


	public String getMessage() {
		return message;
	}


	public Integer getCode() {
		return code;
	}

	

	public static ResultDTO  errorOf(Integer code,String message ) {
		ResultDTO dto = new ResultDTO();
		dto.message = message;
		dto.code = code;
		return dto;
	}


	public static ResultDTO errorOf(CustomizeException ex) {
		return errorOf(ex.getCode(),ex.getMessage());
	}
	
	public static ResultDTO errorOf(ControllerError error) {
		return errorOf(error.getCode(), error.getMessage());
	}
	
	public static ResultDTO okOf() {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.code =200;
		resultDTO.message="请求成功";
		return resultDTO;
	}


	public static ResultDTO okOf(Object model) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.code =200;
		resultDTO.message="请求成功";
		resultDTO.jsonModel=JSON.toJSONString(model);
		return resultDTO;
	}
}
