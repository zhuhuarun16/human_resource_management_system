//后端访问地址前缀
var baseUrl = 'http://localhost:7777/hr_mysql_v3';

//全局ajax事件，处理ajax请求session超时
 $.ajaxSetup({
 	complete: function(xhr,textStatus) {
 		//通过ajax核心对象获得响应报头
 		var sessionStatus = xhr.getResponseHeader('sessionStatus');
 		
 		if (sessionStatus == 'timeout') {
 			alert('登录超时，请重新登录');
 			location.href = baseUrl + '/logout.jsp';
 		}
 	}
 });