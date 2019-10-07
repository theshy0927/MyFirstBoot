package org.bdqn.firstwork.dto;

import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;

import lombok.Data;

@Data
public class NotifyDTO {

	private long id;
	private User notifier;
	private Question question;
	private String isQuestionNotify;
	private long gmtCreate;
	private boolean  read;
	
}
