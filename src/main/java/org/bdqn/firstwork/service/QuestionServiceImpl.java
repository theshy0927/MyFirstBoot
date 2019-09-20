package org.bdqn.firstwork.service;

import java.util.ArrayList;
import java.util.List;

import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.mapper.QuestionMapper;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Override
	public void addQuestion(Question question) {
		questionMapper.addQuestion(question);
	}

	@Override
	public List<QuestionDTO> questionList() {
		 List<Question> questionList = questionMapper.questionList();
		 List<QuestionDTO> questionDTOs =null;
		 if(questionList!=null) {
			 questionDTOs  =new ArrayList<QuestionDTO>();
			 for (Question q : questionList) {
				 QuestionDTO qdto =new QuestionDTO();
				 BeanUtils.copyProperties(q, qdto);
				 User user = questionMapper.getUserByCreateId(qdto.getCreater());
				 qdto.setUser(user);
				 questionDTOs.add(qdto);
			}
		 }
		return questionDTOs;
	}

}
