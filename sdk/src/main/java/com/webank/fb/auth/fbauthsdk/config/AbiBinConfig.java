/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.fb.auth.fbauthsdk.config;

import com.webank.fb.auth.fbauthsdk.enums.ContractEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Data
@Configuration
@Slf4j
public class AbiBinConfig {
    public static final String BIN_POST_FIX = ".bin";
    public static final String ABI_POST_FIX = ".abi";
    public static final String ABI_PATH = "abi/";
    public static final String ECC_BIN_PATH = "bin/";
    public static final String SM_BIN_PATH = "bin/sm/";

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private Client client;

    private Map<String, String> abiMap;
    private Map<String, String> binMap;

    @PostConstruct
    public void initAbiConfig() throws IOException {
        abiMap = abiMapInit();
        binMap = binMapInit();
    }

    public Map<String, String> abiMapInit() throws IOException {

        Map<String, String> map = new HashMap<>();
        for(ContractEnum contractName : ContractEnum.values()){
            String abiFileUrl = ABI_PATH + contractName.getValue() + ABI_POST_FIX;
            String abiStr = load(abiFileUrl);
            map.put(contractName.getValue(), abiStr);
        }
        return map;
    }

    public Map<String, String> binMapInit() throws IOException {
        Map<String, String> map = new HashMap<>();
        String binPath = "";
        switch (client.getCryptoType()){
            case 0:
                binPath = ECC_BIN_PATH;
                break;
            case 1:
                binPath = SM_BIN_PATH;
                break;
            default:
        }
        for(ContractEnum contractName : ContractEnum.values()){
            String binFileUrl = binPath + contractName.getValue() + BIN_POST_FIX;
            String abiStr = load(binFileUrl);
            map.put(contractName.getValue(), abiStr);
        }
        return map;
    }

    private String load(String resource){
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try(InputStream input = cl.getResourceAsStream(resource)){
            if(input == null) {
                throw new IOException("Resource not found:" + resource);
            }
            return IOUtils.toString(input, Charsets.UTF_8);
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
        return null;
    }
}
