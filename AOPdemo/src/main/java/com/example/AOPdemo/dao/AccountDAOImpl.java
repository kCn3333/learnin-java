package com.example.AOPdemo.dao;


import com.example.AOPdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println(getClass() + "in getName()");
        return name;

    }

    public void setName(String name) {
        this.name = name;
        System.out.println(getClass() + "in setName()");
    }

    public String getServiceCode() {
        System.out.println(getClass() + "in getServiceCode()");
        return serviceCode;

    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        System.out.println(getClass() + "in setServiceCode()");
    }

    @Override
    public String addAccount(Account account) {
        System.out.println(getClass()+" : Adding an Account: " + account);
        return account.getName();
    }



}
