package com.hazelcast.wan.batch.map;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Scanner;

import static com.hazelcast.examples.helper.CommonUtils.sleepMillis;
import static com.hazelcast.examples.helper.LicenseUtils.ENTERPRISE_LICENSE_KEY;

/**
 * You have to set your Hazelcast Enterprise license key to make this code sample work.
 * See README for details.
 */
public class EnterpriseMapWanReplicationClusterB {

    private static HazelcastInstance clusterB;

    public static void main(String[] args) {
        initClusters();
        waitUntilClusterSafe();
        Scanner reader = new Scanner(System.in);
        IMap map = clusterB.getMap("default");
        System.out.println("Cluster is ready now.");
        System.out.println("write \"help\" for the command lists:");
        while (true) {
            sleepMillis(100);
            System.out.println("Command:");
            String command = reader.nextLine();
            if (command.equals("help")) {
                printHelpCommands();
            }
            if (command.equals("size")) {
                System.out.println("map size: " + map.size());
            }
            if (command.startsWith("get")) {
                String token = command.split(" ")[1];
                System.out.println(map.get(token));
            }
        }
    }

    private static void printHelpCommands() {
        System.out.println("Commands:\n"
                + "1) get [key]\n"
                + "2) size\n");
    }

    private static void waitUntilClusterSafe() {
        while (!clusterB.getPartitionService().isClusterSafe()) {
            sleepMillis(100);
        }
    }

    private static void initClusters() {
        clusterB = Hazelcast.newHazelcastInstance(getConfigClusterB());
    }

    private static Config getConfigClusterB() {
        Config config = new Config();
        config.setLicenseKey(ENTERPRISE_LICENSE_KEY);
        config.getGroupConfig().setName("clusterB").setPassword("clusterB-pass");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true).addMember("127.0.0.1:5702");
        return config;
    }
}
