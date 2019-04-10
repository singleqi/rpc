package cn.singleqi.registry;

import cn.singleqi.netty.ConnectManage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ServiceDiscovery {

    @Value("${registry.address}")
    private String registryAddress;

    @Autowired
    ConnectManage connectManage;

    // 服务地址列表
    private volatile List<String> addressList = new ArrayList<>();
    private static final String ZK_REGISTRY_PATH = "/rpc";
    private ZkClient client;


    @PostConstruct
    public void init() {
        client = connectServer();
        if (client != null) {
            watchNode(client);
        }
    }

    private ZkClient connectServer() {
        ZkClient client = new ZkClient(registryAddress, 20000, 20000);
        return client;
    }

    private void watchNode(final ZkClient client) {
        List<String> nodeList = client.subscribeChildChanges(ZK_REGISTRY_PATH, (s, nodes) -> {
            log.info("监听到子节点数据变化{}", JSONObject.toJSONString(nodes));
            addressList.clear();
            getNodeData(nodes);
            updateConnectedServer();
        });
        getNodeData(nodeList);
        log.info("已发现服务列表...{}", JSONObject.toJSONString(addressList));
        updateConnectedServer();
    }

    private void updateConnectedServer() {
        connectManage.updateConnectServer(addressList);
    }

    private void getNodeData(List<String> nodes) {
        log.info("/rpc子节点数据为:{}", JSONObject.toJSONString(nodes));
        for (String node : nodes) {
            String address = client.readData(ZK_REGISTRY_PATH + "/" + node);
            addressList.add(address);
        }
    }
}
