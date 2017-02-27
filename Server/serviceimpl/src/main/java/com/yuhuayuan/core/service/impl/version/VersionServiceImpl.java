package com.yuhuayuan.core.service.impl.version;

import com.yuhuayuan.core.dto.version.VersionDto;
import com.yuhuayuan.core.enums.GeneralStateEnum;
import com.yuhuayuan.core.model.entity.version;
import com.yuhuayuan.core.persistence.versionMapper;
import com.yuhuayuan.core.service.appversion.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cl on 2017/2/27.
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    versionMapper versionMapperx;

    public boolean addVersion(version versionDto) {
        versionMapperx.insert(versionDto);
        return false;
    }

    public boolean updateVersionState(long id, GeneralStateEnum stateEnum) {
        return false;
    }

    public boolean updateVersion(version versionDto) {
        return false;
    }

    public VersionDto selectVersionByid(long id) {
        return null;
    }

    public boolean queryByName(String name, String value, String channel, int plateform, int id) {
        return false;
    }
}
