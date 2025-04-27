# 校园二手市场系统---菜单和角色管理权限模块

## 项目结构说明

```
src/main/java/com/yuanlrc/campus_market/
├── config/           # 配置类目录
│   ├── admin/       # 后台管理相关配置
│   └── home/        # 前台页面相关配置
│
├── controller/      # 控制器目录
│   ├── admin/      # 后台管理控制器
│   └── home/       # 前台页面控制器
│
├── constant/        # 常量定义目录
│   └── RuntimeConstant.java  # 运行时常量配置
│
├── util/           # 工具类目录
│   ├── SessionUtil.java      # 会话工具类
│   └── MenuUtil.java        # 菜单工具类
│
├── service/        # 服务层目录
│   ├── admin/     # 后台管理服务
│   │   ├── OperaterLogService.java  # 操作日志服务
│   │   ├── RoleService.java         # 角色管理服务
│   │   └── MenuService.java         # 菜单管理服务
│   └── home/      # 前台业务服务
│
├── interceptor/    # 拦截器目录
│   ├── admin/     # 后台拦截器
│   │   ├── AuthorityInterceptor.java  # 权限拦截器
│   │   └── AdminLoginInterceptor.java  # 登录拦截器
│   └── home/      # 前台拦截器
│
├── entity/        # 实体类目录
│   ├── admin/     # 后台实体类
│   │   ├── OperaterLog.java  # 操作日志实体
│   │   ├── Role.java         # 角色实体
│   │   ├── Menu.java         # 菜单实体
│   │   └── User.java         # 用户实体
│   └── common/    # 公共实体类
│
├── dao/           # 数据访问层目录
│   ├── admin/     # 后台数据访问
│   └── home/      # 前台数据访问
│
├── annotion/      # 自定义注解目录
│   └── ValidateEntity.java  # 实体验证注解
│
├── bean/          # 数据传输对象目录
│   └── PageBean.java  # 分页对象
│
└── App.java       # 应用程序入口类
```

## 核心模块说明

### 1. 权限管理模块
- `entity/admin/Role.java`: 角色实体类，定义用户角色
- `entity/admin/Menu.java`: 菜单实体类，定义系统菜单
- `interceptor/admin/AuthorityInterceptor.java`: 权限拦截器，控制访问权限
- `service/admin/RoleService.java`: 角色管理服务，处理角色相关业务逻辑

### 2. 操作日志模块
- `entity/admin/OperaterLog.java`: 操作日志实体类
- `service/admin/OperaterLogService.java`: 操作日志服务，处理日志记录和查询
- `dao/admin/OperaterLogDao.java`: 操作日志数据访问层

### 3. 用户管理模块
- `entity/admin/User.java`: 用户实体类
- `service/admin/UserService.java`: 用户管理服务
- `controller/admin/UserController.java`: 用户管理控制器

### 4. 系统配置模块
- `config/admin/AdminWebConfig.java`: 后台Web配置
- `constant/RuntimeConstant.java`: 运行时常量配置
- `util/SessionUtil.java`: 会话工具类

## 主要功能说明

1. **权限管理功能**
   - 角色的创建与管理
   - 菜单权限的分配
   - 用户角色的分配
   - 权限的动态校验

2. **操作日志功能**
   - 用户操作的自动记录
   - 日志的分页查询
   - 操作日志的管理维护
   - 关键操作的追踪

3. **用户管理功能**
   - 用户信息的维护
   - 用户权限的管理
   - 用户状态的控制 