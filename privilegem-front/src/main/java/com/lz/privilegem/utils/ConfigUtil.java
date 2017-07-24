package com.lz.privilegem.utils;

/**
 * Created by lizhi on 2017/7/7.
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtil {

    private @Value("${cas.server.url}")String casServerUrl;
    private @Value("${cas.service.url}")String casServiceUrl;

    public String getCasServerUrl() {
        return casServerUrl;
    }

    public String getCasServiceUrl() {
        return casServiceUrl;
    }
}
