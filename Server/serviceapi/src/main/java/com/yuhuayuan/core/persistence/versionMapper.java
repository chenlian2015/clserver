package com.yuhuayuan.core.persistence;

import com.yuhuayuan.core.model.entity.version;

public interface versionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(version record);

    int insertSelective(version record);

    version selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(version record);

    int updateByPrimaryKey(version record);
}