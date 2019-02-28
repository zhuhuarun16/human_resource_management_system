/**
 * 获得请求参数的jquery插件
 */
$.extend({
	
	getParam:function(key) {
		
		var url = location.href;//动态获得当前网页的url
				
		var index = url.indexOf('?');//返回url中问号的位置
		
		var str = url.substring(index + 1);//截取问号后面的内容
		
		var arr = str.split('&');//按照&分隔符分解字符串
		
		for (var i = 0;i < arr.length;i ++) {
			
			//根据=分解字符串
			var param = arr[i].split('=');
			
			//判断当前参数名称是否和传入的key相同
			if (param[0] == key)
				return param[1];
			
		}
		
	}	
	
});