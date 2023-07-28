package org.zhuyb0614.eacp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String contentPath = env.getProperty("server.servlet.context-path");
            log.info("Swagger URL http://{}:{}{}/swagger-ui.html", hostAddress, port == null ? "8080" : port, StringUtils.isEmpty(contentPath) ? "" : contentPath);
        } catch (UnknownHostException e) {
            //do noting
        }
    }

}