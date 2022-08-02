package com.bjpowernode.service;

import com.bjpowernode.pojo.Admin;

public interface AdminService {
    //完成登录功能
    Admin login(String name,String pwd);
}
