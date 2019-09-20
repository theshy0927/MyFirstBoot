package org.bdqn.firstwork.service;

import java.util.List;

import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Question;

public interface QuestionService {

	public void addQuestion(Question question) ;
	
	public List<QuestionDTO> questionList();
}
