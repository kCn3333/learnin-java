package com.example.AOPdemo;

import com.example.AOPdemo.dao.AccountDAO;
import com.example.AOPdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AoPdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AoPdemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
		return runner -> {
			Account account=new Account(1,"Bob");
			System.out.println("hello world");
			demoBeforeAdvice(accountDAO,membershipDAO,account);
			System.out.println("some operations");
			System.out.println("actual Account" + account);

			Thread.sleep(400);
			System.out.println("another operations");
			demoBeforeAdvice(accountDAO,membershipDAO,account);


			takeSomeTime();

		};
	}

	private String takeSomeTime() throws InterruptedException {
		for(int i=0; i<100; i++ ){
			Thread.sleep(100);
		}
		return "done";

	}

	private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO,Account account) {


		accountDAO.addAccount(account);
		accountDAO.getName();
		//membershipDAO.addAccount(account);
	}

}
