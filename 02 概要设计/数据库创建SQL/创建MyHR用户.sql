--创建myhr用户
CREATE USER myhr IDENTIFIED BY myhr;

--为用户授予登录、基本操作数据库以及建表的权限
GRANT CONNECT,RESOURCE,CREATE VIEW TO myhr;