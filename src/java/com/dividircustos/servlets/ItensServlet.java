/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dividircustos.servlets;

import com.dividircustos.servlets.objs.Compras;
import com.dividircustos.servlets.objs.Itens;
import com.dividircustos.servlets.objs.Pessoa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ItensServlet extends HttpServlet
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
        JSONObject jObject = new JSONObject();

        try
        {
            JSONObject pessoaJson;
            JSONArray jArray = new JSONArray();
            JSONArray jArrayItens;
            JSONObject itemJson;
            Map<Pessoa, List<Itens>> mpCompras = compras.getCompras();

            for (Pessoa pessoa : mpCompras.keySet())
            {
                pessoaJson = new JSONObject();
                pessoaJson.put("nome", pessoa.getNome());
                jArrayItens = new JSONArray();

                for (Itens item : mpCompras.get(pessoa))
                {
                    itemJson = new JSONObject();
                    itemJson.put("nome", item.getNome());
                    itemJson.put("valor", item.getValor());
                    itemJson.put("percentual", item.isPercentual());
                    jArrayItens.put(itemJson);
                }

                pessoaJson.put("itens", jArrayItens);
                jArray.put(pessoaJson);
            }

            jObject.put("pessoas", jArray);
            jArrayItens = new JSONArray();

            List<Itens> itensRateio = compras.getItensRateio();
            for (Itens item : itensRateio)
            {
                itemJson = new JSONObject();
                itemJson.put("nome", item.getNome());
                itemJson.put("valor", item.getValor());
                itemJson.put("percentual", item.isPercentual());
                jArrayItens.put(itemJson);
            }

            jObject.put("itensRateio", jArrayItens);
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
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null)
        {
            buffer.append(line);
        }

        JSONObject params;
        String nomePessoa;
        String nomeItem;
        double valor;
        boolean ratear;
        String percentual;

        try
        {
            params = new JSONObject(buffer.toString());
            nomePessoa = params.getString("pessoa");
            nomeItem = params.getString("nome");
            valor = params.getDouble("valor");
            ratear = params.getBoolean("ratear");
            percentual = params.getString("percentual");
        } catch (JSONException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try (PrintWriter out = response.getWriter())
            {
                out.println("Falha ao ler os parâmetros do request:\n" + ex.getMessage());
            }
            return;
        }

        if (nomePessoa == null || nomePessoa.trim().isEmpty())
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = response.getWriter())
            {
                out.println("Favor informar a Pessoa!");
            }
            return;
        } else if (nomeItem == null || nomeItem.trim().isEmpty())
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = response.getWriter())
            {
                out.println("Favor informar a Pessoa!");
            }
            return;
        }

        nomePessoa = nomePessoa.trim();
        nomeItem = nomeItem.trim();

        Compras compras = new Compras();
        Pessoa pessoa = new Pessoa(nomePessoa);
        Itens item = new Itens(nomeItem, valor, percentual.equalsIgnoreCase("P"));

        if (ratear)
        {
            compras.addItemRateio(item);
        } else
        {
            compras.addItem(pessoa, item);
        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        try (PrintWriter out = response.getWriter())
        {
            out.println("Item adicionado com sucesso!");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        new Compras().clearItens();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
