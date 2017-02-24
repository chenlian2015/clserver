package com.yuhuayuan.core.dto.version;

import java.util.Date;
import lombok.Data;
/**
 * Created by cl on 2017/2/23.
 */
@Data
public class VersionDto {

    private long id;
    private String value;
    private String name;
    private String channel;
    private int plateform;
    private int fileId;
    private String fileUrl;
    private String fileName;
    private String versionUrl;
    private Date createTime;
    private String minvalue;
    private int state;
    private String content;
}
