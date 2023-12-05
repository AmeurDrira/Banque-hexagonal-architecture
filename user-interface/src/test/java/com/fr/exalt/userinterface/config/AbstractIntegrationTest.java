package com.fr.exalt.userinterface.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.exalt.userinterface.UserInterfaceApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.*;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.*;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(
        classes = UserInterfaceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(type = POSTGRES, refresh = AFTER_EACH_TEST_METHOD)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;
}
