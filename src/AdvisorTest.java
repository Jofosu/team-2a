import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

    Advisor ac;
    Advisor aa;

    AdvisorTest() {
        this.ac = new Advisor();
        this.aa = new Advisor();
    }

    @Test
    void addAdvisee() {
        ac.addAdvisee("junjie", 999516821, 2020,null);
        ac.addAdvisee("jun", 999516820, 2021,null);
        assertEquals(ac.advisees.size(), 2);
        ac.addAdvisee("xyz", 99516820, 2021,null);
        assertEquals(ac.advisees.size(), 2); //Should not add an unusual ID
        ac.addAdvisee("xyz", 999516820, 20201, null);
        assertEquals(ac.advisees.size(), 2); //Should not add an unusual year
    }

    @org.junit.jupiter.api.Test
    void deleteAdvisee() {
        ac.addAdvisee("junjie", 999516821, 2020,null);
        ac.addAdvisee("jun", 999516820, 2021,null);
        ac.deleteAdvisee(999526411); //jie is not in the list
        assertEquals(ac.advisees.size(),2); // nothing is supposed to be deleted
        ac.deleteAdvisee(999516820);
        assertEquals(ac.advisees.size(), 1); // deletion worked
    }

    @org.junit.jupiter.api.Test
    void returnAdviseeList() {
        aa.addAdvisee("junjie", 999516821, 2020, null);
        ac.addAdvisee("junjie", 999516821, 2020, null);
        ac.addAdvisee("jun", 999516820, 2021, null);
        assertNotEquals(ac.returnAdviseeList(),aa.returnAdviseeList()); //aa set is missing jun
        aa.addAdvisee("jun", 999516820, 2021, null);
        assertEquals(ac.returnAdviseeList(),aa.returnAdviseeList()); //aa set should now have jun and junjie like ac
        aa.deleteAdvisee(999516821);
        aa.addAdvisee("junjie", 999516823, 2020, null);
        assertNotEquals(ac.returnAdviseeList(),aa.returnAdviseeList()); //They have the same names, but are different people


    }
}