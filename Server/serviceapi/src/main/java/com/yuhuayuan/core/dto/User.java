package com.yuhuayuan.core.dto;

public class User {
	
    private Integer id;
    
    private String openid;

    private String nickName;
    
    private String headImageUrl;

    private String sharePicWithZCode;
    
    private String shareFromOpenId;
    
 
    public User()
    {
		openid="";
		nickName="";
		headImageUrl="";
		sharePicWithZCode="";
		shareFromOpenId="";
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public String getSharePicWithZCode() {
		return sharePicWithZCode;
	}

	public void setSharePicWithZCode(String sharePicWithZCode) {
		this.sharePicWithZCode = sharePicWithZCode;
	}

	public String getShareFromOpenId() {
		return shareFromOpenId;
	}

	public void setShareFromOpenId(String shareFromOpenId) {
		this.shareFromOpenId = shareFromOpenId;
	}


}