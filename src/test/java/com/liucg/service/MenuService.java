package com.liucg.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liucg.pojo.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuService {
	@Autowired
	Menuservice menuService;
	@Test
	public void List() {
		List<Menu> list=menuService.getTreeMenu("20191214007");
		System.out.println("***************"+list);
	}
}
