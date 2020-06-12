/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.scheduledexecutor.IScheduledExecutorService;
import com.hazelcast.scheduledexecutor.IScheduledFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MasterMember {

    public static void main(String[] args) throws Exception {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();

        IScheduledExecutorService scheduler = instance.getScheduledExecutorService("scheduler");
        scheduler.scheduleOnAllMembers(new EchoTask("Member Task"), 0, TimeUnit.SECONDS);
        scheduler.schedule(new EchoTask("Other Task"), 0, TimeUnit.SECONDS);

        int totalTasksCount = 0;
        for (Map.Entry<Member, List<IScheduledFuture<Object>>> entry : scheduler.getAllScheduledFutures().entrySet()) {
            totalTasksCount += entry.getValue().size();
        }

        System.out.println("Total tasks count: " + totalTasksCount);
        Hazelcast.shutdownAll();
    }
}
