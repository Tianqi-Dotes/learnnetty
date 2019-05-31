# learnnetty
学习netty---掘金小册对应源代码 https://juejin.im/book/5b4bc28bf265da0f60130116

17 测试结果

服务器

服务器收到客户端登陆请求------
Fri May 31 17:44:13 CST 2019:登陆成功
your userid is : ab6c4983
服务器向客户端响应------
服务器收到客户端登陆请求------
Fri May 31 17:44:15 CST 2019:登陆成功
your userid is : a501396c
服务器向客户端响应------
group create successfully! group id is 6dcdcc22
members in the group is :[tq, tqq]
received client check members command!! groupId is 6dcdcc22
send members response to client with members : [tqq]

客户端

客户端收到服务器登陆响应------
客户端收到服务器消息=----登陆成功
your user id is ab6c4983
login success! type in commands!
createGroup
gather a new group! please enter userIds and seperate with ,comma:
ab6c4983,a501396c
login success! type in commands!
client recieved success group create msg, group id is 6dcdcc22
client receive group member msg from server, memebers: [tq, tqq]
leaveGroup
please enter the group Id:
6dcdcc22
login success! type in commands!
server: you success left the group : 6dcdcc22
checkGroup
type in the group id you want see the details!
6dcdcc22
login success! type in commands!
group 6dcdcc22 has the members : [tqq]
