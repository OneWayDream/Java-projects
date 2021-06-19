package ru.itis.javalab.server.extensions;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class StatusView extends FreeMarkerView {

    protected HttpStatus status;

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setStatus(status.value());
        Map<String, Object> map = this.getAttributesMap();
        map.put("status_string", status.getReasonPhrase());
        this.setAttributesMap(map);
        super.render(model, request, response);
    }
}
