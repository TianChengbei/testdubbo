package com.taishangshan.testdubble.admin.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author tianchengbei
 * @since 2019-11-07
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String hostPort = "localhost:2181";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = new ZooKeeper(hostPort, 2000, null);
        System.out.println(zk);
        try {
            zk.setData("/java/aa","测试数据".getBytes("utf8"),1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*if (zk != null) {
            try {
                String zpath = "/";

                zooChildren = zk.getChildren(zpath, false);
                System.out.println("Znodes of '/java/aa': ");
                for (String child : zooChildren) {
                    System.out.println(child);
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }


}
