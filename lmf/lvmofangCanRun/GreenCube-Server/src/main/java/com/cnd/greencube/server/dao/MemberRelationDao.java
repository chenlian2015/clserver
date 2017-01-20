/**
 * 
 */
package com.cnd.greencube.server.dao;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.entity.MeetingMessage;
import com.cnd.greencube.server.entity.MemberRelation;

/**
 * @author cndimini2
 *
 */
@Repository("MemberRelationDao")
public interface MemberRelationDao extends BaseDao<MemberRelation, String>{
	
}
