package com.woniuxy.crm67;

import com.woniuxy.dao.UserMapper;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.TreeNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Crm67ApplicationTests {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	@Test
	void contextLoads() {
		List<TreeNode> treeNodeList=userService.selectMenusByUid(1);
		System.out.println(treeNodeList);
	}
	@Test
	public void testUserList(){
		User param=new User();
		param.setRealname("e");
		List<User> users=userMapper.selectUsersByCondition(param);
		for (User u:users) {
			logger.info(u.getTelephone()+"\t"+u.getRealname()+"\t");
		}
	}

}
