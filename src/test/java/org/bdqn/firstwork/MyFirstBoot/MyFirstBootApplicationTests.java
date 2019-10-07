package org.bdqn.firstwork.MyFirstBoot;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.bdqn.firstwork.dto.CommentDTO;
import org.bdqn.firstwork.dto.GithubUser;
import org.bdqn.firstwork.dto.NotifyDTO;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.enums.CommentType;
import org.bdqn.firstwork.model.Question;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.service.UserService;
import org.bdqn.firstwork.util.RedisUtils;
import org.bdqn.firstwork.util.TokenIntercept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyFirstBootApplicationTests {
	
	@Resource(name = "redisUtils")
	private RedisUtils redis;
	@Autowired
	private QuestionService questionService;
	
	@Test
	public void contextLoads() {
		//redis.set("username", "杨国钦");
		//redis.expire("username", 3);
		long time = redis.getTime("username");
		System.out.println(time);
		//System.out.println(redis.get("username"));
	}
	
	@Test
	public void testNullCast() {
		GithubUser user=  (GithubUser)null;
		System.out.println(user);
		System.out.println(null instanceof Object);
		System.out.println(user instanceof Object);
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
	}
	@Resource(name = "userService")
	private UserService userService;
	
	@Test
	public void addUser() {
		User u = new User();
		u.setToken("123456789123456789123456789123456789");
		u.setGtmModified(1L);
		u.setGtmCreated(1L);
		u.setName("杨国钦");
		userService.addUser(u);
	}
	
	@Test
	public void test2() {
//		Configuration config = RedisAutoConfig.class.getAnnotation(Configuration.class);
//		config.annotationType();
//		String value = config.value();
//		Test1 t = new org.bdqn.firstwork.utils.Test().new Test1();
//		
//		System.out.println(t.a);
//		String uri = "/admin/user/publish";
//		uri = uri.substring(uri.indexOf("/user/")+6);
//		System.out.println(uri);
		PaginationDTO<QuestionDTO> pageDto = new PaginationDTO<QuestionDTO>();
		Integer curPage = 6;
		Integer totalCount = 12;
		Integer size = 2;
		pageDto.oprData(curPage, size, totalCount);
		System.out.println(pageDto);
	}
	
	@Test
	public void questionPagination() {
		PaginationDTO<QuestionDTO> questionList = questionService.questionList(3, 2,null);
		System.out.println(questionList);
	}
	
	@Test
	public void getQuestionMeg() {
		QuestionDTO dto = questionService.getQuestionMeg(1,false);
		System.out.println(dto);
	}
	@Resource
	ApplicationContext application;
	@Test
	public void getProfileQuestions() {
		PaginationDTO<QuestionDTO> questionList = questionService.questionList(1, 2,6);
		System.out.println(questionList);
	}
	
	@Test
	public void a() {
		TokenIntercept bean = (TokenIntercept)application.getBean("tokenIntercept");
		 //System.out.println(bean.getUserService());
	}
	
	@Test
	public void b() {
		String []s =  application.getBeanDefinitionNames();
		for (String ss : s) {
			System.out.println(ss);
			
		}
	}
	
	@Test
	public void c() {
		List<CommentDTO> commonDTO = questionService.getCommonDTO(1, CommentType.Question);
		commonDTO.forEach(System.out::println);
	}
	@Test
	public void d() {
		QuestionDTO tag = new QuestionDTO();
		tag.setTag("");
		tag.setId(1);
		List<Question> sameQuestion = questionService.getSameQuestion(tag);
		sameQuestion.forEach(System.out::println);
	}
	@Test
	public void e() {
		String str ="git github visual-studio-code vim sublime-text xcode intellij-idea eclipse maven ide svn visual-studio atom emacs textmate hg";
		String [] s=str.split(" ");
		String collect = Arrays.stream(s).collect(Collectors.joining(","));
		System.out.println(collect);
	}
	@Test
	public void f() {
		String str ="git github visual-studio-code vim sublime-text xcode intellij-idea eclipse maven ide svn visual-studio atom emacs textmate hg";
		String [] s=str.split(" ");
		String collect = Arrays.stream(s).collect(Collectors.joining(","));
		System.out.println(collect);
	}
	
	

	@Test
	public void g() {
		PaginationDTO<NotifyDTO> repliesList = questionService.repliesList(1, 2, 6);
		
		System.out.println(repliesList.getData().get(0).getQuestion());
	}
}
