package org.bdqn.firstwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bdqn.firstwork.dto.CommentDTO;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.mapper.CommentMapper;
import org.bdqn.firstwork.mapper.QuestionMapper;
import org.bdqn.firstwork.mapper.UserMapper;
import org.bdqn.firstwork.model.Comment;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.utils.CommentType;
import org.bdqn.firstwork.utils.ControllerError;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public void addQuestion(Question question) {
		questionMapper.addQuestion(question);
	}

	@Override
	public PaginationDTO<QuestionDTO> questionList(Integer curPage,Integer size,Integer userId) {
		
		PaginationDTO<QuestionDTO> dto = new PaginationDTO<QuestionDTO>();
		Integer totalCount = questionMapper.getTotalCount(userId);
		dto.oprData(curPage, size, totalCount);
		Integer offset = (dto.getCurPage()-1)*size;
		 List<Question> questionList = questionMapper.questionList(offset,size,userId);
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
			 dto.setData(questionDTOs);
		 }
		return dto;
	}

	@Override
	@Transactional
	public QuestionDTO getQuestionMeg(Integer id,boolean commited) {
		if(!commited) {
			questionMapper.addViewCount(id);
		}
		QuestionDTO q = questionMapper.getQuestionMeg(id);
		if(q==null) {
			throw new CustomizeException(ControllerError.not_find);
		}
		q.setDescription(q.getDescription().replaceAll(" ","&nbsp;").replaceAll("\r","<br/>"));
		return q;
	}

	@Transactional
	@Override
	public void updateQuestion(Question question) {
		int result = questionMapper.updateQuestion(question);
		if(result==0) {
			throw new CustomizeException(ControllerError.not_find);
		}
	}

	@Override
	public List<CommentDTO> getCommonDTO(Integer questionId,CommentType type) {
		
		List<CommentDTO> comment = null;
		List<Comment> commentList = commentMapper.getCommentList(questionId,type.getType());
		Set<Long> collect = commentList.stream().map(c-> c.getCreatorId()).distinct().collect(Collectors.toSet());
		//collect 确认返回类型  map方法作为对应集合元素的单个操作  可以分析上面这句是将CreatorId抽出来作为新的set集合
		List<Long> userId = new ArrayList<Long>(collect);
		
		
		
		//获取评论人并转换成map 方便映射 减少时间复杂度
		List<User> userList = userMapper.getUserByCreatorId(userId);
		Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(user->user.getId(), user->user));
		
		//comment 转换成 commentDTO进行数据传递
		comment = commentList.stream().map(c->{
			CommentDTO dto =  new CommentDTO();
			 BeanUtils.copyProperties(c, dto);
			 dto.setCreator(userMap.get(c.getCreatorId()));
			return dto;
		}).collect(Collectors.toList());
		
		return comment;
		
	}
	
	

}
