package ru.itis.javalab.server.extensions;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Component
public class MvcUriComponentBuilderDelegator {

    public MvcUriComponentsBuilder.MethodArgumentBuilder fromMappingName(String mappingName){
        return MvcUriComponentsBuilder.fromMappingName(mappingName);
    }

}
