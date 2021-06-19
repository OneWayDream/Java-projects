package ru.itis.javalab.server.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinionsContentManagerInitializer implements InitializingBean {

    @Autowired
    protected MinionsContentManager minionsContentManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        minionsContentManager.init();
        minionsContentManager.updateMinionsInfo();
        minionsContentManager.startUpdateTop3Thread();
    }
}
