<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 引入easy ui -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function($){
            $("#dg").datagrid({
                url:'/Servlet1',
                columns:[[
                    {field:'name',title:'序号',width:100},
                    {field:'value',title:'发票编号',width:100},
                    {field:'cargoname',title:'对象',width:100},
                    {field:'count',title:'数量',width:100},
                    {field:'unit',title:'单位',width:100},
                    {field:'price',title:'单价',width:100},
                    {field:'taxprice',title:'税后单价',width:100},
                    {field:'totalprice',title:'总价',width:100},
                    {field:'realname',title:'负责员工',width:100},
                    {field:'datestr',title:'提交日期',width:100}
                ]],
                fitColumns:true,
                singleSelect:true,
                pagination:true,
                pageList: [5,10,15],
                pageSize:5,
                title:"所有发票",
                toolbar:"#toolbar"
            });
        });

        var url;

    </script>
</head>
<body>

<table id="dg"></table>

</body>
</html>