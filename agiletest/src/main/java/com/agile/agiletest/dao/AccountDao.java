package com.agile.agiletest.dao;

import com.agile.agiletest.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountDao {
    @Update("update admin set active=#{active} where id=#{id}")
    public Boolean update(Admin admin);
}
