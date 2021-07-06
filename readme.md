

<h1 style = "text-align:center;">《学生信息管理系统》 </h1>

<p style = "text-align:right;">19级计算机师范卢泳年</P>


# 面向对象课程设计报告

## 开发背景

​		XX高校的学生信息管理比较混乱，近年来，随着生源不断扩大，有关学生的信息成倍增加，面对如此庞大的信息量，校领导决定使用一套合理、有效、规范、实用的学生信息管理系统，对学生信息进行统一、集中的管理。受该高校委托，开发一个学生信息管理系统，开发宗旨是实现学生信息管理的系统化、规范化，达成便捷管理学生信息的目标。

## 需求分析

​		学生信息管理系统是学生管理工作中不可缺少的部分，十分重要，但长期以来，混乱的学生信息管理让对学生的各方面的统计工作十分困难，效率低下，而一个学生信息管理系统应提供快速的学生信息检索，便捷的录入，以及学生信息的可视化，为管理者提供快捷的信息处理手段，解脱繁琐的工作。通过对一些典型人员信息管理系统的考察，从老师工作的角度出发，开发的学生信息管理系统应具有以下特点：

- 具有良好的系统性能，友好的用户界面。
- 较高处理效率，便于维护和使用。
- 采用成熟技术开发，使系统具有较长的生命周期
- 系统尽可能简化学生信息管理工作，提高工作效率
- 简化数据查询、统计难度

## 数据库设计与实现

数据库使用MySQL进行支持，数据库中学生表stu设计如下


学生对象包括学号，姓名，性别，语文，数学，英语等属性，主键为==学号==


##  前端代码

#### 默认登录页

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<!--引入thymeleaf名称空间xmlns:th="http://www.thymeleaf.org"-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>学生信息管理系统</title>
    <!-- Bootstrap core CSS -->
    <link href="asserts/css/bootstrap.min.css" th:href="@{/webjars/bootstrap/5.0.0-beta3/css/bootstrap.css}"
          rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="asserts/css/signin.css" th:href="@{/asserts/css/signin.css}" rel="stylesheet">
    <link th:href="j25.ico" rel="icon">
</head>
<body class="text-center">
<form class="form-signin" action="dashboard.html" th:action="@{/user/login}" method="post">
    <img class="mb-4" th:src="@{/asserts/img/bootstrap-solid.svg}" src="asserts/img/bootstrap-solid.svg" alt=""
         width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal" th:text="#{index.tip}">Please sign in</h1>
    <p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
    <!--			<label class="sr-only">Username</label>-->
    <input type="text" class="form-control" placeholder="Username" name="username" th:placeholder="#{index.username}"
           required="" autofocus="">
    <!--			<label class="sr-only">Password</label>-->
    <input type="password" class="form-control" name="password" placeholder="Password" required=""
           th:placeholder="#{index.password}">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> [[#{index.remember}]]
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{index.login}">sign in</button>
    <br>
    <a class="btn btn-sm" th:href="@{/(l='zh_CN')}">中文</a>
    <a class="btn btn-sm" th:href="@{/(l='en_US')}">English</a>
</form>
</body>
</html>
```

####  公共导航栏

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--topbar引入片段-->
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" th:fragment="topbar">
    <!--			抽取公共片段th:fragment="topbar"，topbar为片段名-->
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">[[${session.loginUser}]]</a>
    <!--    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">-->
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/">Sign out</a>
        </li>
    </ul>
</nav>
<!--sidebar引入片段-->
<nav class="col-md-2 d-none d-md-block bg-light sidebar" id="sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" th:class="${activeUri == 'emps.html' ? 'nav-link active' : 'nav-link'}"
                   href="#" th:href="@{/emps}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-users">
                        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                        <circle cx="9" cy="7" r="4"></circle>
                        <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                        <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                    </svg>
                    学生管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" th:class="${activeUri == 'rank.html' ? 'nav-link active' : 'nav-link'}"
                   th:href="@{/rank}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-bar-chart-2">
                        <line x1="18" y1="20" x2="18" y2="10"></line>
                        <line x1="12" y1="20" x2="12" y2="4"></line>
                        <line x1="6" y1="20" x2="6" y2="14"></line>
                    </svg>
                    成绩排名
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" th:class="${activeUri == 'search.html' ? 'nav-link active' : 'nav-link'}"
                   th:href="@{/search}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-layers">
                        <polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
                        <polyline points="2 17 12 22 22 17"></polyline>
                        <polyline points="2 12 12 17 22 12"></polyline>
                    </svg>
                    查询信息
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" th:class="${activeUri == 'chart.html'? 'nav-link active' : 'nav-link'}"
                   href="#" th:href="@{/chart}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-home">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                        <polyline points="9 22 9 12 15 12 15 22"></polyline>
                    </svg>
                    班级成绩 <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
```

#### 	学生管理页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>学生管理</title>
    <link href="asserts/css/bootstrap.min.css" th:href="@{/asserts/css/bootstrap.min.css}" rel="stylesheet">
    <link href="asserts/css/dashboard.css" th:href="@{/asserts/css/dashboard.css}" rel="stylesheet">
    <link th:href="j25.ico" rel="icon">
</head>
<body>
<!--		引入公共片段th:insert="dashboard::topbar",模板名::片段名-->
<div th:replace="commons/bar::topbar"></div>

<div class="container-fluid">
    <div class="row">
        <!-- 引入sidebar-->
        <div th:replace="commons/bar::#sidebar(activeUri='emps.html')"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <h2><a class="btn btn-sm btn-success" href="emp" th:href="@{/emp}">添加学生信息</a></h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>语文</th>
                        <th>数学</th>
                        <th>英语</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="emp:${emps}">
                        <td th:text="${emp.id}"></td>
                        <td>[[${emp.name}]]</td>
                        <td th:text="${emp.gender}"></td>
                        <td th:text="${emp.chinese}"></td>
                        <td th:text="${emp.math}"></td>
                        <td th:text="${emp.english}"></td>
                        <td>
                            <!--<a class="btn btn-sm btn-primary" th:href="@{/emp/}+${emp.id}">编辑</a>-->
                            <!--<button type="submit" th:attr="del_uri=@{/emp/}+${emp.id}" class="btn btn-sm btn-danger deleteBtn">删除</button>-->
                            <a class="btn btn-sm btn-primary" th:href="@{/emp/}+${emp.id}">编辑</a>
                            <button th:attr="del_uri=@{/emp/}+${emp.id}" class="btn btn-sm btn-danger deleteBtn">删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </main>
        <form id="deleteEmpForm" method="post">
            <input type="hidden" name="_delete" value="delete"/>
        </form>
    </div>
</div>
<script type="text/javascript" src="asserts/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="asserts/js/popper.min.js"></script>
<script type="text/javascript" src="asserts/js/bootstrap.min.js"></script>
<script>
    $(".deleteBtn").click(function () {
        //删除当前信息
        $("#deleteEmpForm").attr("action", $(this).attr("del_uri")).submit();
        return false;
    })
</script>
</body>
</html>
```



#### 成绩排名页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>学生成绩排名</title>
    <link href="asserts/css/bootstrap.min.css" th:href="@{/asserts/css/bootstrap.min.css}" rel="stylesheet">
    <link href="asserts/css/dashboard.css" th:href="@{/asserts/css/dashboard.css}" rel="stylesheet">
    <link th:href="j25.ico" rel="icon">
</head>
<body>
<!--		引入公共片段th:insert="dashboard::topbar",模板名::片段名-->
<div th:replace="commons/bar::topbar"></div>
<div class="container-fluid">
    <div class="row">
        <!-- 引入sidebar-->
        <div th:replace="commons/bar::#sidebar(activeUri='rank.html')"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>语文</th>
                        <th>数学</th>
                        <th>英语</th>
                        <th>总分</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="emp:${emps}">
                        <td th:text="${map.get(emp)}"></td>
                        <td th:text="${emp.id}"></td>
                        <td>[[${emp.name}]]</td>
                        <td th:text="${emp.gender}"></td>
                        <td th:text="${emp.chinese}"></td>
                        <td th:text="${emp.math}"></td>
                        <td th:text="${emp.english}"></td>
                        <td th:text="${emp.chinese+emp.math+emp.english}"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
<script type="text/javascript" src="asserts/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="asserts/js/popper.min.js"></script>
<script type="text/javascript" src="asserts/js/bootstrap.min.js"></script>
</body>
</html>
```





#### 查询学生信息页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>查询学生信息</title>
    <link href="asserts/css/bootstrap.min.css" th:href="@{/asserts/css/bootstrap.min.css}" rel="stylesheet">
    <link href="asserts/css/dashboard.css" th:href="@{/asserts/css/dashboard.css}" rel="stylesheet">
    <link th:href="j25.ico" rel="icon">
</head>
<body>
<!--		引入公共片段th:insert="dashboard::topbar",模板名::片段名-->
<div th:replace="commons/bar::topbar"></div>
<div class="container-fluid">
    <div class="row">
        <!-- 引入sidebar-->
        <div th:replace="commons/bar::#sidebar(activeUri='search.html')"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <table>
                <form th:action="@{/search}" method="post">
                    <tr>
                        <td>
                            <input type="number" name="num" placeholder="请输入学号">
                            <input type="submit" class="btn btn-sm btn-success" value="查询">
                        </td>
                </form>
                <form th:action="@{/searchName}" method="post">
                    <td>
                        <input type="text" name="name" placeholder="请输入姓名">
                        <input type="submit" class="btn btn-sm btn-success" value="查询">
                    </td>
                    </tr>
                </form>
            </table>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>语文</th>
                        <th>数学</th>
                        <th>英语</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="emp:${emps}">
                        <td th:text="${emp.id}"></td>
                        <td>[[${emp.name}]]</td>
                        <td th:text="${emp.gender}"></td>
                        <td th:text="${emp.chinese}"></td>
                        <td th:text="${emp.math}"></td>
                        <td th:text="${emp.english}"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
<script type="text/javascript" src="asserts/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="asserts/js/popper.min.js"></script>
<script type="text/javascript" src="asserts/js/bootstrap.min.js"></script>
</body>
</html>
```



#### 班级成绩页面（将数据转为图表显示）

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>学生管理</title>
    <link href="asserts/css/bootstrap.min.css" th:href="@{/asserts/css/bootstrap.min.css}" rel="stylesheet">
    <link href="asserts/css/dashboard.css" th:href="@{/asserts/css/dashboard.css}" rel="stylesheet">
    <link th:href="j25.ico" rel="icon">
    <style type="text/css">

        @-webkit-keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        @keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }
    </style>
</head>

<body>
<!--		引入公共片段th:insert="dashboard::topbar",模板名::片段名-->
<div th:replace="commons/bar::topbar"></div>

<div class="container-fluid">
    <div class="row">
        <!-- 引入sidebar-->
        <div th:replace="commons/bar::#sidebar(activeUri='chart.html')"></div>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <canvas id="myChart"></canvas>
        </main>
    </div>
</div>

<script type="text/javascript" src="asserts/js/jquery-3.2.1.slim.min.js"></script>
<script type="text/javascript" src="asserts/js/popper.min.js"></script>
<script type="text/javascript" src="asserts/js/bootstrap.min.js"></script>
<script type="text/javascript" src="asserts/js/feather.min.js"></script>
<script>
    feather.replace()
</script>

<!-- Graphs -->
<script type="text/javascript" src="asserts/js/Chart.min.js"></script>
<script>
    var ctx = document.getElementById("myChart");
    var data = {
        labels: ["语文", "数学", "英语"],
        datasets: [
            {
                label: "平均分",
                backgroundColor: "rgba(220,220,220,1)",
                data: ["[[${da[0]}]]", "[[${da[1]}]]", "[[${da[2]}]]"]
            },
            {
                label: "及格率",
                backgroundColor: "rgba(151,187,205,1)",
                data: ["[[${da[3]}]]", "[[${da[4]}]]", "[[${da[5]}]]"]
            }

        ]

    };
    var myChart = new Chart(ctx, {
        type: 'bar',
        tittle: '成绩条形图',
        data: data,
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
</body>
</html>
```

## 后端代码

#### 登录

```java
package com.example.springbootdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
  @PostMapping(value = "/user/login")
  public String login(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      Map<String, Object> map,
      HttpSession session) {
    if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
      // 登录成功
      // return "dashboard";
      // 为防止表单重复提交，将上面改为页面重定向
      session.setAttribute("loginUser", username);
      // 保存已登录的用户信息
      return "redirect:/emps";
      // 重定向
    } else {
      // 登录失败
      map.put("msg", "用户名密码错误");
      return "index";
    }
  }
}

```

#### 登录检查（前面设置了页面重定向）

```java
package com.example.springbootdata.component;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 页面重定向以后要设置拦截器进行登录检查
 */
public class LoginHandlerIntercept implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登录，返回登录页面
            request.setAttribute("msg","没有权限请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            //已登录，请求通过
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

```

#### 国际化

```properties
#index.properties(默认页面)index.tip=Please sign inindex.password=passwordindex.username=usernameindex.remember=Remember Meindex.login=sign in#index_en_US.properties(英语)index.tip=Please sign inindex.password=passwordindex.username=usernameindex.remember=Remember Meindex.login=sign in#index_zh_CN.properties(中文)index.tip=请登录index.password=密码index.username=用户名index.remember=记住我index.login=登录
```

#### 区域解析器

```java
package com.example.springbootdata.component;import org.springframework.web.servlet.LocaleResolver;import org.thymeleaf.util.StringUtils;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.util.Locale;/** 通过按钮在链接上携带区域信息 实现区域信息解析器接口 */public class MyLocaleResolver implements LocaleResolver {  @Override  public Locale resolveLocale(HttpServletRequest httpServletRequest) {    String l = httpServletRequest.getParameter("l");    Locale locale = Locale.getDefault();    if (!StringUtils.isEmpty(l)) {      String string[] = l.split("_");      locale = new Locale(string[0], string[1]);    }    return locale;  }  @Override  public void setLocale(      HttpServletRequest httpServletRequest,      HttpServletResponse httpServletResponse,      Locale locale) {}}
```

#### 扩展SpringMVC

```java
package com.example.springbootdata.config;import com.example.springbootdata.component.LoginHandlerIntercept;import com.example.springbootdata.component.MyLocaleResolver;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.web.servlet.LocaleResolver;import org.springframework.web.servlet.config.annotation.InterceptorRegistry;import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;/** webmvcconfigurer 扩展springMvc的功能 */@Configuration // 标注配置类public class MyMvcConfig implements WebMvcConfigurer {  @Override  public void addViewControllers(ViewControllerRegistry registry) {    // 设置欢迎页    registry.addViewController("/").setViewName("index");  }  // 注册拦截器  @Override  public void addInterceptors(InterceptorRegistry registry) {    // 拦截所有请求再排除允许通过的请求    registry        .addInterceptor(new LoginHandlerIntercept())        .addPathPatterns("/**")        .excludePathPatterns("/", "/user/login", "/asserts/**");  }  @Bean  public LocaleResolver localeResolver() {    return new MyLocaleResolver();  }}
```

#### 编写Student类

```java
package com.example.springbootdata;public class Student {  Integer id;  String name;  String gender;  Integer chinese;  Integer math;  Integer english;  public String getGender() {    return gender;  }  public void setGender(String gender) {    this.gender = gender;  }  @Override  public String toString() {    return "Student{"        + "id="        + id        + ", name='"        + name        + '\''        + ", gender='"        + gender        + '\''        + ", chinese="        + chinese        + ", math="        + math        + ", english="        + english        + '}';  }  public Integer getChinese() {    return chinese;  }  public void setChinese(Integer chinese) {    this.chinese = chinese;  }  public Integer getMath() {    return math;  }  public void setMath(Integer math) {    this.math = math;  }  public Integer getEnglish() {    return english;  }  public void setEnglish(Integer english) {    this.english = english;  }  public Integer getId() {    return id;  }  public void setId(Integer id) {    this.id = id;  }  public String getName() {    return name;  }  public void setName(String name) {    this.name = name;  }  public Student(Integer id, String name) {    this.id = id;    this.name = name;  }}
```

#### 编写配置文件application.yml连接数据库

```yaml
spring:  datasource:    username: root    password: root    url: jdbc:mysql://127.0.0.1:3306/jdbc    driver-class-name: com.mysql.cj.jdbc.Driver    type: com.alibaba.druid.pool.DruidDataSource    druid:      initial-size: 5      min-idle: 5      max-active: 20      max-wait: 60000      minEvictableIdleTimeMillis: 300000      validationQuery: SELECT 1 FROM DUAL      testWhileIdle: true      testOnBorrow: false      testOnReturn: false      poolPreparedStatements: true      filters: stat,wall,log4j2      maxPoolPreparedStatementPerConnectionSize: 20      useGlobalDataSourceStat: true      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500#    #   数据源其他配置#    initialSize: 5#    minIdle: 5#    maxActive: 20#    maxWait: 60000#    timeBetweenEvictionRunsMillis: 60000#    minEvictableIdleTimeMillis: 300000#    validationQuery: SELECT 1 FROM DUAL#    testWhileIdle: true#    testOnBorrow: false#    testOnReturn: false#    poolPreparedStatements: true#    #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙#    filters: stat,wall,log4j#    maxPoolPreparedStatementPerConnectionSize: 20#    useGlobalDataSourceStat: true#    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

#### 编写StuMapper接口，完成数据库相应操作

```java
package com.example.springbootdata.mapper;import com.example.springbootdata.Student;import org.apache.ibatis.annotations.*;import java.util.List;//指定这是一个操作数据库的mapper（可直接在application下指定mapper包，不用一个一个@mapper）//@Mapperpublic interface StuMapper {    @Select("select * from stu order by chinese+math+english desc")    public List<Student> rank();    @Select("select * from stu where id=#{id}")    public Student getStudent(Integer id);    @Select("select * from stu where name=#{name}")    public List<Student> getStudentByName(String name);    @Select("select * from stu")    public List<Student> allStudent();    @Options(useGeneratedKeys = true,keyProperty = "id")    @Insert("insert into stu(name,gender,chinese,math,english) values(#{name},#{gender},#{chinese},#{math},#{english})")    public int insertStu(Student student);    @Delete("delete from stu where id=#{id}")    public int deleteStu(Integer id);    @Update("update stu set name=#{name},gender=#{gender},chinese=#{chinese},math=#{math},english=#{english} where id=#{id}")    public void update(Student student);}
```

#### 启动springboot时扫描mapper

```java
package com.example.springbootdata;import org.mybatis.spring.annotation.MapperScan;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;@MapperScan("com.example.springbootdata.mapper")@SpringBootApplicationpublic class SpringbootDataApplication {  public static void main(String[] args) {    SpringApplication.run(SpringbootDataApplication.class, args);  }}
```



#### 实现控制器，完成CRUD功能并处理其他请求

```java
package com.example.springbootdata.controller;import com.example.springbootdata.Student;import com.example.springbootdata.mapper.StuMapper;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import java.util.*;@Controllerpublic class StudentController {  @Autowired StuMapper stuMapper;  // 成绩排名  @GetMapping("/rank")  public String rank(Model model) {    Collection<Student> students = stuMapper.rank();    model.addAttribute("emps", students);    ArrayList<Student> list = (ArrayList<Student>) students;    Map<Student, Integer> map = new HashMap<>();    for (int i = 1; i <= list.size(); ++i) {      map.put(list.get(i - 1), i);    }    model.addAttribute("map", map);    return "emp/rank";  }  // 平均分和及格率  @GetMapping("/chart")  public String chart(Model model) {    Collection<Student> students = stuMapper.allStudent();    int a, b, c;    a = b = c = 0;    int cntA, cntB, cntC;    cntA = cntB = cntC = 0;    for (Student tmp : students) {      a += tmp.getChinese();      b += tmp.getMath();      c += tmp.getEnglish();      if (tmp.getChinese() >= 60) {        cntA++;      }      if (tmp.getMath() >= 60) {        cntB++;      }      if (tmp.getEnglish() >= 60) {        cntC++;      }    }    float[] data = new float[6];    data[0] = a / students.size();    data[1] = b / students.size();    data[2] = c / students.size();    data[3] = (float) cntA / students.size() * 100;    data[4] = (float) cntB / students.size() * 100;    data[5] = (float) cntC / students.size() * 100;    model.addAttribute("da", data);    return "emp/chart";  }  @GetMapping("/search")  public String s() {    return "emp/search";  }  // 通过学号查询信息请求  @PostMapping("/search")  public String search(String num, Model model) {    Integer i = Integer.parseInt(num);    Student student = stuMapper.getStudent(i);    Collection<Student> students = new ArrayList<>();    students.add(student);    model.addAttribute("emps", students);    if (student == null) {      return "emp/error";    }    return "emp/search";  }  // 通过姓名查询信息请求  @PostMapping("/searchName")  public String searchName(String name, Model model) {    Collection<Student> students = stuMapper.getStudentByName(name);    if (students == null) {      return "emp/error";    }    model.addAttribute("emps", students);    return "emp/search";  }  // 查询所有学生页面  @GetMapping("/emps")  public String lsit(Model model) {    Collection<Student> employees = stuMapper.allStudent();    // 放在请求域中    model.addAttribute("emps", employees);    // thymeleaf自动配置搜索classpath/templates/***.html路径    return "emp/list";  }  // 来到员工添加页面  @GetMapping("/emp")  public String toAdd(Model model) {    return "emp/add";  }  // 添加员工  // springMVC自动将请求参数与入参对象的属性一一绑定；但要求请求参数的名字和JavaBean入参的对象里面的属性名一致（employee的lastname，gender，email。。。）  @PostMapping("/emp")  public String add(Student student) {    System.out.println("保存的信息：" + student);    stuMapper.insertStu(student);    // 不能直接/emps，thymeleaf会在/templates/**下找，所以要用redirect（重定向）或forward（转发）    return "redirect:/emps";  }  // 来到修改页面  @GetMapping("/emp/{id}")  public String toEdit(@PathVariable Integer id, Model model) {    Student employee = stuMapper.getStudent(id);    model.addAttribute("emp", employee);    // 回到修改页面(修改添加二合一)    return "emp/add";  }  // 修改数据  @PostMapping("/update")  public String update(Student student) {    System.out.println("修改的数据：" + student);    stuMapper.update(student);    return "redirect:/emps";  }  // 删除数据  @PostMapping("/emp/{id}")  public String delete(@PathVariable("id") Integer id) {    stuMapper.deleteStu(id);    return "redirect:/emps";  }}
```

#### 在pom文件中导入相应依赖

```xml
<?xml version="1.0" encoding="UTF-8"?><project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">    <modelVersion>4.0.0</modelVersion>    <parent>        <groupId>org.springframework.boot</groupId>        <artifactId>spring-boot-starter-parent</artifactId>        <version>2.4.4</version>        <relativePath/> <!-- lookup parent from repository -->    </parent>    <groupId>com.example</groupId>    <artifactId>springboot-data</artifactId>    <version>0.0.1-SNAPSHOT</version>    <name>springboot-data</name>    <description>Demo project for Spring Boot</description>    <properties>        <java.version>11</java.version>    </properties>    <dependencies>        <dependency>            <groupId>org.springframework.boot</groupId>            <artifactId>spring-boot-starter-web</artifactId>        </dependency>        <dependency>            <groupId>org.mybatis.spring.boot</groupId>            <artifactId>mybatis-spring-boot-starter</artifactId>            <version>2.1.4</version>        </dependency>        <dependency>            <groupId>mysql</groupId>            <artifactId>mysql-connector-java</artifactId>            <scope>runtime</scope>        </dependency>        <dependency>            <groupId>org.springframework.boot</groupId>            <artifactId>spring-boot-starter-test</artifactId>            <scope>test</scope>        </dependency>        <dependency>            <groupId>com.alibaba</groupId>            <artifactId>druid-spring-boot-starter</artifactId>            <version>1.1.10</version>        </dependency>        <dependency>            <groupId>org.springframework.boot</groupId>            <artifactId>spring-boot-starter-jdbc</artifactId>        </dependency>        <dependency>            <groupId>org.webjars</groupId>            <artifactId>jquery</artifactId>            <version>3.6.0</version>        </dependency>        <dependency>            <groupId>org.springframework.boot</groupId>            <artifactId>spring-boot-starter-thymeleaf</artifactId>        </dependency>        <dependency>            <groupId>org.webjars</groupId>            <artifactId>bootstrap</artifactId>            <version>5.0.0-beta3</version>        </dependency>    </dependencies>    <build>        <plugins>            <plugin>                <groupId>org.springframework.boot</groupId>                <artifactId>spring-boot-maven-plugin</artifactId>            </plugin>        </plugins>    </build></project>
```

#### 导入阿里巴巴Druid数据库连接池

```java
package com.example.springbootdata.config;import com.alibaba.druid.pool.DruidDataSource;import com.alibaba.druid.support.http.StatViewServlet;import com.alibaba.druid.support.http.WebStatFilter;import org.springframework.boot.context.properties.ConfigurationProperties;import org.springframework.boot.web.servlet.FilterRegistrationBean;import org.springframework.boot.web.servlet.ServletRegistrationBean;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import javax.sql.DataSource;import java.util.Arrays;import java.util.HashMap;import java.util.Map;// 导入Druid数据源@Configurationpublic class DruidConfig {  @Bean  @ConfigurationProperties(prefix = "spring.datasource")  public DataSource druid_data() {    return new DruidDataSource();  }  // 配置druid的监控  // 1.配置一个管理后台的service  @Bean  public ServletRegistrationBean statViewServlet() {    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");    Map<String, String> initParams = new HashMap<>();    initParams.put("loginUsername", "root");    initParams.put("loginPassword", "root");    initParams.put("allow", ""); // 默认允许所有访问    // deny拒绝    bean.setInitParameters(initParams);    return bean;  }  // 2.配置一个web监控的filter  @Bean  public FilterRegistrationBean webStatFilter() {    FilterRegistrationBean bean = new FilterRegistrationBean();    bean.setFilter(new WebStatFilter());    Map<String, String> initParams = new HashMap<>();    initParams.put("exclusions", "*.js,*.css,/druid/*");    bean.setInitParameters(initParams);    bean.setUrlPatterns(Arrays.asList("/*"));    return bean;  }}
```
