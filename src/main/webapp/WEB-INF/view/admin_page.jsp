<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String nowDate = sdf.format(date);
    String name = (String) request.getSession().getAttribute("name");
%>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=basePath%>/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/static/layui/css/layui.css"/>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <div class="layui-header">
        <div class="layui-logo" style="font-size: 17px;"><strong>校园论坛系统管理后台</strong></div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a href="<%=basePath%>/admin/adminPage.do" style="text-decoration: none;">
                    <strong>首页</strong>
                </a>
            </li>
        </ul>


        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;" style="text-decoration: none;">
                    <img src="<%=basePath%>/static/img/avatar.jpg" class="layui-nav-img">
                    <strong>${sessionScope.name}</strong>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="" style="text-decoration: none;"><strong>基本资料</strong></a></dd>
                    <dd><a href="" style="text-decoration: none;"><strong>安全设置</strong></a></dd>
                    <hr/>
                    <dd>
                        <a href="<%=basePath%>/admin/outLogin.do" style="text-decoration: none;;">
                            <strong>退出登录</strong>
                        </a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="<%=basePath%>/admin/outLogin.do" style="text-decoration: none;;">
                    <strong>退出</strong>
                </a>
            </li>
        </ul>
    </div>


    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">

                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="javascript: toAllAdmin();" style="text-decoration: none;">
                        <strong>
                            <span class="fa fa-twitter fa-fw"></span>&nbsp;&nbsp;&nbsp;用户管理
                        </strong>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a style="text-decoration: none;"><strong><span class="fa fa-leaf fa-fw"></span>&nbsp;&nbsp;&nbsp;论坛版块</strong></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle01();"
                               style="text-decoration: none;"><strong>校园快讯</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle02();"
                               style="text-decoration: none;"><strong>新生专区</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle03();"
                               style="text-decoration: none;"><strong>生活信息</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle04();"
                               style="text-decoration: none;"><strong>知识海洋</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle05();"
                               style="text-decoration: none;"><strong>求职就业</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle06();"
                               style="text-decoration: none;"><strong>社团组织</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle07();"
                               style="text-decoration: none;"><strong>考研地带</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle08();"
                               style="text-decoration: none;"><strong>休闲娱乐</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle09();"
                               style="text-decoration: none;"><strong>游戏运动</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle10();"
                               style="text-decoration: none;"><strong>学术科学</strong></a></dd>
                        <dd><a href="javascript:;" onclick="javascript: toModuleArticle11();"
                               style="text-decoration: none;"><strong>电脑技术</strong></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="javascript: toGreatArticle();" style="text-decoration: none;">
                        <strong>
                            <span class="fa fa-send fa-fw"></span>&nbsp;&nbsp;&nbsp;精品帖子
                        </strong>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="javascript: toMyArticle();" style="text-decoration: none;">
                        <strong>
                            <span class="fa fa-user fa-fw"></span>&nbsp;&nbsp;&nbsp;我的帖子
                        </strong>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 以上都是共享内容 -->

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div id="content">
            <div style="font-size: 45px;color: #1D9D73;margin-top: 300px;" class="text-center">
                <strong>欢迎您进入校园论坛系统</strong></div>
        </div>
    </div>

</div>
</body>
<script src="<%=basePath%>/static/layui/layui.js"></script>
<script src="<%=basePath%>/static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
<script type="text/javascript">
    function toAllArticle() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?key=0\" width=\"100%\" height=\"100%\"></object>";
    }

    function toMyArticle() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?author=${sessionScope.name}&key=1\" width=\"100%\" height=\"100%\"></object>";
    }

    function toGreatArticle() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?status=1&key=3\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle01() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=校园快讯&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle02() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=新生专区&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle03() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=生活信息&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle04() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=知识海洋&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle05() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=求职就业&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle06() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=社团组织&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle07() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=考研地带&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle08() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=休闲娱乐&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle09() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=游戏运动&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle10() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=学术科学&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

    function toModuleArticle11() {
        document.getElementById("content").innerHTML = "<object type=\"text/html\" data=\"<%=basePath%>/userArticle/findByPage.do?module=电脑技术&key=2\" width=\"100%\" height=\"100%\"></object>";
    }

</script>
</html>