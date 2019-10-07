package org.bdqn.firstwork.service;



import java.util.List;

import org.bdqn.firstwork.dto.CommentDTO;
import org.bdqn.firstwork.dto.NotifyDTO;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.enums.CommentType;
import org.bdqn.firstwork.model.Question;

public interface QuestionService {

	public void addQuestion(Question question) ;
	
	public PaginationDTO<QuestionDTO> questionList(Integer curPage,Integer size,Integer userId);
	
	public QuestionDTO getQuestionMeg(Integer id, boolean commited);
	
	public void updateQuestion(Question question);

	public List<CommentDTO> getCommonDTO(Integer questionId,CommentType type);

	public List<Question> getSameQuestion(QuestionDTO tag);

	public List<Question> getHotQuestion();

	public Integer getUnReadCount(Long id);

	public PaginationDTO<NotifyDTO> repliesList(Integer curPage, Integer size, Integer id);

}
