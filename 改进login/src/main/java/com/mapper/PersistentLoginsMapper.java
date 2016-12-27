package com.mapper;

import com.entity.PersistentLogins;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Gunn on 2016/12/20.
 */
public interface PersistentLoginsMapper {

    public int deleteByPrimaryKey(Integer id);

    public int insert(PersistentLogins record);

    public int insertSelective(PersistentLogins record);

    public PersistentLogins selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(PersistentLogins record);

    public int updateByPrimaryKey(PersistentLogins record);

    public PersistentLogins selectByUsernameAndSeries(@Param("username") String username, @Param(("series")) String series);

    public PersistentLogins selectByUsername(@Param("username") String username);




}
