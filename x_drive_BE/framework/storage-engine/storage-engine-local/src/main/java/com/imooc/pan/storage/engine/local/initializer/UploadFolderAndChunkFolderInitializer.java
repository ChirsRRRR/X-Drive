package com.imooc.pan.storage.engine.local.initializer;

import com.imooc.pan.storage.engine.local.LocalStorageEngine;
import com.imooc.pan.storage.engine.local.config.LocalStorageEngineConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 初始化文件根目录的问价年分片存储的初始化器
 */
@Component
@Slf4j
public class UploadFolderAndChunkFolderInitializer implements CommandLineRunner {

    @Autowired
    private LocalStorageEngineConfig config;

    @Override
    public void run(String... args) throws Exception {
        FileUtils.forceMkdir(new File(config.getRootFilePath()));
        log.info("The root file path has been created!");
        FileUtils.forceMkdir(new File(config.getRootFileChunkPath()));
        log.info("The root file chunk path has been created!");
    }
}
