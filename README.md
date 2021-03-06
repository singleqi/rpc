# 流程
## rpc基于netty、zookeeper、动态代理、反射、序列化实现
### provider-netty-server
在nettyServer中启动netty服务器,并将服务地址传到zookeeper中注册。
### provider-zookeeper
在zookeeper的/rpc目录下创建/provider顺序节点，data为rpc服务端地址serverAddress。
### consumer-zookeeper
从zookeeper的/rpc目录下取出所有节点并且监听是否有节点变化，取出所有节点的data。
### consumer-netty-client
将从zookeeper取得的data,放到ConnectManage中管理，并建立Channel通道。


### consumer-动态代理
RpcScannerConfigurer扫描调用rpc的接口的包，  
ClassPathRpcScanner为每个类设置属性，和特殊化处理（覆盖方法），  
RpcFactoryBean设置每个类在调用方法的时候，会被代理，  
RpcFactory设置代理的方法会将接口名、方法名、参数保存为request并传给nettyClient发送。
### consumer-netty-client、序列化
在channels中选择一个channel将request发送出去，每次channel=原子加+1%channel.size()，  
读写完成后每30s会发送心跳测试。
### provider-netty-server
从request中取出接口名、方法名、参数。
### provider-反射、（反）序列化
通过反射调用本地接口，并将数据序列化后放到response中返回。
### consumer-netty-client、动态代理
取得response返回给代理，代理从中获取数据  。

## 测试
*新增*  
POST /user HTTP/1.1  
Host: localhost:8002  
Content-Type: application/json  
cache-control: no-cache  
{

	"name":"test",
	"address":"test"
}

*查看*  
GET /user/<userId> HTTP/1.1  
Host: localhost:8002  
cache-control: no-cache  

*查看所有*  
GET /user HTTP/1.1  
Host: localhost:8002  
cache-control: no-cache  

*修改*  
PUT /user/<userId> HTTP/1.1  
Host: localhost:8002  
Content-Type: application/json  
cache-control: no-cache  
{

	"name":"test",
	"address":"test1"
}

*删除*  
DELETE /user/<userId> HTTP/1.1  
Host: localhost:8002  
cache-control: no-cache  

[参考](https://juejin.im/post/5c6d7640f265da2de80f5e9c#heading-10)

## rpc基于Dubbo实现
### provider-zookeeper
将接口信息注册到zookeeper的/dubbo目录下，并存下当前接口服务的信息，例如：  
dubbo://192.168.1.4:20880/cn.singleqi.service.HelloService?anyhost=true&application=dubbo-provider&dubbo=2.6.0&generic=false&interface=cn.singleqi.service.HelloService&methods=sayHello&pid=2936&side=provider&timestamp=1555499312341

### consumer-zookeeper
将接口调用注册到zookeeper的/dubbo目录下，并存下当前接口调用的信息，例如：  
consumer://192.168.1.4/cn.singleqi.service.HelloService?application=dubbo-consumer&category=consumers&check=false&dubbo=2.6.0&interface=cn.singleqi.service.HelloService&methods=sayHello&pid=12156&side=consumer&timestamp=1555499642279

[参考](https://juejin.im/post/5bfdf8f86fb9a049df23c37f#heading-24)