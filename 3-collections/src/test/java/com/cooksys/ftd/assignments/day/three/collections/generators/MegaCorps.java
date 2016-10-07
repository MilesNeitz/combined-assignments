package com.cooksys.ftd.assignments.day.three.collections.generators;

import com.cooksys.ftd.assignments.day.three.collections.MegaCorp;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class MegaCorps extends Generator<MegaCorp> {
    @SuppressWarnings("unused")
	private Corp corp;

    public MegaCorps() {
        super(MegaCorp.class);
    }

    @Override
    public MegaCorp generate(SourceOfRandomness random, GenerationStatus status) {
        return new MegaCorp();
    }

    public void configure(Corp corp) {
        this.corp = corp;
    }
}
