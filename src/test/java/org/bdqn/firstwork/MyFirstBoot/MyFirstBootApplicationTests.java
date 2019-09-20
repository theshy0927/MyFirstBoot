package org.bdqn.firstwork.MyFirstBoot;

import javax.annotation.Resource;

import org.bdqn.firstwork.config.RedisAutoConfig;
import org.bdqn.firstwork.dto.GithubUser;
import org.bdqn.firstwork.model.User;
import org.bdqn.firstwork.service.UserService;
import org.bdqn.firstwork.utils.ListNode;
import org.bdqn.firstwork.utils.RedisUtils;
import org.bdqn.firstwork.utils.Test.Test1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyFirstBootApplicationTests {
	
	@Resource(name = "redisUtils")
	private RedisUtils redis;
	
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
		Test1 t = new org.bdqn.firstwork.utils.Test().new Test1();
		
		System.out.println(t.a);
	}
}
