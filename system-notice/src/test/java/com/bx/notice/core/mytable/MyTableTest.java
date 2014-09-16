/**
 * 
 */
package com.bx.notice.core.mytable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bx.notice.core.entity.MyTable;
import com.bx.notice.core.service.IMyTableService;

/**
 * @author luzhenghong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class MyTableTest {
	@Autowired
	private IMyTableService mytableService;
	
	@Test
	public void textInsertData() throws Exception {
		MyTable mytable = new MyTable();
		mytable.setId(1L);
		mytable.setName("abc");
		mytableService.save(mytable);
	}
}
