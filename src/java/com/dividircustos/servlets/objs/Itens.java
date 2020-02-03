package com.dividircustos.servlets.objs;

/**
 *
 * @author andre
 */
public class Itens
{

    private final String nome;
    private double valor;
    private boolean percentual;

    public Itens(String nome, double valor, boolean percentual)
    {
        this.nome = nome;
        this.valor = valor;
        this.percentual = percentual;
    }

    public Itens(String nome)
    {
        this.nome = nome;
        this.valor = 0.0;
        this.percentual = false;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public void setPercentual(boolean percentual)
    {
        this.percentual = percentual;
    }

    public String getNome()
    {
        return nome;
    }

    public double getValor()
    {
        return valor;
    }

    public boolean isPercentual()
    {
        return percentual;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Itens other = (Itens) obj;
        if (!this.nome.equalsIgnoreCase(other.nome))
        {
            return false;
        }

        return this.percentual == other.percentual;
    }

    @Override
    public String toString()
    {
        return nome + ", valor = " + valor + (percentual ? " %" : "");
    }

}
