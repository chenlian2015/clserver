/**
 * 
 */
package com.cnd.greencube.server.business;

import com.cnd.greencube.server.entity.MemberRelation;

/**
 * @author cndimini2
 *
 */
public interface MemberRelationBusiness {
	
	public String registerUser(String username, String pwd);
	
	public String addFriend(String hostUserId, String guestUserId, String relationType);
}
