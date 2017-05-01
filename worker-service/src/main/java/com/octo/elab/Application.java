package com.octo.elab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		// we can't take arguments anyways, so we just obliterate the args array
		// with our own stuff
		args = new String[1];

		// if the env variable doesn't exist then deploy_env will be null
		// if running on comp, minc, prodlike, or prod, then the env var should
		// be there
		String deploy_env = System.getenv("DEPLOY_ENV");
		logger.info("DEPLOY_ENV: " + deploy_env);

		if (deploy_env == null) {
			args[0] = "--spring.profiles.active=local";
			logger.info("Will now deployment on: local");
		} else {
			if (!(deploy_env.equals("comp") || deploy_env.equals("minc") || deploy_env.equals("prodlike")
					|| deploy_env.equals("prod"))) {
				logger.error(deploy_env + " is not a recognized deploy environment, aborting.");
				return;
			}
			args[0] = "--spring.profiles.active=" + deploy_env;
			logger.info("Will now deployment on: " + deploy_env);
		}

		SpringApplication.run(Application.class, args);
		logger.info("Finish Main");
	}
}
