package com.webank.fb.auth.fbauthsdk.config;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.util.HashMap;

@Configuration
@Slf4j
@ComponentScan
public class BeanConfig {

    @Autowired
    private BcosConfig bcosConfig;
    @Autowired
    private SysConfig sysConfig;

    @Bean
    public Client client() throws Exception {

        ConfigOption configOption = new ConfigOption(loadProperty());
        Client client = new BcosSDK(configOption).getClient(sysConfig.getGroupId());
        BigInteger blockNumber = client.getBlockNumber().getBlockNumber();
        if (log.isInfoEnabled()) {
            log.info("Chain connect successful. Current block number {}", blockNumber);
        }

        return client;
    }

    @Bean
    public CryptoSuite cryptoSuite(Client client) throws Exception {
        int type = client.getCryptoType();
        return new CryptoSuite(client.getCryptoType());
    }

    private ConfigProperty loadProperty(){
        ConfigProperty configProperty = new ConfigProperty();
        configProperty.setCryptoMaterial(bcosConfig.getCryptoMaterial());
        configProperty.setNetwork(new HashMap<String, Object>(){{
            put("peers", bcosConfig.getNetwork().get("peers"));
        }} );
        return configProperty;
    }
}