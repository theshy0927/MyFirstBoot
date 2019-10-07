package org.bdqn.firstwork.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bdqn.firstwork.dto.CommentDTO;
import org.bdqn.firstwork.dto.NotifyDTO;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.enums.CommentType;
import org.bdqn.firstwork.enums.ControllerError;
import org.bdqn.firstwork.enums.NotifyType;
import org.bdqn.firstwork.exception.CustomizeException;
import org.bdqn.firstwork.mapper.CommentMapper;
import org.bdqn.firstwork.mapper.QuestionMapper;
import org.bdqn.firstwork.mapper.UserMapper;
import org.bdqn.firstwork.model.Comment;
import org.bdqn.firstwork.model.Notify;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
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

	@Override
	public List<Question> getSameQuestion(QuestionDTO question) {
		//把 ， 替换成 | 进行sql正则表达式匹配
		Question q = new Question();
		q.setId(question.getId());
		if(question.getTag()!=null&&!question.getTag().equals("")) {
			String[] tags = question.getTag().split(",");
			String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
			q.setTag(regexTag);
		}
		List<Question> questions = questionMapper.getSameQeustion(q);
		return questions;
	}

	@Override
	public List<Question> getHotQuestion() {
		List<Question> arrayList = new ArrayList<Question>();
		arrayList = questionMapper.getHotQuestion();
		return arrayList;
	}

	@Override
	public Integer getUnReadCount(Long id) {
		return questionMapper.getUnReadCount(id);
	}

	@Override
	public PaginationDTO<NotifyDTO> repliesList(Integer curPage, Integer size, Integer id) {
		PaginationDTO<NotifyDTO> dto = new PaginationDTO<NotifyDTO>();
		Integer totalCount = questionMapper.getTotalNotifyCount(id);
		dto.oprData(curPage, size, totalCount);
		Integer offset = (dto.getCurPage()-1)*size;
		 List<Notify> notifyList = questionMapper.repliseList(offset,size,id);
		 List<NotifyDTO> notifyDTOs =null;
		 
		 Set<Long> collect = notifyList.stream().map(n->n.getNotifier()).collect(Collectors.toSet());
		 List<Long> userId = new ArrayList<Long>(collect);
		
		 Set<Integer> commentCollect = notifyList.stream().filter(n->n.getIsQuestionNotify()==CommentType.Comment.getType()).map(n->n.getOuterId()).collect(Collectors.toSet());
		 List<Integer> commentId = new ArrayList<Integer>(commentCollect);
		 Set<Integer> questionCollect = notifyList.stream().filter(n->n.getIsQuestionNotify()!=CommentType.Comment.getType()).map(n->n.getOuterId()).collect(Collectors.toSet());
		 List<Integer> questionId = new ArrayList<Integer>(questionCollect);
		
		 
		 System.out.println("commentId"+commentId);
		 System.out.println("questionId"+questionId);
		
		 Map<Integer, Question> commonMap = commentId.stream().collect(Collectors.toMap(c->c,c->{
			 return questionMapper.getQuestionById2(id);
		 }));
		 Map<Long, User> userMap = userMapper.getUserByCreatorId(userId).stream().collect(Collectors.toMap(u->u.getId(),u->u ));
		Map<Integer, Question> questionMap = questionMapper.getQuestionById(questionId).stream().collect(Collectors.toMap(q->q.getId(), q->q));
		//QuestionMap.putAll(CommonMap);
		 
		if(notifyList!=null) {
			 notifyDTOs  =new ArrayList<NotifyDTO>();
			 for (Notify n : notifyList) {
				 NotifyDTO qdto =new NotifyDTO();
				 BeanUtils.copyProperties(n, qdto);
				 qdto.setNotifier(userMap.get(n.getNotifier()));
				 if(n.getIsQuestionNotify()==CommentType.Question.getType()) {
					 qdto.setQuestion(questionMap.get(n.getOuterId()));
					 qdto.setIsQuestionNotify(NotifyType.question_notify.getDesc());
				 }else {
					 qdto.setQuestion(commonMap.get(n.getOuterId()));
					 qdto.setIsQuestionNotify(NotifyType.common_notify.getDesc());
				 }
				 
				 notifyDTOs.add(qdto);
			}
			 dto.setData(notifyDTOs);
		 }
		return dto;
		
		
	}
	
	

}
