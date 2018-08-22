package uk.dioxic.ccpt.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * workaround for https://jira.spring.io/browse/DATAREST-524
 */
//@Configuration
@Deprecated
public class ValidatorEventRegister implements InitializingBean {
 
    @Autowired
    ValidatingRepositoryEventListener validatingRepositoryEventListener;
 
    @Autowired
    private Map<String, Validator> validators;
 
    @Override
    public void afterPropertiesSet() {
        List<String> events = Collections.singletonList("beforeCreate");
        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            events.stream()
              .filter(p -> entry.getKey().startsWith(p))
              .findFirst()
              .ifPresent(
                p -> validatingRepositoryEventListener
               .addValidator(p, entry.getValue()));
        }
    }
}