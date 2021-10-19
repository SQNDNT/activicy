package com.guihx;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

@Slf4j
public class InitialActivitiTable {

    @Test
    public void testGenerateTables() {
        // 获取默认的流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        log.info("defaultProcessEngine:{}", defaultProcessEngine);
        log.info("defaultProcessEngine.name:{}", defaultProcessEngine.getName());
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
    }
}
