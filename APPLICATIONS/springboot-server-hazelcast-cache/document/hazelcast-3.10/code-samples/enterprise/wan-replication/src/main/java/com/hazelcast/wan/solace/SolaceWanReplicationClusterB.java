package com.hazelcast.wan.solace;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Scanner;

public class SolaceWanReplicationClusterB {

    private static HazelcastInstance clusterB;

    public static void main(String[] args) throws InterruptedException {
        initClusters();
        waitUntilClusterSafe();
        Scanner reader = new Scanner(System.in);
        IMap map = clusterB.getMap("default");
        System.out.println("Cluster is ready now.");
        System.out.println("write \"help\" for the command lists:");
        for (; ; ) {
            Thread.sleep(100);
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

    private static void waitUntilClusterSafe() throws InterruptedException {
        while (!clusterB.getPartitionService().isClusterSafe()) {
            Thread.sleep(100);
        }
    }

    private static void initClusters() {
        clusterB = Hazelcast.newHazelcastInstance(new ClasspathXmlConfig("hazelcast-solace-clusterB.xml"));
    }

}
