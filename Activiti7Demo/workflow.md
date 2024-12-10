## WordFlow 工作流引擎

是一种按照预定义规则【需要符合BPMN规范】进行部署，将业务和节点的流程进行分离【特定形式进行关联】，


### 二、什么是Activiti7？

Activiti 是一个工作流引擎， activiti 可以将业务系统中复杂的业务流程抽取出来，使用专门的
建模语言（BPMN2.0）进行定义，业务系统按照预先定义的流程进行执行

https://www.activiti.org

2.2 Activiti7内部核心机制

1️⃣业务流程图要规范化，需要遵守一套标准。

2️⃣业务流程图本质上就是一个XML文件，而XML可以存放所要的数据。

3️⃣读取业务流程图的过程就是解析XML文件的过程。

4️⃣读取一个业务流程图的结点就相当于解析一个XML的结点，进一步将数据插入到MySQL表中，形成一条记录。

5️⃣将一个业务流程图的所有节点都读取并存入到MySQL表中。

6️⃣后面只要读取MySQL表中的记录就相当于读取业务流程图的一个节点。

7️⃣业务流程的推进，后面就转换为读取表中的数据，并且处理数据，结束的时候这一行数据就可以删除了。

### BPMN

BPMN（Business Process Model And Notation），业务流程模型和符号，是由BPMI（Business Process Management Initiative）开发的一套的业务流程建模符号，使用BPMN提供的符号可以创建业务流程。2004年5月发布了BPMN1.0规范。

> [processOn BPMN 概念](https://www.processon.com/knowledge/bpmndiagram)
>
> ### BPMN 的核心元素
> - 活动：活动是 BPMN 中最基本的元素之一，它代表了业务流程中的一个具体任务或操作。可以自动/手动、
> - 事件：事件是 BPMN 中用于表示流程中的特定时刻或状态的元素。例如，开始事件、结束事件、中间事件等。
> - 网关：网关用于控制流程的执行路径，可以实现并行、条件、互斥等多种逻辑。
> - 泳道：泳道用于将流程分解为多个并行的部分，每个泳道代表一个独立的执行路径。

> 一个BPMN的例子：
> - 当事人填写请假单，启动流程后把请假单ID绑定到流程中； 
> - 部门经理对请假单进行审核； 
> - 然后人事经理进行复核并进行备案； 
> - 最后请假流程结束。

### Activiti支持的数据库

- Activiti的运行需要数据库的支撑，支持如下:
  - h2
  - MySQL
  - Oracle
  - Db2
  - postgres
  - mysql

- 在Navicat工具中创建`activiti`的数据库，用于后续的实验.



### Activiti使用步骤

> 2.4.1 整合Activiti
> Activiti是一个工作流引擎，业务系统使用Activiti来对系统的业务流程进行自动化管理，为了方便业务系统访问（操作）Activiti的接口或功能，通常将Activiti和业务系统的环境集成在一起。
> 2.4.2 业务流程建模
> 使用Activiti流程建模工具(Activity-designer)定义业务流程(.bpmn文件)。
> .bpmn文件就是业务流程定义文件，通过xml定义业务流程。
> 如果使用其他公司开发的工作引擎一般都提供了可视化的建模工具（Process Designer）用于生成流程定义文件，建模工具操作直观，一般都支持图形化拖拽方式、多窗口的用户界面、丰富的过程图形元素、过程元素拷贝、粘贴、删除等功能。
> 2.4.3 部署业务流程
> 向Activiti部署业务流程定义(.bpmn文件)。
> 使用Activiti提供的API向Activiti中部署.bpmn文件（一般情况下还需要一起部署业务流程的图片.png）。
> 2.4.4 启动流程实例
> 启动一个流程实例表示开始一次业务流程的运行，比如员工请假流程部署完成，如果张三要请假就可以启动一个流程实例，如果李四要请假也需要启动一个流程实例，两个流程的执行互不影响，就好比定义一个Java类，实例化两个Java对象一样，部署的流程就好比Java类，启动一个流程实例就好比new一个Java对象。
> 2.4.5 查询待办任务
> 因为现在系统的业务流程已经交给Activiti管理，通过Activiti就可以查询当前流程执行到哪里了，当前用户需要办理什么任务了，这些Activiti帮我们管理了，而不像传统方式中需要我们在SQL语句中的WHERE条件中指定当前查询的状态值是多少。
> 2.4.6 处理待办任务
> 用户查询待办任务后，就可以办理某个任务，如果这任务办理完成还需要其他用户办理，比如采购单创建后由部门经理审核，这个过程也是由Activiti帮我们完成了，不需要我们在代码中硬编码指定下一个任务办理人了
> 2.4.7 结束流程
> 当任务办理完成没有下一个任务/结点了，这个流程实例就完成了。

### Service总览

![img](http://so9vd9h8j.hd-bkt.clouddn.com/v2-c33ee3444141b1a2c3b45ef88db771df_1440w.jpg_repeat_1731401329594__239673.png)

- RepositoryService	Activiti的资源管理接口：管理和控制流程发布包和流程定义的操作
- RuntimeService	Activiti的流程运行管理接口：流程执行相关的信息
- TaskService	Activiti的任务管理接口：
- HistoryService	Activiti的历史管理接口
- ManagementService	Activiti的引擎管理接口，管理和维护功能

#### 数据表解读

Activiti 使用到的表都是 ACT_ 开头的，表名的第二部分表示用途。

##### ACT_GE_ （GE） 表示 general 全局通用数据及设置，各种情况都使用的数据。

> #### 全局通用数据（ACT_GE_*）
>
> | ACT_GE_BYTEARRAY | 二进制数据表，存储通用的流程定义和流程资源。         |
> | ---------------- | ---------------------------------------------------- |
> | ACT_GE_PROPERTY  | 系统相关属性，属性数据表存储整个流程引擎级别的数据。 |

##### ACT_HI_ （HI） 表示 history 历史数据表，包含着程执行的历史相关数据。

##### ACT_RE_ （RE） 表示 repository 存储，包含的是静态信息。

##### ACT_RU_ （RU） 表示 runtime 运行时，运行时的流程变量，用户任务，变量，职责（job）等运行时数据。Activiti 只存储实例执行期间的运行时数据，当流程实例结束时，将删除这些记录 （salvio修





## 流程定义 ProcessDefinition

```java
@Autowired
private RepositoryService repositoryService;
@Test
void queryProcessDe() {
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
```



## 流程实例 ProcessInstance



```java
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
```



## **流程符号**

### 事件 Event

![img](http://so9vd9h8j.hd-bkt.clouddn.com/v2-7abf29716c8a87df55a503f537b86230_1440w.jpg_repeat_1731401500483__988588.png)

### **网关 GateWay**

![img](http://so9vd9h8j.hd-bkt.clouddn.com/v2-f27e0ae9d25d13b7b0cc90fceaffd732_1440w.jpg_repeat_1731401505552__805264.png)

- 排他网关：只有一条路径会被选择
- 并行网关：所有路径都会被选择
- 包容网关：可以在网关上设置条件，**为每个输出设置计算**
- 事件网关：进入等待状态，**等待事件后转为活动状态**

### 流向 Flow

![img](http://so9vd9h8j.hd-bkt.clouddn.com/v2-9f863cc8aa78ed965a8a50349bcd3304_1440w.jpg_repeat_1731401793171__863727.png)

## 
