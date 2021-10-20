package com.guihx;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class InitialActivitiTable {

    @Autowired
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void testGenerateTables() {
        // 获取默认的流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        log.info("defaultProcessEngine:{}", defaultProcessEngine);
        log.info("defaultProcessEngine.name:{}", defaultProcessEngine.getName());
    }

    @Test
    public void deploy(){
        Deployment diyi = processEngine.getRepositoryService().createDeployment().name("第一个部署的流程").addClasspathResource("processes/原石.bpmn").deploy();
        System.out.println(diyi.getId());
    }

    @Test
    public void start(){
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("第一个流程");
        System.out.println(processInstance.getId());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    @Test
    public void getTask(){
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("").list();
        if(taskList!=null&&taskList.size()>0){
            for (Task task : taskList) {
                System.out.println(task.getId());
                System.out.println(task.getAssignee());
                System.out.println(task.getName());
            }
        }
    }

    @Test
    public void banli(){
        processEngine.getTaskService().complete("10002");
    }
}
