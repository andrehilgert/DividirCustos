package com.dividircustos.servlets;

import com.dividircustos.servlets.objs.Compras;
import com.dividircustos.servlets.objs.Pessoa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class PessoasServlet extends HttpServlet
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
            JSONArray jArray = new JSONArray();
            for (Pessoa pessoa : compras.getPessoas())
            {
                JSONObject pessoaJson = new JSONObject();
                pessoaJson.put("nome", pessoa.getNome());
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

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null)
        {
            buffer.append(line);
        }

        JSONObject params;
        String nome;
        try
        {
            params = new JSONObject(buffer.toString());
            nome = params.getString("pessoa");
        } catch (JSONException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try (PrintWriter out = response.getWriter())
            {
                out.println("Falha ao ler os parâmetros do request:\n" + ex.getMessage());
            }
            return;
        }

        if (nome == null || nome.trim().isEmpty())
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = response.getWriter())
            {
                out.println("Favor informar o nome da Pessoa!");
            }
            return;
        }

        nome = nome.trim();

        Compras compras = new Compras();
        compras.addPessoa(nome);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try (PrintWriter out = response.getWriter())
        {
            out.println(nome);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        new Compras().clear();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
