package com.service;

import com.entity.PersistentLogins;

/**
 * Created by Gunn on 2016/12/20.
 */
public interface PersistentLoginsService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(PersistentLogins persistentLogins);

    public int insertSelective(PersistentLogins persistentLogins);

    public PersistentLogins selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(PersistentLogins persistentLogins);

    public int updateByPrimaryKey(PersistentLogins persistentLogins);

    public PersistentLogins selectByUsernameAndSeries(String username, String series);

    public PersistentLogins selectByUsername(String username);
}
