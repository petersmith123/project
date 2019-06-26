package com.springbasetest;

import freemarker.ext.beans.HashAdapter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/applicationContext.xml"})
public class ActivityTest {

    @Autowired
    private ProcessEngineConfiguration  processEngineConfiguration;


    private  ProcessEngine processEngine;


    @Before
     public  void getProcessEnginee(){
        //processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
        processEngine=processEngineConfiguration.setHistoryLevel(HistoryLevel.FULL).buildProcessEngine();
     }


        @Test
        public void deployment() {

           // 流程定义中的id属性被设置为 {processDefinitionKey}:{processDefinitionVersion}:{generated-id},
            // 这里的generated-id是一个唯一的数字
            Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署相关的Service
                    .createDeployment()//创建一个部署对象
                    .name("listenerTest")//添加部署的名称
                    .addClasspathResource("bpm/listenerTest.bpmn")
                    //从classpath下加载资源，一次一个
                    .deploy();//完成部署
            System.out.println("部署ID"+deployment.getId());//1
            System.out.println("部署名称"+deployment.getName());
        }

        /**
         *启动流程实例
         */
        @Test
        public void startProcessInstance() {
            String processDefinitionKey = "listenerTest";
            Map<String,Object> variables =new HashMap<String, Object>();
            //variables.put("day",100);
            variables.put("user1","0071");
            variables.put("user2","JAN1");
            variables.put("user3","JIM1");
            //System.out.println(processEngine.getName());
            // processEngine.getRuntimeService();
             ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey,variables);
            // Map<String,Object> map=processInstance.getProcessVariables();
            // System.out.println(map);
             System.out.println("流程实例ID:"+processInstance.getId());  //2501
            //对应数据库act_ru_execution
            System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId()); //helloword:1:4
           //对应数据库act_re_deployment
        }
    @Test
    public void findTask() {
        String processDefinitionKey = "listenerTest";
        String enginee="JAN1";
         List<Task>  list=findByTasksByAssignee(enginee,processDefinitionKey,1);
         Map<String,Object> map=new HashMap<String, Object>();
         map.put("message","nopass");
         finished(list.get(0),map);
    }

    // 认领任务
    @Test
    public void claimTask(){
            String assignee="d";
        String processDefinitionKey = "testProcess";
           List<Task> list=findByTasksByAssignee(assignee,processDefinitionKey,0);
        processEngine.getTaskService().claim(list.get(0).getId(),assignee);

    }
    // 放弃已经认领的任务
    @Test
    public void giveUp(){
        String assignee="d";
        String processDefinitionKey = "testProcess";
        List<Task> list=findByTasksByAssignee(assignee,processDefinitionKey,1);
        processEngine.getTaskService().setAssignee(list.get(0).getId(),null);

    }

    @Test
    public  void addUser(){
            String assignee="gg";
        String assignee2="gg";
        String processDefinitionKey = "testProcess";
        List<Task> list=findByTasksByAssignee(assignee,processDefinitionKey,0);
     //   processEngine.getTaskService().addCandidateUser(list.get(0).getId(),assignee2);
    }


   //查看流程定义
    @Test
    public  void getProcessList(){
            List<ProcessDefinition> list= processEngine.getRepositoryService().createProcessDefinitionQuery().list();
            for (ProcessDefinition processDefinition:list){
                System.out.println("--------------------------------------------------");
                System.out.println("id-"+processDefinition.getId()+"\r\nkey-"+processDefinition.getKey()+"\r\nname-"+processDefinition.getName());
                System.out.println("category-"+processDefinition.getCategory()+"\r\n deploymentId-"+processDefinition.getDeploymentId()+"\r\n DiagramResourceName-"+processDefinition.getDiagramResourceName());
                System.out.println("--------------------------------------------------");
            }
    }

    @Test
    public  void delProcess(){
        processEngine.getRepositoryService().deleteDeployment("3701",true);
    }


    @Test
    public void finisedTask(){
        String processDefinitionKey = "listenerTest";
        String enginee="007";
        List<Task> list=findByTasksByAssignee(enginee,processDefinitionKey,1);
        Map<String,Object> variables =new HashMap<String, Object>();
       /* variables.put("money",10);
        if(list.size()>0){

            finished(list.get(0),variables);
        }else{
            System.err.println("暂无任务可完成");
        }*/
    }

    //完成任务
    public  void finished(Task task,Map map){

       // Task task= processEngine.getTaskService().createTaskQuery().taskAssignee(enginee).processDefinitionKey(processDefinitionKey).singleResult();

        //processEngine.getTaskService().addComment(task.getId(),task.getProcessInstanceId(),"同意");

        processEngine.getTaskService().complete(task.getId(),map);
       // processEngine.getTaskService().complete((task.getId()));
       //String processInstanceId=task.getProcessInstanceId();
       //String definedId= task.getProcessDefinitionId();
      // System.out.println(processInstanceId);
      // System.out.println(definedId);
    }
    @Test
    public  void manageApprove(){
        String enginee="manager";
        String processDefinitionKey = "leave_process_3";
        Task task=processEngine.getTaskService().createTaskQuery().taskAssignee(enginee).processDefinitionKey(processDefinitionKey).singleResult();
        String processInstanceId=task.getProcessInstanceId();
        String definedId= task.getProcessDefinitionId();
        System.out.println(processInstanceId);
        System.out.println(definedId);
    }


    /****
     *
     * @param assignee
     * @param processDefinitionKey
     * @param type 0 taskCandidateUser查询 1  assignee 查询
     * @return
     */
    public  List<Task> findByTasksByAssignee(String assignee,String processDefinitionKey,int type){
        //用户任务分类：
        //分为4中状态：未签收/待办理、已签收/办理中、运行中/办理中、已完成/已办结

        //查询个人未签收 待办理任务
        List<Task> list=null;
        if(type==0){// 任务组形式用户查询
          list =processEngine.getTaskService().createTaskQuery().processDefinitionKey(processDefinitionKey).taskCandidateUser(assignee).list();
        }else{
            list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                    .createTaskQuery().processDefinitionKey(processDefinitionKey)//创建任务查询对象
                    .taskAssignee(assignee)//指定个人任务查询，指定代理人
                    .list();//以list形式记录对象
        }



        if(list != null && list.size()>0) {
            for(Task task:list) {
                System.out.println("任务ID:"+task.getId());//2501
                System.out.println("任务名称："+task.getName());//提交申请
                System.out.println("任务的创建时间："+task.getCreateTime());//Wed Jun 06 18:12:15 CST 2018
                System.out.println("任务的代理人："+task.getAssignee());//张三
                System.out.println("流程实例ID："+task.getProcessInstanceId());//2501
                System.out.println("执行对象ID："+task.getExecutionId());//2501
                System.out.println("流程定义ID："+task.getProcessDefinitionId());//helloword:1:4

            }
        }else{
            System.out.println("empty list ***********************************************************");

        }
        return list;
    }

    /***
     * 挂起一个流程/激活
     */
    @Test
    public  void suspendProcessDefinekey(){
        String processDefinitionKey = "leave_process_2";
        //挂起
        processEngine.getRepositoryService().suspendProcessDefinitionByKey(processDefinitionKey);
        //激活
       //   processEngine.getRepositoryService().activateProcessDefinitionById(definKey);
    }

    @Test
    public void getList() {
        String processDefinitionKey = "testProcess";

       List<Task> tasks=processEngine.getTaskService().createTaskQuery().processDefinitionKey(processDefinitionKey).list();

        List<IdentityLink> list = processEngine.getTaskService()//

                .getIdentityLinksForTask(tasks.get(0).getId());

        if (list != null && list.size() > 0) {

            for (IdentityLink identityLink : list) {

                System.out.println("任务ID：" + identityLink.getTaskId());

                System.out.println("用户ID：" + identityLink.getUserId());

                System.out.println("-----------------------------------");

            }
        }

    }

    /**
     * 获取历史任务
     */
    @Test
    public void getHistoryTaskList(){
        String defineId="listenerTest:1:3304";
        // 查看历史流程实例
        List<HistoricProcessInstance> list=processEngine.getHistoryService().createHistoricProcessInstanceQuery().processDefinitionId(defineId).finished().list();
        for (HistoricProcessInstance historicProcessInstance: list){
            System.out.println("startTime "+historicProcessInstance.getStartTime());
            System.out.println("endTime "+historicProcessInstance.getEndTime());
            System.out.println("startUserId "+historicProcessInstance.getStartUserId());
            System.out.println("ProcessDefinedId "+historicProcessInstance.getProcessDefinitionId());
            Map<String,Object> map=historicProcessInstance.getProcessVariables();
            System.out.println(map);
        }

        System.out.println("-------------------------------------------------------------------------------");
        // 查看历史流程任务
        List<HistoricActivityInstance> list2=processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(defineId).finished().list();
       System.out.println("size"+list2.size());
       for (HistoricActivityInstance activityInstance :list2){
           System.out.println("activitiId   "+activityInstance.getActivityId());
           System.out.println("activitiName  "+activityInstance.getActivityName());
           System.out.println("assignee   "+activityInstance.getAssignee());
       }

    }
}


