//package cn.whaifree.springdemo.config;
//
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//import org.springframework.transaction.support.TransactionCallback;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import javax.sql.DataSource;
//
///**
// * @version 1.0
// * @Author whai文海
// * @Date 2024/11/15 16:37
// * @注释
// */
//@Configuration
//@Slf4j
//public class TransactionConfig implements TransactionManagementConfigurer {
//
//
//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//    //配置事务管理器
//    @Bean
//    public TransactionManager transactionManager(DataSource dataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
//        // 打印参数
//        log.info("transactionManager: {}", transactionManager);
//        log.info("dataSource: {}", dataSource);
//        return transactionManager;
//    }
//
//    @Resource(name="txManager1")
//    private PlatformTransactionManager txManager1;
//
//    // 创建事务管理器1
//    @Bean(name = "txManager1")
//    public PlatformTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Override
//    public TransactionManager annotationDrivenTransactionManager() {
//        return txManager1;
//    }
//
//    @Transactional(value="txManager1")
//    public void addUser() {
//
//    }
//
//    public void addUser2() {
//        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        TransactionStatus transaction = txManager1.getTransaction(transactionDefinition);
//        try {
//            txManager1.commit(transaction);
//        } catch (Exception e) {
//            txManager1.rollback(transaction);
//        }
//    }
//
//    /**
//     * 编程事务
//     * TransactionTemplate
//     * PlatformTransactionManager
//     * DataSourceTransactionManager
//     */
//
//    public void adduser3() {
//        Object execute = transactionTemplate.execute(new TransactionCallback<Object>() {
//            @Override
//            public Object doInTransaction(TransactionStatus status) {
//                return null;
//            }
//        });
//    }
//
//
//}
//
//class Tran{
//
//}
