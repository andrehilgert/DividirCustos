package com.dividircustos.servlets.objs;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author andre
 */
public class ComprasTest
{

    public ComprasTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of addPessoa method, of class Compras.
     */
    @Test
    public void testAddPessoa_String()
    {
        System.out.println("addPessoa");
        String nomePessoa = "Andre";
        Compras instance = new Compras();
        instance.addPessoa(nomePessoa);
        Compras compras = new Compras();
        Pessoa pessoa = new Pessoa("Andre");
        compras.addPessoa(pessoa);
        Assert.assertTrue(compras.getCompras().size() == 1);
    }

    /**
     * Test of addPessoa method, of class Compras.
     */
    @Test
    public void testAddPessoa_Pessoa()
    {
        System.out.println("addPessoa");
        Pessoa pessoa = null;
        Compras instance = new Compras();
        instance.addPessoa(pessoa);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class Compras.
     */
    @Test
    public void testAddItem()
    {
        System.out.println("addItem");
        Pessoa pessoa = null;
        Itens item = null;
        Compras instance = new Compras();
        instance.addItem(pessoa, item);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of addItemRateio method, of class Compras.
     */
    @Test
    public void testAddItemRateio()
    {
        System.out.println("addItemRateio");
        Itens item = null;
        Compras instance = new Compras();
        instance.addItemRateio(item);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getPessoas method, of class Compras.
     */
    @Test
    public void testGetPessoas()
    {
        System.out.println("getPessoas");
        Compras instance = new Compras();
        List<Pessoa> expResult = null;
        List<Pessoa> result = instance.getPessoas();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getCompras method, of class Compras.
     */
    @Test
    public void testGetCompras()
    {
        System.out.println("getCompras");
        Compras instance = new Compras();
        Map<Pessoa, List<Itens>> expResult = null;
        Map<Pessoa, List<Itens>> result = instance.getCompras();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getItensRateio method, of class Compras.
     */
    @Test
    public void testGetItensRateio()
    {
        System.out.println("getItensRateio");
        Compras instance = new Compras();
        List<Itens> expResult = null;
        List<Itens> result = instance.getItensRateio();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class Compras.
     */
    @Test
    public void testClear()
    {
        System.out.println("clear");
        Compras instance = new Compras();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of clearItens method, of class Compras.
     */
    @Test
    public void testClearItens()
    {
        System.out.println("clearItens");
        Compras instance = new Compras();
        instance.clearItens();
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

}
