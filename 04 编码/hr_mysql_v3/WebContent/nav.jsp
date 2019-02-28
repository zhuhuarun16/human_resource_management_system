<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!--设置浏览器的布局视口，只在移动端浏览器起作用-->
		<meta name="viewport" content="width=width-device,initial-scale=1,user-scalable=no" />
		<title></title>
		<!--引入bootstrap的核心css-->
		<link rel="stylesheet" href="js/bootstrap/css/bootstrap.css" />
		
		<!--判断如果是IE9以下，引入兼容h5标签的js文件，注意空格的位置-->		
		<!--[if lt IE 9]>
		<script src="js/bootstrap/js/html5shiv.min.js"></script>
		<script src="js/bootstrap/js/respond.min.js"></script>		
		<![end if] -->
		
		<style>
			* {
				margin: 0;
				padding: 0;
			}
			
			body {
				background: #333333;
			}		
			
			/*头像区*/
			.touxiang {
				width: 150px;
				height: 150px;
				margin: 20px auto;
				border-radius: 50%;
				border:5px solid #ffffff;
				overflow: hidden;
			}	
			
			.touxiang img {
				width: 100%;
				display: block;
			}
			
			/*左侧导航菜单*/
			.nav-left .list-group-item {
				background: transparent;
				border: none;
				border-radius: 0!important;
				text-align: center;
				line-height: 35px;
				font-size: 16px;
				color: #eeeeee;
				letter-spacing: 1px;
				border-bottom: 1px solid #444444;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">		
			
			<!--头像区-->
			<div class="row touxiang">
				<a href="UpdateEmpPic.html" target="content" title="点击修改用户头像">
					<img id="empPic" src="images/liudehua.jpg" class="img-responsive"/>
				</a>
			</div>
			
			<!--用户名-->
			<p class="text-center" style="color: #eeeeee;">
				欢迎你，<span id="empLoginName"></span>
			</p>
			
			<!--导航菜单列表-->
			<div class="row nav-left">
				<div class="list-group">	
					<!-- 根据角色权限动态显示导航菜单 -->
					
					<shiro:hasRole name="deptAdmin">								
					<a href="dept/query" target="content" class="list-group-item">部门管理</a>
					</shiro:hasRole>
					
					<shiro:hasRole name="jobAdmin">
					<a href="QueryJob.html" target="content" class="list-group-item">职务管理</a>
					</shiro:hasRole>
					
					<shiro:hasRole name="empAdmin">
					<a href="QueryEmp.html" target="content" class="list-group-item">员工管理</a>
					</shiro:hasRole>
					
					<shiro:hasRole name="anaAdmin">
					<a href="Analysis.html" target="content" class="list-group-item">数据分析</a>		
					</shiro:hasRole>
					
					<shiro:hasPermission name="emp:query">			
					<a href="SearchEmp.html" target="content" class="list-group-item">数据检索</a>
					</shiro:hasPermission>
					
					<shiro:hasRole name="picAdmin">
					<a href="QueryPic.html" target="content" class="list-group-item">公司图库</a>
					</shiro:hasRole>
					
					<a href="UpdatePwd.html" target="content" class="list-group-item">修改密码</a>
					<a href="logout" target="_top" class="list-group-item">退出登录</a>
				</div>
			</div>			
		</div>
		
		<!--引入jquery-->
		<script src="js/jquery.js"></script>
		<!--引入全局公共js-->
		<script src="js/global.js"></script>
		<!--引入bootstrap的js功能-->
		<script src="js/bootstrap/js/bootstrap.js"></script>
		<!--引入layer插件-->
		<script src="js/layer/layer.js"></script>
		
		<script>
			$(function() {
				
				//读取本地存储的用户名
				$('#empLoginName').html( localStorage.getItem('empLoginName') );
				
				//读取本地存储的头像数据显示
				if (localStorage.getItem('empPic')) {
					$('#empPic').attr('src',localStorage.getItem('empPic'));
				}				
				
			});			
		</script>
		
	</body>
</html>