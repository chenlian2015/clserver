package com.yuhuayuan.core.service.impl.version;

import com.yuhuayuan.core.dto.version.VersionDto;
import com.yuhuayuan.core.enums.GeneralStateEnum;
import com.yuhuayuan.core.service.appversion.VersionService;

/**
 * Created by cl on 2017/2/23.
 */
public class VersionServiceImpl implements VersionService {
    @Override
    public boolean addVersion(VersionDto versionDto) {
        return false;
    }

    @Override
    public boolean updateVersionState(long id, GeneralStateEnum stateEnum) {
        return false;
    }

    @Override
    public boolean updateVersion(VersionDto versionDto) {
        return false;
    }

    @Override
    public VersionDto selectVersionByid(long id) {
        return null;
    }

    @Override
    public boolean queryByName(String name, String value, String channel, int plateform, int id) {
        return false;
    }
}
