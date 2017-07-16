# Spider
一些爬虫练习  
## 简介  
&nbsp;&nbsp;Java+MySQL+Jersey实现的爬虫系统。  
## API
### User
注册：POST http://localhost:8080/RESTSpider/users/new_user  
登录：POST http://localhost:8080/RESTSpider/users/user  
找回密码：GET http://localhost:8080/RESTSpider/users/password?name={}  
改密码：PUT http://localhost/:8080/RESTSpider/users/password?token={}  
### List
获取待读列表：GET http://localhost:8080/RESTSpider/List/Pending?name={}  
获取关注列表：GET http://localhost:8080/RESTSpider/List/Following?name={}  
加入漫画到待读列表：POST http://localhost:8080/RESTSpider/List/Pending?name={}  
加入漫画到关注列表：POST http://localhost:8080/RESTSpider/List/Following?name={}  
删除待读漫画：DELETE http://localhost:8080/RESTSpider/List/Pending?name={}  
删除关注漫画：DELETE http://localhost:8080/RESTSpider/List/Following?name={}  
### Anime
搜索漫画：GET http://localhost:8080/RESTSpider/animes?name={}  
获取每一话章节链接：GET http://localhost:8080/RESTSpider/animes/detail?name={}  

