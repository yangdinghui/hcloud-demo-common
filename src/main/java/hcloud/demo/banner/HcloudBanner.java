package hcloud.demo.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class HcloudBanner implements Banner {
    private static final String BANNER = "   ___  _____  _____  _      _____  _   _ ______\n  |_  |/  ___|/  __ \\| |    |  _  || | | ||  _  \\\n    | |\\ `--. | /  \\/| |    | | | || | | || | | |\n    | | `--. \\| |    | |    | | | || | | || | | |\n/\\__/ //\\__/ /| \\__/\\| |____\\ \\_/ /| |_| || |/ /\n\\____/ \\____/  \\____/\\_____/ \\___/  \\___/ |___/";
    private static final String APPLICATON_INFO = ":: By JsCloud(Shanghai) ::  %s\n:: Spring Boot ::           %s";

    public HcloudBanner() {
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        String name = environment.getProperty("spring.application.name");
        String port = environment.getProperty("server.port");
        name = name != null ? " " + name + ":" + port : "";
        String appVersion = this.getApplicationVersion(sourceClass);
        appVersion = name + this.getVersionString(appVersion, true);
        String bootVersion = this.getBootVersion();
        bootVersion = this.getVersionString(bootVersion, true);
        String info = String.format(":: By JsCloud(Shanghai) ::  %s\n:: Spring Boot ::           %s", appVersion, bootVersion);
        printStream.println();
        printStream.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, "   ___  _____  _____  _      _____  _   _ ______\n  |_  |/  ___|/  __ \\| |    |  _  || | | ||  _  \\\n    | |\\ `--. | /  \\/| |    | | | || | | || | | |\n    | | `--. \\| |    | |    | | | || | | || | | |\n/\\__/ //\\__/ /| \\__/\\| |____\\ \\_/ /| |_| || |/ /\n\\____/ \\____/  \\____/\\_____/ \\___/  \\___/ |___/"}));
        printStream.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, info}));
        printStream.println();
    }

    protected String getApplicationVersion(Class<?> sourceClass) {
        Package sourcePackage = sourceClass != null ? sourceClass.getPackage() : null;
        return sourcePackage != null ? sourcePackage.getImplementationVersion() : null;
    }

    protected String getBootVersion() {
        return SpringBootVersion.getVersion();
    }

    private String getVersionString(String version, boolean format) {
        if (version == null) {
            return "";
        } else {
            return format ? " (v" + version + ")" : version;
        }
    }
}
