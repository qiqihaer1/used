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
		$("#div01").panel({
			title:"我是标题！",
			tools:"#tt",
			collapsible:true,
			maximizable:true,
			closable:true,
			minimizable:true
		});
		
		$('#div02').draggable();
	});
	
	function addtest(){
		alert('add2333');
	}
</script>
</head>
<body>
<div id="div02">
	<div id="div01" style="width:500px;height:200px;padding:10px;">   
	    The panel content    
	</div>  
</div>

<div id="tt">
	<a href="#" class="icon-add" onclick="javascript:alert('add')"></a>
	<a href="#" class="icon-edit" onclick="javascript:alert('edit')"></a>
</div>

</body>
</html>