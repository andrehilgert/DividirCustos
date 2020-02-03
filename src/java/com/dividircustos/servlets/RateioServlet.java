/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dividircustos.servlets;

import com.dividircustos.servlets.objs.Compras;
import com.dividircustos.servlets.objs.Itens;
import com.dividircustos.servlets.objs.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author andre
 */
public class RateioServlet extends HttpServlet
{

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Compras compras = new Compras();
        Map<Pessoa, List<Itens>> mpCompras = compras.getCompras();
        Map<Pessoa, Double> mpComprasTotal = new LinkedHashMap();
        double total = 0;
        double totalRat = 0;
        double totalRateado = 0;
        double rateio;
        double totalPessoa;
        int posCompra = 0;
        int qtdeCompras = mpCompras.size();

        for (Pessoa pessoa : mpCompras.keySet())
        {
            totalPessoa = 0;
            for (Itens item : mpCompras.get(pessoa))
            {
                totalPessoa += item.getValor();
            }

            mpComprasTotal.put(pessoa, totalPessoa);
            total += totalPessoa;
        }

        for (Itens itemRat : compras.getItensRateio())
        {
            if (itemRat.isPercentual())
            {
                totalRat += (total * (itemRat.getValor() / 100));
            } else
            {
                totalRat += itemRat.getValor();
            }
        }

        for (Pessoa pessoa : mpComprasTotal.keySet())
        {
            posCompra++;
            rateio = ((mpComprasTotal.get(pessoa) / total) * totalRat);
            if (posCompra == qtdeCompras)
            {
                rateio = totalRat - totalRateado;
            }

            rateio = Double.parseDouble(String.format("%.2f", rateio).replace(".", "").replace(",", "."));

            mpComprasTotal.put(pessoa, mpComprasTotal.get(pessoa) + rateio);
            totalRateado += rateio;
        }

        JSONObject jObject = new JSONObject();

        try
        {
            JSONArray jArray = new JSONArray();
            JSONObject pessoaJson;

            for (Pessoa pessoa : mpComprasTotal.keySet())
            {
                pessoaJson = new JSONObject();
                pessoaJson.put("nome", pessoa.getNome());
                pessoaJson.put("valor", mpComprasTotal.get(pessoa));
                jArray.put(pessoaJson);
            }

            jObject.put("pessoas", jArray);
        } catch (JSONException jse)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try (PrintWriter out = response.getWriter())
            {
                out.println("Falha ao montar json:\n" + jse.getMessage());
            }
        }

        try (PrintWriter out = response.getWriter())
        {
            out.write(jObject.toString());
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

    }
}
