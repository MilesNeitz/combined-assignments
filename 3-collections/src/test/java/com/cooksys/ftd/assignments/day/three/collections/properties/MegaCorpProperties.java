package com.cooksys.ftd.assignments.day.three.collections.properties;

import com.cooksys.ftd.assignments.day.three.collections.MegaCorp;
import com.cooksys.ftd.assignments.day.three.collections.generators.Cap;
import com.cooksys.ftd.assignments.day.three.collections.generators.Corp;
import com.cooksys.ftd.assignments.day.three.collections.generators.Slave;
import com.cooksys.ftd.assignments.day.three.collections.model.Capitalist;
import com.cooksys.ftd.assignments.day.three.collections.model.WageSlave;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import com.cooksys.ftd.assignments.day.three.collections.generators.Cat;
import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;
import com.pholser.junit.quickcheck.Property;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class MegaCorpProperties {


    @Property
    public void addNull(@Corp MegaCorp corp) {
        assertFalse("#add() did not return false when called with null", corp.add(null));
    }

    @Property
    public void addHasParentlessWageSlave(@Corp MegaCorp corp, @Slave WageSlave slave) {
        assertFalse("#add() did not return false when called with a parent-less WageSlave", corp.add(slave));
        assertFalse("#has() did not return false when called with a parent-less WageSlave that failed to be added", corp.has(slave));
    }

    @Property
    public void addHasParentlessFatCat(@Corp MegaCorp corp, @Cat FatCat cat) {
        assertTrue("#add() did not return true when called with a parent-less FatCat", corp.add(cat));
        assertTrue("#has() did not return true when called with a parent-less FatCat that was just added", corp.has(cat));
    }

    @Property
    public void addHasCapitalistWithParent(@Corp MegaCorp corp, @Cap(depth = 1) Capitalist cap) {
        assertTrue("#add() did not return true when called with a Capitalist with one parent", corp.add(cap));
        assertTrue("#has() did not return true when called with the parent of an element that was just added", corp.has(cap));
        assertTrue("#has() did not return true when called with an element that was just added", corp.has(cap));
    }

}
