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

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlSecuritySchemeBean implements InitializingBean {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Value("${spring.session_jdbc_version}")
    protected String sessionJdbcVersion;

    @Value("${spring.session_jdbc.path_to_scheme}")
    protected String pathToScheme;

    @Value("${spring.session_jdbc.scheme_file}")
    protected String schemeFile;

    @Override
    public void afterPropertiesSet() throws Exception {
        String separator = File.separator;
        JarFile jar = new JarFile(ResourceUtils.getFile("classpath:../lib/"
                + separator
                + "spring-session-jdbc-" + sessionJdbcVersion + ".jar")
                .getPath());
        String filePath = pathToScheme.equals("${spring.session_jdbc.path_to_scheme}") ? "org/springframework/session/jdbc" : pathToScheme;
        String fileName = schemeFile.equals("${spring.session_jdbc.scheme_file}") ? "schema-postgresql.sql" : schemeFile;
        String path = filePath + "/" + fileName;
        JarEntry entry = jar.getJarEntry(path);
        InputStream input = jar.getInputStream(entry);
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        try{
            jdbcTemplate.update(contentBuilder.toString());
        } catch (Exception ex){
            //ignore
        }
        jar.close();
        input.close();
    }
}
