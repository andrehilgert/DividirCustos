/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dividircustos.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class PessoasServletTest
{

    public PessoasServletTest()
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
     * Test of doGet method, of class PessoasServlet.
     */
    @Test
    public void testDoGet() throws Exception
    {
        System.out.println("doGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        PessoasServlet instance = new PessoasServlet();
        instance.doGet(request, response);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of doPost method, of class PessoasServlet.
     */
    @Test
    public void testDoPost() throws Exception
    {
        System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        PessoasServlet instance = new PessoasServlet();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of doDelete method, of class PessoasServlet.
     */
    @Test
    public void testDoDelete() throws Exception
    {
        System.out.println("doDelete");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        PessoasServlet instance = new PessoasServlet();
        instance.doDelete(request, response);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

}
