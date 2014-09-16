/**
 * 
 */
package com.bx.notice.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bx.notice.core.entity.MyTable;
import com.bx.notice.core.repository.MyTableRepository;
import com.bx.notice.core.service.IMyTableService;

/**
 * @author luzhenghong
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyTableServiceImpl implements IMyTableService {
	@Autowired
	private MyTableRepository myTableRepository;
	/* (non-Javadoc)
	 * @see com.bx.notice.core.service.IMyTableService#save(com.bx.notice.core.entity.MyTable)
	 */
	@Override
	public void save(MyTable mt) throws Exception {
		myTableRepository.save(mt);
	}

}
