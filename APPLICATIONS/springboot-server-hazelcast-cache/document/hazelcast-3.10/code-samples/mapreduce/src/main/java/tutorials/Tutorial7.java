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

package tutorials;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.aggregation.Aggregation;
import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.PropertyExtractor;
import com.hazelcast.mapreduce.aggregation.Supplier;
import model.SalaryYear;

public class Tutorial7 implements Tutorial {

    @Override
    public void execute(HazelcastInstance hazelcastInstance) throws Exception {
        IMap<String, SalaryYear> map = hazelcastInstance.getMap("salaries");
        Supplier<String, SalaryYear, Integer> supplier = Supplier.all(new SalaryPropertyExtractor());

        Aggregation<String, Integer, Integer> aggregation = Aggregations.integerSum();
        int sum = map.aggregate(supplier, aggregation);
        System.out.println("Salary sum: " + sum);
    }

    private static class SalaryPropertyExtractor implements PropertyExtractor<SalaryYear, Integer> {

        @Override
        public Integer extract(SalaryYear salaryYear) {
            return salaryYear.getAnnualSalary();
        }
    }
}
