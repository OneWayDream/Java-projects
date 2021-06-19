package ru.itis.javalab.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlFileExecutorBean implements InitializingBean {

    @Value("${spring.datasource.files}")
    protected String dataSourceFiles;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] files = new String[0];
        String separator = File.separator;
        try{
            files = Objects.requireNonNull(dataSourceFiles).split(";");
        } catch (NullPointerException ex){
            //ignore
        }
        for (String file : files) {
            File dataFile = ResourceUtils.getFile("classpath:schemas" + separator + file);
            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(dataFile.toPath().toString()), StandardCharsets.UTF_8)) {
                stream.forEach(contentBuilder::append);
            } catch (IOException ex) {
                //ignore
            }
            jdbcTemplate.update(contentBuilder.toString());
        }
    }

}
