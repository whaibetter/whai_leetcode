package cn.whai.activiti.activiti7demo;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Activiti7DemoApplicationTests {


    @Autowired
    private RepositoryService repositoryService;

    @Test
    void contextLoads() {

        //进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/leave.bpmn")
                .addClasspathResource("bpmn/leave.png")
                .name("请假流程")
                .deploy();
        //输出部署的一些信息
        System.out.println("流程部署ID:"+deployment.getId());
        System.out.println("流程部署名称:"+deployment.getName());
        /**
         * Opening JDBC Connection
         * ==>  Preparing: insert into ACT_RE_DEPLOYMENT(ID_, NAME_, CATEGORY_, KEY_, TENANT_ID_, DEPLOY_TIME_, ENGINE_VERSION_) values(?, ?, ?, ?, ?, ?, ?)
         * ==> Parameters: b931f093-a0bf-11ef-a8d1-6ac6acfdceec(String), 请假流程(String), null, null, (String), 2024-11-12 14:31:17.803(Timestamp), null
         * <==    Updates: 1
         * ==>  Preparing: INSERT INTO ACT_GE_BYTEARRAY(ID_, REV_, NAME_, BYTES_, DEPLOYMENT_ID_, GENERATED_) VALUES (?, 1, ?, ?, ?, ?) , (?, 1, ?, ?, ?, ?)
         * ==> Parameters: b931f094-a0bf-11ef-a8d1-6ac6acfdceec(String), bpmn/leave.png(String), java.io.ByteArrayInputStream@3d4ea4cf(ByteArrayInputStream), b931f093-a0bf-11ef-a8d1-6ac6acfdceec(String), false(Boolean), b93217a5-a0bf-11ef-a8d1-6ac6acfdceec(String), bpmn/leave.bpmn(String), java.io.ByteArrayInputStream@655e024(ByteArrayInputStream), b931f093-a0bf-11ef-a8d1-6ac6acfdceec(String), false(Boolean)
         * <==    Updates: 2
         * Closing JDBC Connection [Transaction-aware proxy for target Connection [HikariProxyConnection@1997102220 wrapping com.mysql.cj.jdbc.ConnectionImpl@4ac2b4c6]]
         * 流程部署ID:b931f093-a0bf-11ef-a8d1-6ac6acfdceec
         * 流程部署名称:请假流程
         */

    }


    @Test
    void queryProcessDefinition() {

        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            System.out.println("流程定义ID:" + processDefinition.getId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程定义key:" + processDefinition.getKey());
            System.out.println("流程定义版本:" + processDefinition.getVersion());
            System.out.println("流程部署ID:" + processDefinition.getDeploymentId());
            System.out.println("流程定义资源名称:" + processDefinition.getResourceName());
            System.out.println("流程定义图片资源名称:" + processDefinition.getDiagramResourceName());
            System.out.println("流程定义描述:" + processDefinition.getDescription());
            System.out.println("流程定义分类:" + processDefinition.getCategory());
            System.out.println("======================================================");
        }
        /**
         * Opening JDBC Connection
         * ==>  Preparing: select distinct RES.* from ACT_RE_PROCDEF RES order by RES.ID_ asc LIMIT ? OFFSET ?
         * ==> Parameters: 2147483647(Integer), 0(Integer)
         * <==    Columns: ID_, REV_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, DGRM_RESOURCE_NAME_, DESCRIPTION_, HAS_START_FORM_KEY_, HAS_GRAPHICAL_NOTATION_, SUSPENSION_STATE_, TENANT_ID_, ENGINE_VERSION_
         * <==        Row: Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec, 1, http://www.activiti.org/processdef, Part1_Deployment, Part1_Deployment, 1, 0c82b70d-a0c1-11ef-9820-6ac6acfdceec, bpmn/leave.bpmn, bpmn/leave.png, null, 0, 1, 1, , null
         * <==      Total: 1
         * Closing JDBC Connection [Transaction-aware proxy for target Connection [HikariProxyConnection@1566233058 wrapping com.mysql.cj.jdbc.ConnectionImpl@17796f47]]
         * 流程定义ID:Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec
         * 流程定义名称:Part1_Deployment
         * 流程定义key:Part1_Deployment
         * 流程定义版本:1
         * 流程部署ID:0c82b70d-a0c1-11ef-9820-6ac6acfdceec
         * 流程定义资源名称:bpmn/leave.bpmn
         * 流程定义图片资源名称:bpmn/leave.png
         * 流程定义描述:null
         * 流程定义分类:http://www.activiti.org/processdef
         * ======================================================
         */
    }
    // 流程实例
    // 流程定义（ProcessDefinition）与流程实例（ProcessInstance）是一对多关系（salvio修改）
    @Autowired
    private RuntimeService runtimeService;
    /**
     * 初始化流程实例
     */
    @Test
    public void initProcessInstance() {
        // 流程定义KEY
        String processDefinitionKey = "Part1_Deployment";
        // 业务表KEY（用于把业务数据与Activiti7流程数据相关联）
        String businessKey = "4208169753200945";
        // 参数
        Map<String, Object> variables = new HashMap<>(16);
        ProcessInstance processInstance = this.runtimeService
                .startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        System.out.println("流程实例ID：" + processInstance.getProcessInstanceId());
        /**
         * Opening JDBC Connection
         * ==>  Preparing: select * from ACT_RE_PROCDEF where KEY_ = ? and (TENANT_ID_ = '' or TENANT_ID_ is null) and VERSION_ = (select max(VERSION_) from ACT_RE_PROCDEF where KEY_ = ? and (TENANT_ID_ = '' or TENANT_ID_ is null))
         * ==> Parameters: Part1_Deployment(String), Part1_Deployment(String)
         * <==    Columns: ID_, REV_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, DGRM_RESOURCE_NAME_, DESCRIPTION_, HAS_START_FORM_KEY_, HAS_GRAPHICAL_NOTATION_, SUSPENSION_STATE_, TENANT_ID_, ENGINE_VERSION_
         * <==        Row: Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec, 1, http://www.activiti.org/processdef, Part1_Deployment, Part1_Deployment, 1, 0c82b70d-a0c1-11ef-9820-6ac6acfdceec, bpmn/leave.bpmn, bpmn/leave.png, null, 0, 1, 1, , null
         * <==      Total: 1
         * ==>  Preparing: select * from ACT_RU_VARIABLE where TASK_ID_ = ?
         * ==> Parameters: b25c87dd-a0c3-11ef-8ba8-68c6acfdcef0(String)
         * <==      Total: 0
         * ==>  Preparing: insert into ACT_HI_TASKINST ( ID_, PROC_DEF_ID_, PROC_INST_ID_, EXECUTION_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, OWNER_, ASSIGNEE_, START_TIME_, CLAIM_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TASK_DEF_KEY_, FORM_KEY_, PRIORITY_, DUE_DATE_, CATEGORY_, TENANT_ID_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
         * ==> Parameters: b25c87dd-a0c3-11ef-8ba8-68c6acfdcef0(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25ab31a-a0c3-11ef-8ba8-68c6acfdcef0(String), UserTask(String), null, null, null, admin(String), 2024-11-12 14:59:44.325(Timestamp), null, null, null, null, sid-192966db-031d-4a50-886d-379935dd9bc1(String), null, 50(Integer), null, null, (String)
         * <==    Updates: 1
         * ==>  Preparing: insert into ACT_HI_PROCINST ( ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_ID_, START_TIME_, END_TIME_, DURATION_, START_USER_ID_, START_ACT_ID_, END_ACT_ID_, SUPER_PROCESS_INSTANCE_ID_, DELETE_REASON_, TENANT_ID_, NAME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
         * ==> Parameters: b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), 4208169753200945(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), 2024-11-12 14:59:44.311(Timestamp), null, null, null, sid-26d6e725-878e-411d-b23d-6df4ae3cbd24(String), null, null, null, (String), null
         * <==    Updates: 1
         * ==>  Preparing: insert into ACT_HI_ACTINST ( ID_, PROC_DEF_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TENANT_ID_ ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) , (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * ==> Parameters: b25ada2b-a0c3-11ef-8ba8-68c6acfdcef0(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25ab31a-a0c3-11ef-8ba8-68c6acfdcef0(String), sid-26d6e725-878e-411d-b23d-6df4ae3cbd24(String), null, null, null, startEvent(String), null, 2024-11-12 14:59:44.314(Timestamp), 2024-11-12 14:59:44.316(Timestamp), 2(Long), null, (String), b25b4f5c-a0c3-11ef-8ba8-68c6acfdcef0(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25ab31a-a0c3-11ef-8ba8-68c6acfdcef0(String), sid-192966db-031d-4a50-886d-379935dd9bc1(String), b25c87dd-a0c3-11ef-8ba8-68c6acfdcef0(String), null, UserTask(String), userTask(String), admin(String), 2024-11-12 14:59:44.317(Timestamp), null, null, null, (String)
         * <==    Updates: 2
         * ==>  Preparing: insert into ACT_HI_IDENTITYLINK (ID_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_INST_ID_) values (?, ?, ?, ?, ?, ?)
         * ==> Parameters: b25cd5fe-a0c3-11ef-8ba8-68c6acfdcef0(String), participant(String), admin(String), null, null, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String)
         * <==    Updates: 1
         * ==>  Preparing: insert into ACT_RU_EXECUTION (ID_, REV_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_ID_, ACT_ID_, IS_ACTIVE_, IS_CONCURRENT_, IS_SCOPE_,IS_EVENT_SCOPE_, IS_MI_ROOT_, PARENT_ID_, SUPER_EXEC_, ROOT_PROC_INST_ID_, SUSPENSION_STATE_, TENANT_ID_, NAME_, START_TIME_, START_USER_ID_, IS_COUNT_ENABLED_, EVT_SUBSCR_COUNT_, TASK_COUNT_, JOB_COUNT_, TIMER_JOB_COUNT_, SUSP_JOB_COUNT_, DEADLETTER_JOB_COUNT_, VAR_COUNT_, ID_LINK_COUNT_) values (?, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) , (?, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * ==> Parameters: b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), 4208169753200945(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), null, true(Boolean), false(Boolean), true(Boolean), false(Boolean), false(Boolean), null, null, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), 1(Integer), (String), null, 2024-11-12 14:59:44.311(Timestamp), null, false(Boolean), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), b25ab31a-a0c3-11ef-8ba8-68c6acfdcef0(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), null, Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), sid-192966db-031d-4a50-886d-379935dd9bc1(String), true(Boolean), false(Boolean), false(Boolean), false(Boolean), false(Boolean), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), null, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), 1(Integer), (String), null, 2024-11-12 14:59:44.313(Timestamp), null, false(Boolean), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer)
         * <==    Updates: 2
         * ==>  Preparing: insert into ACT_RU_TASK (ID_, REV_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, PRIORITY_, CREATE_TIME_, OWNER_, ASSIGNEE_, DELEGATION_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, TASK_DEF_KEY_, DUE_DATE_, CATEGORY_, SUSPENSION_STATE_, TENANT_ID_, FORM_KEY_, CLAIM_TIME_) values (?, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
         * ==> Parameters: b25c87dd-a0c3-11ef-8ba8-68c6acfdcef0(String), UserTask(String), null, null, 50(Integer), 2024-11-12 14:59:44.317(Timestamp), null, admin(String), null, b25ab31a-a0c3-11ef-8ba8-68c6acfdcef0(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec(String), sid-192966db-031d-4a50-886d-379935dd9bc1(String), null, null, 1(Integer), (String), null, null
         * <==    Updates: 1
         * ==>  Preparing: insert into ACT_RU_IDENTITYLINK (ID_, REV_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_INST_ID_, PROC_DEF_ID_) values (?, 1, ?, ?, ?, ?, ?, ?)
         * ==> Parameters: b25cd5fe-a0c3-11ef-8ba8-68c6acfdcef0(String), participant(String), admin(String), null, null, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), null
         * <==    Updates: 1
         * Closing JDBC Connection [Transaction-aware proxy for target Connection [HikariProxyConnection@621103114 wrapping com.mysql.cj.jdbc.ConnectionImpl@4ac2b4c6]]
         * 流程实例ID：b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0
         */
    }

    /**
     * 查询流程实例
     */
    @Test
    public void getProcessInstance() {
        String processInstanceId = "b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0";
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println("ProcessInstanceId：" + processInstance.getProcessInstanceId());
        System.out.println("ProcessDefinitionId：" + processInstance.getProcessDefinitionId());
        System.out.println("isEnded：" + processInstance.isEnded());
        System.out.println("isSuspended：" + processInstance.isSuspended());
        /**
         * Opening JDBC Connection
         * ==>  Preparing: select distinct RES.* , P.KEY_ as ProcessDefinitionKey, P.ID_ as ProcessDefinitionId, P.NAME_ as ProcessDefinitionName, P.VERSION_ as ProcessDefinitionVersion, P.DEPLOYMENT_ID_ as DeploymentId, S.PROC_INST_ID_ AS PARENT_PROC_INST_ID_ from ACT_RU_EXECUTION RES inner join ACT_RE_PROCDEF P on RES.PROC_DEF_ID_ = P.ID_ left outer join ACT_RU_EXECUTION S on RES.SUPER_EXEC_ = S.ID_ WHERE RES.PARENT_ID_ is null and RES.ID_ = ? and RES.PROC_INST_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
         * ==> Parameters: b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0(String), 2147483647(Integer), 0(Integer)
         * <==    Columns: ID_, REV_, PROC_INST_ID_, BUSINESS_KEY_, PARENT_ID_, PROC_DEF_ID_, SUPER_EXEC_, ROOT_PROC_INST_ID_, ACT_ID_, IS_ACTIVE_, IS_CONCURRENT_, IS_SCOPE_, IS_EVENT_SCOPE_, IS_MI_ROOT_, SUSPENSION_STATE_, CACHED_ENT_STATE_, TENANT_ID_, NAME_, START_TIME_, START_USER_ID_, LOCK_TIME_, IS_COUNT_ENABLED_, EVT_SUBSCR_COUNT_, TASK_COUNT_, JOB_COUNT_, TIMER_JOB_COUNT_, SUSP_JOB_COUNT_, DEADLETTER_JOB_COUNT_, VAR_COUNT_, ID_LINK_COUNT_, ProcessDefinitionKey, ProcessDefinitionId, ProcessDefinitionName, ProcessDefinitionVersion, DeploymentId, PARENT_PROC_INST_ID_
         * <==        Row: b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0, 1, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0, 4208169753200945, null, Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec, null, b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0, null, 1, 0, 1, 0, 0, 1, null, , null, 2024-11-12 14:59:44.311, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, Part1_Deployment, Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec, Part1_Deployment, 1, 0c82b70d-a0c1-11ef-9820-6ac6acfdceec, null
         * <==      Total: 1
         * Closing JDBC Connection [Transaction-aware proxy for target Connection [HikariProxyConnection@1118158255 wrapping com.mysql.cj.jdbc.ConnectionImpl@77eb0e47]]
         * ProcessInstanceId：b25a64f9-a0c3-11ef-8ba8-68c6acfdcef0
         * ProcessDefinitionId：Part1_Deployment:1:0c957bc0-a0c1-11ef-9820-6ac6acfdceec
         * isEnded：false
         * isSuspended：false
         */

        // 查询流程实例列表
        List<ProcessInstance> processInstanceList = this.runtimeService.createProcessInstanceQuery().list();
        if (!CollectionUtils.isEmpty(processInstanceList)) {
            processInstanceList.forEach(pi -> {
                System.out.println("ProcessInstanceId：" + pi.getProcessInstanceId());
                System.out.println("ProcessDefinitionId：" + pi.getProcessDefinitionId());
                System.out.println("isEnded：" + pi.isEnded());
                System.out.println("isSuspended：" + pi.isSuspended());
            });
        }
        // 挂起流程实例
        this.runtimeService.suspendProcessInstanceById(processInstanceId);
        // 激活流程实例
        this.runtimeService.activateProcessInstanceById(processInstanceId);
        // 删除流程实例
        this.runtimeService.deleteProcessInstance(processInstanceId, "测试删除流程实例");
    }

    /**
     * 任务管理
     */
    @Autowired
    private TaskService taskService;

    /**
     * 查询任务列表
     */
    @Test
    public void listTasks() {
        List<Task> taskList = this.taskService.createTaskQuery().list();
        if (!CollectionUtils.isEmpty(taskList)) {
            taskList.forEach(task -> {
                System.out.println("Id：" + task.getId());
                System.out.println("Name：" + task.getName());
                System.out.println("Assignee：" + task.getAssignee());
            });
        }
    }


    @Test
    public void listTasksByAssignee() {
        TaskQuery query = taskService.createTaskQuery();
        List<Task> tasks = query.listPage(1, 2);






        /**
         * 查询Admin的代办任务
         */
        String assignee = "admin";
        List<Task> taskList = this.taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if (!CollectionUtils.isEmpty(taskList)) {
            taskList.forEach(task -> {
                System.out.println("Id：" + task.getId());
                System.out.println("Name：" + task.getName());
                System.out.println("Assignee：" + task.getAssignee());
            });
        }


        // 完成任务
        String taskId = "354b9d90-477f-11ed-abfa-e4a8dfd43d4a";
        Map<String, Object> variables = new HashMap<>(16);
        this.taskService.complete(taskId, variables);


        // 给定用户成为任务的受理人。
        String userId = "jason";
        taskService.claim(taskId, userId);

        // 归还任务，分配的任务回收，撤回
        taskService.unclaim(taskId);

        // 将给定任务的被分派人更改为给定的 userId。不检查身份组件是否知道该用户。
        taskService.setAssignee(taskId, userId);
    }

    @Autowired
    private HistoryService historyService;

    // 历史记录
    @Test
    public void history() {
        // 查询历史流程实例
        HistoricProcessInstanceQuery historicProcessInstanceQuery =
                this.historyService
                        .createHistoricProcessInstanceQuery()
                        .orderByProcessInstanceEndTime();
        List<HistoricProcessInstance> list = historicProcessInstanceQuery.list();

        list.forEach(historicProcessInstance -> {
            System.out.println("ProcessInstanceId：" + historicProcessInstance.getId());
            System.out.println("ProcessDefinitionId：" + historicProcessInstance.getProcessDefinitionId());
        });



        // 根据流程实例ID查询历史
        String processInstanceId = "0f8a9b00-479e-11ed-af85-e4a8dfd43d4a";
        List<HistoricProcessInstance> historicProcessInstances =
                this.historyService
                        .createHistoricProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .list();
        historicProcessInstances.forEach(historicProcessInstance -> {
            System.out.println("ProcessInstanceId：" + historicProcessInstance.getId());
            System.out.println("ProcessDefinitionId：" + historicProcessInstance.getProcessDefinitionId());
        });
    }



}
