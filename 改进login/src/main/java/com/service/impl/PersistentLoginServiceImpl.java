package com.service.impl;

import com.entity.PersistentLogins;
import com.mapper.PersistentLoginsMapper;
import com.service.PersistentLoginsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by Gunn on 2016/12/20.
 */
@Service("persistentLoginsServiceImpl")
public class PersistentLoginServiceImpl implements PersistentLoginsService{

    @Resource(name="persistentLoginsMapper")
    private PersistentLoginsMapper persistentLoginsMapper;

    public int deleteByPrimaryKey(Integer id) {
        return persistentLoginsMapper.deleteByPrimaryKey(id);
    }

    public int insert(PersistentLogins persistentLogins) {
        return persistentLoginsMapper.insert(persistentLogins);
    }

    public int insertSelective(PersistentLogins persistentLogins) {
        return persistentLoginsMapper.insertSelective(persistentLogins);
    }

    public PersistentLogins selectByPrimaryKey(Integer id) {
        return persistentLoginsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(PersistentLogins persistentLogins) {
        return persistentLoginsMapper.updateByPrimaryKey(persistentLogins);
    }

    public int updateByPrimaryKeySelective(PersistentLogins persistentLogins) {
        return persistentLoginsMapper.updateByPrimaryKeySelective(persistentLogins);
    }

    public PersistentLogins selectByUsernameAndSeries(String username, String series) {
        if (!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(series)){
            return persistentLoginsMapper.selectByUsernameAndSeries(username, series);
        }else
            return null;
    }

    public PersistentLogins selectByUsername(String username) {
        return persistentLoginsMapper.selectByUsername(username);
    }
}
