package org.bdqn.firstwork.MyFirstBoot;


import javax.annotation.Resource;

import org.bdqn.firstwork.dto.GithubUser;
import org.bdqn.firstwork.dto.PaginationDTO;
import org.bdqn.firstwork.dto.QuestionDTO;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.QuestionService;
import org.bdqn.firstwork.service.UserService;
import org.bdqn.firstwork.utils.ListNode;
import org.bdqn.firstwork.utils.RedisUtils;
import org.bdqn.firstwork.utils.TokenIntercept;
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
	public ListNode swapPairs(ListNode head) {
        if(head.next==null){
            return head;
        }
        head = head.next;
        return swapPairs(head);
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
}
