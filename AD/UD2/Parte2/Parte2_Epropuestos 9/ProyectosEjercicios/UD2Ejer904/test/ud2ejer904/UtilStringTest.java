/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer904;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mahroz
 */
public class UtilStringTest {
    
    public UtilStringTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of StrRepetir method, of class UtilString.
     */
    @Test
    public void testStrRepetir() {
        System.out.println("StrRepetir");
        char c = ' ';
        int n = 0;
        String expResult = "";
        String result = UtilString.StrRepetir(c, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Linea method, of class UtilString.
     */
    @Test
    public void testLinea() {
        System.out.println("Linea");
        UtilString.Linea();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
