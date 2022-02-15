package com.example.woc.config;


import com.example.woc.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "manager.jwt")     //指定配置文件的key
public class JwtProperties {

    /**
     * 密钥
     */
    private String secret;

    /**
     * 公钥保存路径
     */
    private String pubKeyPath;


    /**
     * 私钥保存路径
     */
    private String priKeyPath;


    /**
     * token过期时间
     */
    private int expire;

    /**
     * 公钥
     */
    private PublicKey publicKey;


    /**
     * 私钥
     */
    private PrivateKey privateKey;

    /**
     * token名称
     */
    private String headerName;


    /**
     * @PostContruct：在构造方法执行之后执行该方法
     * 创建私钥和公钥，并且获取赋值
     */
    @PostConstruct
    public void init() throws Exception {

            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);

    }

}
