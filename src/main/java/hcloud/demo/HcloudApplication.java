package hcloud.demo;

import hcloud.demo.banner.HcloudBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class HcloudApplication {

    private HcloudApplication() {
    }

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        SpringApplication application = new SpringApplication(new Class[]{primarySource});
        application.setBanner(new HcloudBanner());
        return application.run(args);
    }
}
