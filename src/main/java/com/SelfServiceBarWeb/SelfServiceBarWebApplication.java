package com.SelfServiceBarWeb;

import com.SelfServiceBarWeb.server.SocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Muki on 2018/11/4
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAsync
@MapperScan("com.SelfServiceBarWeb.mapper")
@EnableScheduling
public class SelfServiceBarWebApplication {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SelfServiceBarWebApplication.class);
        logger.debug("start application");
        SpringApplication.run(SelfServiceBarWebApplication.class, args);

        //启动socket服务
        SocketServer server = new SocketServer();
        server.startSocketServer(8188);
    }
}
