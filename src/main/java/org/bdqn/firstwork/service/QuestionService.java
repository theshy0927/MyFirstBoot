package org.bdqn.firstwork.service;



import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.Question;

public interface QuestionService {

	public void addQuestion(Question question) ;
	
	public PaginationDTO<QuestionDTO> questionList(Integer curPage,Integer size,Integer userId);
	
	public QuestionDTO getQuestionMeg(Integer id);

}
