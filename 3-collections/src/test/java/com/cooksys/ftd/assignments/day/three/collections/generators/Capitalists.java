package com.cooksys.ftd.assignments.day.three.collections.generators;

import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;
import com.cooksys.ftd.assignments.day.three.collections.model.WageSlave;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public interface Capitalists {

    default FatCat generateFatCat(SourceOfRandomness random, int depth) {
        String name = String.format("cat-%s", random.nextInt());
        int salary = random.nextInt((int) 1e6, (int) 1e10);
        FatCat owner = depth > 0 ? generateFatCat(random, depth - 1) : null;
        return owner != null ? new FatCat(name, salary, owner) : new FatCat(name, salary);
    }

    default WageSlave generateWageSlave(SourceOfRandomness random, int depth) {
        String name = String.format("slave-%s", random.nextInt());
        int salary = random.nextInt((int) 1e2, (int) 1e4);
        FatCat owner = depth > 0 ? generateFatCat(random, depth - 1) : null;
        return owner != null ? new WageSlave(name, salary, owner) : new WageSlave(name, salary);
    }
}
