package cn.whai.activiti.activiti7demo.ys_workflow;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/29 15:09
 * @注释
 */
@SpringBootTest()
public class PendingTest {

    @Resource
    TaskService taskService;

    @Test
    public void pending(){
        // 查询 所有 待办任务数量
        TaskQuery query = taskService.createTaskQuery();
        List<Task> tasks = query.listPage(0, 100);
        System.out.println(tasks.size());


        // 我发起的


    }




}
