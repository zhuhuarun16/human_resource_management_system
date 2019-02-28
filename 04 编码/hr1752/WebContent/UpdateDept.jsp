<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--设置浏览器的布局视口，只在移动端浏览器起作用-->
<meta name="viewport"
	content="width=width-device,initial-scale=1,user-scalable=no" />
<title></title>
<!--引入bootstrap的核心css-->
<link rel="stylesheet" href="js/bootstrap/css/bootstrap.css" />

<!--判断如果是IE9以下，引入兼容h5标签的js文件，注意空格的位置-->
<!--[if lt IE 9]>
		<script src="js/bootstrap/js/html5shiv.min.js"></script>
		<script src="js/bootstrap/js/respond.min.js"></script>		
		<![end if] -->
</head>
<body>
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="#">人力资源管理系统</a></li>
			<li><a href="#">部门管理</a></li>
			<li>修改部门</li>
		</ol>

		<h2 class="page-header">修改部门</h2>

		<form id="form1" method="post" class="form-horizontal"
			action="UpdateDeptServlet">
			<div class="form-group">
				<label for="" class="col-md-2">部门名称</label>
				<div class="col-md-10">
					<input value="${dept.deptName}" id="deptName" name="deptName"
						type="text" placeholder="请输入部门名称" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="" class="col-md-2">部门地址</label>
				<div class="col-md-10">
					<input value="${dept.deptLoc }" id="deptLoc" name="deptLoc"
						type="text" placeholder="请输入部门地址" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-10 col-md-offset-2">
					<input value="${dept.deptId }" type="hidden" id="deptId"
						name="deptId"> <input type="submit" value="修改部门"
						class="btn btn-success" /> <input type="button" value="取消"
						class="btn btn-danger" onclick="history.back();" />
				</div>
			</div>
		</form>
	</div>

	<!--引入jquery-->
	<script src="js/jquery.js"></script>
	<!--引入bootstrap的js功能-->
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<!--引入layer插件-->
	<script src="js/layer/layer.js"></script>
	<script>
			
			$(function(){
				
				/*表单验证*/
				var deptName = $('#deptName');
				var deptLoc = $('#deptLoc');
				
				$('#form1').submit(function() {
					
					//验证部门名称
					if ( deptName.val() == '' || deptName.val().length > 10 ) {
						layer.msg('部门名称不能为空且最多10字符');
						return false;
					}
					
					//验证部门地址
					if ( deptLoc.val() == '' || deptLoc.val().length > 20 ) {
						layer.msg('部门地址不能为空且最多20字符');
						return false;
					}					
					
				});
				
			});
			
			
		</script>
</body>
</html>