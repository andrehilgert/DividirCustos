package com.dividircustos.servlets.objs;

import java.util.Objects;

/**
 *
 * @author andre
 */
public class Pessoa {

    private final String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.nome, other.nome);
    }
}
