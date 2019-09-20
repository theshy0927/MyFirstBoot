package org.bdqn.firstwork.mapper;

import java.util.List;

import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;

public interface QuestionMapper {

	public void addQuestion(Question question);
	
	public List<Question> questionList();
	
	public User getUserByCreateId(long id);
}
