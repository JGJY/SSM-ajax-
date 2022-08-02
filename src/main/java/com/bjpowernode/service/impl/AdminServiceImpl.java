package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    //在业务逻辑层中一定有数据访问层对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户到DB中查询相应的对象
        //如果有条件，则一定要创建AdminExample的对象，用来封装条件
        AdminExample example = new AdminExample();
        /**
         *如何添加条件
         * select * from admin where a_name = 'admin'
         */
        //添加用户名条件
        example.createCriteria().andANameEqualTo(name);

        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()>0){
            Admin admin = list.get(0);
            //如果找到则进行密码比对
            /**
             * 分析:
             * 密文加密的密码比较
             */
             String miPwd = MD5.encrypByMd5(pwd);
             if(miPwd.equals(admin.getaPass())){
                 return admin;
             }
        }
        //
        return null;
    }
}
