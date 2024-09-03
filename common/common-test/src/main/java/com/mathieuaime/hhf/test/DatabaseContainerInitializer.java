package com.mathieuaime.hhf.test;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgisContainerProvider;
import org.testcontainers.lifecycle.Startables;

class DatabaseContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String POSTGIS_TAG = "16-3.4-alpine";
    private static final JdbcDatabaseContainer<?> DB_CONTAINER = new PostgisContainerProvider().newInstance(POSTGIS_TAG);

    static {
        Startables.deepStart(DB_CONTAINER).join();
    }

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        TestPropertyValues.of(
                "spring.datasource.url=" + DB_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + DB_CONTAINER.getUsername(),
                "spring.datasource.password=" + DB_CONTAINER.getPassword()
        ).applyTo(ctx.getEnvironment());
    }
}