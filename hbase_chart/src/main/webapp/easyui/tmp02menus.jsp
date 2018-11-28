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
<script type="text/javascript">
	$(function($){
		$('#dg').datagrid({    
		    url:'<%=path%>/allmenus.do',    
		    columns:[[    
		        {field:'mid',title:'ID',width:100},    
		        {field:'name',title:'名称',width:100},    
		        {field:'url',title:'地址',width:100,align:'right'}    
		    ]],
		    singleSelect:true,
		    width:700,
		    pagination:true,
		    pageList: [5,10,15],
		    pageSize:5,
		    title:"所有菜单"
		}); 
	})
</script>
</head>
<body>
<table id="dg"></table>  
</body>
</html>