### 1.常用日志依赖
```xml
        <dependencys>
         <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
            <version>1.7.30</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
            <scope>runtime</scope>
        </dependency>
        </dependencys>
```

### 2.AOP常用依赖项
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>
    </dependencies>
```
### 3.事务常用ｘｍｌ配置
```xml
 <!--6.开启基于注解的事物，使用xml配置事物，（必要，主要都是使用配置式）-->
    <aop:config>
        <!--切入点表达式、-->
        <aop:pointcut id="txPoint" expression="execution(* com.luonianxin.crud.service..*(..))"/>
        <!--配置事物增强-->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
    </aop:config>
    <!--7.配置数据库事务增加，事务如何切入;注意transaction-manager 属性值默认是transactionManager，如果名称不一致需要显示指定-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!--所有方法都是事务方法-->
            <tx:method name="*"/>
            <!--所有以get开头的方法都是只读的-->
            <tx:method name="get*" read-only="true"/>
        </tx:attributes>
    </tx:advice>
```
### 4.使用xml配置事务管理器
```xml
<beans>
  <!--使用xml配置事务管理器-->
    <bean id="transactionInterceptor"
    class="org.springframework.transaction.interceptor.TransactionInterceptor">
        <property name="transactionManager" ref="transactionManager"/>
        <!--配置事务属性，隔离性．-->
        <property name="transactionAttributes" >
            <props>
                <prop key="insert*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="save*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="add*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="select*">PROPAGATION_REQUIRED,readOnly</prop>
                <prop key="get*">PROPAGATION_REQUIRED,readOnly</prop>
                <prop key="find*">PROPAGATION_REQUIRED,readOnly</prop>
                <prop key="del*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="remove*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
            </props>
        </property>
    </bean>
        <!--　配置事务管理器拦截哪些类，哪些方法　-->
    <bean class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <property name="beanNames">
            <list>
                <value>*ServiceImpl</value>
            </list>
        </property>
        <property name="interceptorNames">
            <list>
                <value>transactionInterceptor</value>
            </list>
        </property>
    </bean>
</beans>
```