package cn.singleqi.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.imageio.spi.ServiceRegistry;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class NettyServer implements ApplicationContextAware, InitializingBean {

    private Map<String, Object> serviceMap = new HashMap<>();

    @Value("${rpc.server.address}")
    private String serverAddress;

    @Autowired
    ServiceRegistry registry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}
