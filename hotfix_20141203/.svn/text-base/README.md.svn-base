DataBank 数据库查询及报表系统
====

# 1.历史版本

## 1.0.0 20130401

baitao.ji@yeepay.com

# 2.部署测试

## 2.1 配置文件

目录：databank-core/main/resources/com.yeepay.g3.app.databank.dbconf

    CONFIGDS.properties 统一配置数据库配置文件
    DATABANKDS.properties 数据银行数据库配置文件

## 2.2 初始化脚本

目录：databank-core/test/resources/db

    dbschema.sql 数据表结构
    dbinit.sql 测试用初始化数据

## 2.3 权限配置

文件：databank-boss/test/resources/*

# 3.开发维护

## 3.1 MyBatis 配置

只需配置扫描的类包，无需在 mapper/Configuration.xml 文件中一一指定 mapper、typeAliase、typeHandler。应用启动时自动扫描指定包下的类并做初始化操作，不存在延迟加载的问题。

文件：databank-core/main/resources/databank-spring/databank-core.xml

    <!-- MyBatis配置 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:/mapper/Configuration.xml"/>
        <property name="mapperLocations" value="classpath:/mapper/*Mapper.xml"/>
    </bean>
    
    <!-- 指定要扫描的dao以便自动注入 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.yeepay.g3.core.databank.mapper"/>
    </bean>
    
注意 1：

* 直接使用 org.mybatis.spring.SqlSessionFactoryBean 就可以了，MyBatis 3.2.2 不存在延迟加载的概念。
* 所有 DAO 写在 com.yeepay.core.**.com.yeepay.g3.app.databank.dao.*Mapper，只需要接口声明不需要实现。
* 所有 SQL 写在 /mapper/*Mapper.xml 中，并指定完整类名 namespace="com.yeepay.core.**.com.yeepay.g3.app.databank.dao.*Mapper"

注意 2：

* 需要指定自定义配置时，需要提供 configLocation 参数。
* mapperLocations 指向 Mapper 文件保存的路径 /mapper/*Mapper.xml
* typeAliasesPackage 指向 Entity 类的路径 com.yeepay.core.**.entity.*Entity（也可以指向其他在 Mapper.xml 需要使用到的类）
* typeHandlersPackage 指向 TypeHander 类的路径 com.yeepay.core.**.typehander.*TypeHander

## 3.2 Spring 注解使用

### 3.2.1 配置文件

文件：databank-core/main/resources/databank-spring/databank-application.xml

    <!-- 使用annotation 自动注册bean, 并保证@Required、@Autowired的属性被注入 -->
    <context:component-scan base-package="com.yeepay.g3.core.databank">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

注意：

* base-package 指定待扫描的包路径
* context:exclude-filter 用于指定需要排除的类，通常用于 Spring MVC 的整合

#### 3.2.2 Action 层的使用

    @Controller
    public class UserAction extends ActionSupport {
    
      private static final long serialVersionUID = 6365589345445082271L;
    
      @Autowired
      private UserManager userManager;
    
      @Autowired
      private RoleManager roleManager;

    }

注意：
* @Controller 注释类名
* Action 一般不写单元测试，故直接在属性上 @Autowired

#### 3.2.3 Service 层的使用

    @Component
    @Transactional(readOnly = true)
    public class UserManagerImpl implements UserManager {
    
      private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    
      private UserMapper userMapper;
    
      private RoleMapper roleMapper;

      @Override
      public List<User> getAll() {
        return userMapper.getAll();
      }
      
      @Deprecated
      @Transactional(readOnly = false)
      public User create(User user) {
        return user;
      }
  
      @Autowired
      public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
      }
    
      @Autowired
      public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
      }
    }

注意：
* @Component 注释类名
* @Transactional(readOnly = true) 声明只读事务，在非只读事务上注释 @Transactional(readOnly = false)
* Service 需要单元测试，故在 setter 方法上 @Autowired

单元测试方法：
（待整理）

#### 3.2.4 DAO 层的使用

    @Repository
    public interface UserMapper extends GenericDao<User> {
    
    /**
     * 通过用户名获取用户
     *
     * @return
     */
    User getByLoginname(@Param("loginname") String loginname);
    
注意：
* @Repository 注释类名
* 只声明接口而不用提供实现类，方法名与 Mapper 文件中的 id 应该保持一致

单元测试方法：

    @DirtiesContext
    @ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
    public class UserMapperTest extends SpringTransactionalTests {
    
      @Autowired
      private UserMapper mapper;
    
      @Test
      public void testCount() throws Exception {
        assertTrue(mapper.count() > 0);
      }
    }
    
其中用到一个类：

    @ActiveProfiles("test")
    public class SpringTransactionalTests extends AbstractTransactionalJUnit4SpringContextTests {
    
      protected DataSource dataSource;
    
      @Override
      @Autowired
      public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
      }
    
    }

# 4.陷阱

## 4.1 jquery.validate 插件

需要修改一下部分，否则验证失败（比如name="user.loginName"）

    remote: function(value, element, param) {
        if (this.optional(element))
            return "dependency-mismatch";

        ...

        param = typeof  param == "string" && {url:param} || param;

        if (previous.old !== value) {
            previous.old = value;
            var validator = this;
            this.startRequest(element);
            var data = {};
            data[element.name] = value;
            $.ajax($.extend(true, {
                // url: param,
                url: param.url,
                mode: "abort",
                port: "validate" + element.name,
                dataType: "json",
                // data: data,
                data: param.data || data,
                success: function(response) {
                    ...