package com.taishangshan.testdubble.admin.test;

import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import sun.misc.Cleaner;

/**
 * @author tianchengbei
 * @since 2019-11-07
 */
public class SimpleZkClient {

    //这里是服务器里安装的ZK，conf -> zoo.cfg 里自己配的 server
    private static final String connectString = "localhost:2181";
    private static final int sessionTimeout = 2000;

    static ZooKeeper zkClient = null;
    static {
        try {
            init();
        } catch (Exception e) {
            System.out.println("11"+e.getMessage());
        }
    }

    public static void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "-22---" + event.getPath());
                try {
                    zkClient.getChildren("/", true);//再次触发监听
                } catch (Exception e) {
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        SimpleZkClient client = new SimpleZkClient();
        client.testGetZnode();
    }

    /**
     * 创建数据节点到zk中
     * 描述:在zk的根路径下创建一个节点eclipse，内容为hellozk，
     * 第三个参数是权限，我们给的是Ids.OPEN_ACL_UNSAFE 意思是 This is a completely open ACL .
     */

    public void testCreate() throws Exception {
        String create = zkClient.create("/eclipse/tt", "dfdfd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("创建----" + create);
    }

    /**
     * 获取节点内容
     * 描述：获取/eclipse这个节点的内容
     * 第二个参数：是否监听，这里的意思：/eclipse节点下内容发生变化时，则会通知。
     *
     * @throws Exception
     */
    public void testGetZnode() throws Exception {
        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    /**
     * 设置znode内容
     * 修改/eclipse节点的内容为helloWHB
     *
     * @throws Exception
     */
    public void testSetData() throws Exception {
        zkClient.setData("/eclipse", "helloWHB".getBytes(), -1);
        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    /**
     * 判断znode是否存在
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void testExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/eclipse", false);
        System.out.println(null == stat ? "znode不存在" : "znode存在");
    }

    /**
     * 删除znode
     * 删除/eclipse这个节点
     *
     * @throws Exception
     */
    public void testDelete() throws Exception {
        // -1表示所有版本
        zkClient.delete("/eclipse", -1);
        testExist();
    }

    /**
     * 获取子节点
     *
     * @throwsException
     */
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);//让程序一直运行，在CRT终端里 ls / watch ;create /appe www ；观察控制台打印情况
    }

}
