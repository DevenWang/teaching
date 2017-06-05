package com.whut.teaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.whut.teaching.dao")
public class TeachingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TeachingApplication.class, args);
    }

    /**
     * 使用   war 打包时使用
     * <p>
     * Configure the application. Normally all you would need to do it add sources (e.g.
     * config classes) because other settings have sensible defaults. You might choose
     * (for instance) to add default command line arguments, or set an active Spring
     * profile.
     *
     * @param builder a builder for the application context
     * @return the application builder
     * @see SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TeachingApplication.class);
    }

}
