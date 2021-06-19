package ru.itis.javalab.server.extensions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Arrays;
import java.util.Locale;

@Component
@Order(value = 1)
public class StatusViewResolver extends FreeMarkerViewResolver {

    public StatusViewResolver() {
        super();
        this.setViewClass(StatusView.class);
    }

    public StatusViewResolver(String prefix, String suffix) {
        super(prefix, suffix);
        this.setViewClass(StatusView.class);
    }

    protected final static String STATUS_URL_PREFIX = "status:";

    protected final static String REDIRECT_URL_PREFIX = "redirect:";

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith(STATUS_URL_PREFIX)) {
            Integer status = Integer.valueOf(viewName.trim().split(":")[1].trim());
            HttpStatus httpStatus = Arrays.stream(HttpStatus.values())
                    .filter(httpStatus1 -> httpStatus1.value()==status)
                    .findAny()
                    .orElse(HttpStatus.NOT_FOUND);
            StatusView statusView = (StatusView) super.createView("status-code.ftlh", locale);
            statusView.setStatus(httpStatus);
            return statusView;
        }
        return null;
    }
}
