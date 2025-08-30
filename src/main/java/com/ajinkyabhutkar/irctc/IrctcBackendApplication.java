package com.ajinkyabhutkar.irctc;

import com.ajinkyabhutkar.irctc.entity.Roles;
import com.ajinkyabhutkar.irctc.repo.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrctcBackendApplication implements CommandLineRunner {

	private Logger logger= LoggerFactory.getLogger(IrctcBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IrctcBackendApplication.class, args);
	}

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		logger.info("info");
		logger.debug("debug");
		logger.warn("warning");
		logger.trace("tracing");
		logger.error("error");

		if(!roleRepo.existsByName("ROLE_ADMIN")){

			Roles roles=new Roles();
			roles.setName("ROLE_ADMIN");
			roleRepo.save(roles);

			System.out.println("ROLE_ADMIN generated successfully");
		}

		if(!roleRepo.existsByName("ROLE_NORMAL")){

			Roles roles=new Roles();
			roles.setName("ROLE_NORMAL");
			roleRepo.save(roles);

			System.out.println("ROLE_NORMAL generated successfully");
		}


	}
}
