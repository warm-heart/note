package org.cloud.note.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangqianlong
 * @create 2020-08-16 19:58
 */

public class sentinel {
    private static JedisSentinelPool jSentinelPool;

    public static void main(String[] args) throws Exception {

        Set<String> sentinels = new HashSet<String>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26380");
        sentinels.add("127.0.0.1:26381");

        String clusterName = "master";

        JedisSentinelPool jsentinelPool = new JedisSentinelPool(clusterName, sentinels);
        Jedis jedis = null;
        try {
            jedis = jsentinelPool.getResource();
            jedis.set("key", "aaa");
            while (true) {

                try {
                    System.out.println(jedis.getClient().getHost() + ":" + jedis.getClient().getPort() + "@" + jedis.get("key"));
                } catch (Exception e) {
                    System.out.println("getConntion  error,waiting  5s,will try again..." + e.getMessage());
                    Thread.sleep(5000);
                    try {
                        jedis = jsentinelPool.getResource();
                    } catch (Exception e1) {
                        System.out.println("getResource  error2,waiting  more,will try again..." + e.getMessage());
                    }
                }
                Thread.sleep(2000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        jsentinelPool.close();
    }
}
