package org.bdqn.firstwork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Notify;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;

public interface QuestionMapper {

	public void addQuestion(Question question);
	
	public List<Question> questionList(@Param("offset")Integer offset,@Param("size") Integer size,@Param("creater") Integer userId);
	
	public User getUserByCreateId(long id);

	public Integer getTotalCount(@Param("creater")Integer userId);
	
	public QuestionDTO getQuestionMeg(Integer id);
	
	public int addViewCount(@Param("id")Integer id);
	
	public int updateQuestion(Question question);

	public List<Question> getSameQeustion(Question q);

	public List<Question> getHotQuestion();

	public Integer getUnReadCount(Long id);

	public Integer getTotalNotifyCount(@Param("receiver")Integer id);

	public List<Notify> repliseList(@Param("offset")Integer offset,@Param("size") Integer size, @Param("receiver")Integer id);

	public List<Question> getQuestionById(List<Integer> commentId);
	public Question getQuestionById2(Integer id);
}
