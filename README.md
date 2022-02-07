# Shopping-SoftWare

实现了登录注册，修改密码以及个人信息。商品发布，购买，对销量进行统计，查看自己的购买记录，以及商品搜索（关键字搜索），以及购买后可以评论。联系卖家（在线与离线通讯）

Java项目
    CS架构，一个客户端，一个服务端。
    UI用Java swing实现 。
    用maven进行包管理。
数据库为mysql，建表文件为服务端的resources文件夹下的init.sql。

Client为客户端的代码实现
Server为服务端的代码实现
建表文件在.sql文件中，在idea中直接运行即可；

项目运行
    先运行maven的package将client打包，并在pom.xml中导入client
    需要先运行服务端(即服务端下的ServerStart.java文件)。
    再运行客户端下的start文件进入登录界面。
    
   
    主界面:    

 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140414_3c6f6b9c_8491872.png "屏幕截图.png" )

    上传商品界面：  
 
 ![](https://images.gitee.com/uploads/images/2021/0928/140425_0aeb7120_8491872.png "屏幕截图.png")
   
    统计销量界面：

 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140447_4df11371_8491872.png "屏幕截图.png")

    消息列表界面： 

 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140458_def07349_8491872.png "屏幕截图.png")
    
    登录界面： 

 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140508_c18c224e_8491872.png "屏幕截图.png")

    商品详细信息界面： 

 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140520_e7c7ff93_8491872.png "屏幕截图.png")
   
    修改个人信息界面：
  
 ![输入图片说明](https://images.gitee.com/uploads/images/2021/0928/140530_d7dc03ed_8491872.png "屏幕截图.png")


