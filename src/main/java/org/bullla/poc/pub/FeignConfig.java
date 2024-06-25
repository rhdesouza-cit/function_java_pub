package org.bullla.poc.pub;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class FeignConfig {

    private FeignConfig() {
    }

    public static <T> T createClient(Class<T> clientClass, String url) {
        return Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(clientClass, url);
    }

}
