/**
 * 
 */
package com.bx.notice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bx.notice.core.entity.NoticeBodyInfo;

/**
 * @author luzhenghong
 *
 */
public interface NoticeBodyInfoRepository extends
		PagingAndSortingRepository<NoticeBodyInfo, Long> {

}
