package org.bdqn.firstwork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;

public interface QuestionMapper {

	public void addQuestion(Question question);
	
	public List<Question> questionList(@Param("offset")Integer offset,@Param("size") Integer size,@Param("creater") Integer userId);
	
	public User getUserByCreateId(long id);

	public Integer getTotalCount(@Param("creater")Integer userId);
	
	public QuestionDTO getQuestionMeg(Integer id);
}
