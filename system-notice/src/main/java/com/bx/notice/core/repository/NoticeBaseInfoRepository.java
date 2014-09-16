/**
 * 
 */
package com.bx.notice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bx.notice.core.entity.NoticeBaseInfo;

/**
 * @author luzhenghong
 *
 */
public interface NoticeBaseInfoRepository extends
		PagingAndSortingRepository<NoticeBaseInfo, Long> {

}
