package com.webank.fb.auth.fbauthsdk;

import com.webank.fb.auth.fbauthsdk.config.AbiBinConfig;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.transaction.codec.decode.TransactionDecoderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BaseTest {
    @Autowired
    private CryptoSuite cryptoSuite;
    @Autowired
    protected Client client;
    @Autowired
    protected AbiBinConfig abiBinConfig;
    public BigInteger blockNumberInterval = BigInteger.valueOf(3600);
    public TransactionDecoderInterface decoder;


    public CryptoKeyPair u1;
    public CryptoKeyPair u2;
    public CryptoKeyPair u3;
    public CryptoKeyPair u4;
    public CryptoKeyPair u5;

    @PostConstruct
    void contextLoads() {
        u1 = cryptoSuite.createKeyPair( "b958fce4cf2374b090570cee273de82cbcc755fdbddb5eae1cd34bf307245f0c");
        u2 = cryptoSuite.createKeyPair( "a1916b6aaa70f56c3c118d75793c1cb7b7d5d414555d49052921e1404103fbfc");
        u3 = cryptoSuite.createKeyPair( "cdc996acda5109a92d010fc3ede77e082778d0cd43b76c7cdeca2a17b564335e");
        u4 = cryptoSuite.createKeyPair("ee2050986239f0343877df37d5cbdbf74c023f96d2d4dbc2e57aa334f11f0d71");
        u5 = cryptoSuite.createKeyPair( "b16193c224b707053d228bf67149ef0f58795c65fc12efa5b4f58b271b86a221");
        decoder = new TransactionDecoderService(cryptoSuite);
    }

}
