package com.example.AOPdemo.dao;

import com.example.AOPdemo.Account;

public interface AccountDAO {

    String addAccount(Account account);

    String getName();

    void setName(String name);
    String getServiceCode();

    void setServiceCode(String serviceCode);
}
