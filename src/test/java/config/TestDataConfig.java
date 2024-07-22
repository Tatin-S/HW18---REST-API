package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:testdata.properties"})

public interface TestDataConfig extends Config {
    String userLogin();

    String userPassword();

    String isbn();
}