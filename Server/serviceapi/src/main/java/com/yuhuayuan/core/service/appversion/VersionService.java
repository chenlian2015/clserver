package com.yuhuayuan.core.service.appversion;

import com.yuhuayuan.core.dto.version.VersionDto;
import com.yuhuayuan.core.enums.GeneralStateEnum;
import com.yuhuayuan.core.model.entity.version;

/**
 * Created by cl on 2017/2/23.
 */
public interface VersionService {

    boolean addVersion(version versionDto);

    boolean updateVersionState(long id, GeneralStateEnum stateEnum);

    boolean updateVersion(version versionDto);

    VersionDto selectVersionByid(final long id);

    boolean queryByName(String name, String value, String channel, int plateform, int id);
}
