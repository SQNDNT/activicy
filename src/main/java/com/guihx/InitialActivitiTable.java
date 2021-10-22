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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        Deployment diyi = processEngine.getRepositoryService().createDeployment().name("第一个部署的流程").addClasspathResource("processes/原石.bpmn").deploy();
//        Deployment diyi = processEngine.getRepositoryService().createDeployment().name("网关测试").addClasspathResource("processes/并排.bpmn").deploy();
        Deployment diyi = processEngine.getRepositoryService().createDeployment().name("会签直接结束").addClasspathResource("processes/会签直接结束.bpmn").deploy();
        System.out.println(diyi.getId());
    }

    @Test
    public void start(){
        Map<String,Object> map = new HashMap<>();
        map.put("cyList", Arrays.asList("jack","tom"));
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("会签直接结束 ",map);
        System.out.println(processInstance.getId());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    @Test
    public void getTask(){
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("右").list();
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
        Map<String,Object> map = new HashMap<>();
        map.put("flag", false);
        map.put("resule", "n");
        processEngine.getTaskService().complete("22526",map);
    }
}
