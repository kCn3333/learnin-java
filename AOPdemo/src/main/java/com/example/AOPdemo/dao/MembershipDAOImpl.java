package com.example.AOPdemo.dao;

import com.example.AOPdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public String addAccount(Account account) {
            System.out.println(getClass()+" : Adding an Account: " + account);
            return account.getName();


    }
}
