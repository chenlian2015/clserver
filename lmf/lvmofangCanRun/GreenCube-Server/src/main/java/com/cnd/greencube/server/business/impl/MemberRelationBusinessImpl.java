package com.cnd.greencube.server.business.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.business.MemberRelationBusiness;
import com.cnd.greencube.server.dao.MemberRelationDao;
import com.cnd.greencube.server.dao.impl.MemberRelationDaoImpl;
import com.cnd.greencube.server.entity.MemberRelation;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.util.HuanXinHelper;

@SuppressWarnings("rawtypes")
@Service("MemberRelationBusinessImpl")
public class MemberRelationBusinessImpl extends BaseBusinessImpl implements MemberRelationBusiness {

	@Resource(name = "MemberRelationDaoImpl")
	MemberRelationDao memberRelationDao;

	@Override
	public String registerUser(String username, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addFriend(String hostUserId, String guestUserId, String relationType) {

		String sql = "select t.id from MemberRelation t where hostId = ? and guestId = ?";
		int nSize = memberRelationDao.findList(sql, new Object[] { hostUserId, guestUserId }).size();

		if (nSize == 0) {
			MemberRelation mr = new MemberRelation();
			mr.setCreateTime(new Date());
			mr.setGuestId(guestUserId);
			mr.setHostId(hostUserId);
			mr.setLabelName("");
			mr.setRelationType(relationType);
			mr.setDeleteFlag(0);
			memberRelationDao.save(mr);

			// 一个用户关系存储两次，是为了备注名等信息
			MemberRelation mrb = new MemberRelation();
			mrb.setCreateTime(new Date());
			mrb.setGuestId(hostUserId);
			mrb.setHostId(guestUserId);
			mrb.setLabelName("");
			mrb.setRelationType(relationType);
			mrb.setDeleteFlag(0);
			memberRelationDao.save(mrb);

			int y=0;
			if(true)
			{
				int x = 1;
				 y = x/1-1;
			}
			y=2;
			return Message.okMessage(HuanXinHelper.addUser(hostUserId, guestUserId));
		} else {
			return Message.errorMessage("你们已经是好友，请不要重复添加!");
		}

	}
}
