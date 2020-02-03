/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dividircustos.servlets.objs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andre
 */
public class Compras
{

    private static final Map<Pessoa, List<Itens>> compras = new HashMap();
    private static final List<Itens> itensRateio = new ArrayList();

    public void addPessoa(String nomePessoa)
    {
        Pessoa pessoa = new Pessoa(nomePessoa);
        addPessoa(pessoa);
    }

    public void addPessoa(Pessoa pessoa)
    {
        if (!compras.containsKey(pessoa))
        {
            compras.put(pessoa, new ArrayList());
        }
    }

    public void addItem(Pessoa pessoa, Itens item)
    {
        addPessoa(pessoa);
        compras.get(pessoa).add(item);
    }

    public void addItemRateio(Itens item)
    {
        itensRateio.add(item);
    }

    public List<Pessoa> getPessoas()
    {
        List<Pessoa> pessoas = new ArrayList();

        for (Pessoa pessoa : compras.keySet())
        {
            pessoas.add(pessoa);
        }

        return pessoas;
    }

    public Map<Pessoa, List<Itens>> getCompras()
    {
        return compras;
    }

    public List<Itens> getItensRateio()
    {
        return itensRateio;
    }

    public void clear()
    {
        compras.clear();
        itensRateio.clear();
    }

    public void clearItens()
    {
        for (Pessoa pessoa : compras.keySet())
        {
            compras.get(pessoa).clear();
        }

        itensRateio.clear();
    }
}
