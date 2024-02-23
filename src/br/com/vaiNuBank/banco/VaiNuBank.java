package br.com.vaiNuBank.banco;

import br.com.vaiNuBank.contas.Conta;

import java.util.ArrayList;
import java.util.Optional;

public class VaiNuBank {
    private static ArrayList<Conta> contas = new ArrayList<>();

    public static void add(Conta conta){
        Optional<Conta> OConta = contas.stream().filter(c -> c.getAgenciNum().equals(conta.getAgenciNum()) && c.getCpf().
                equals(conta.getCpf())).findFirst();
        if(OConta.isEmpty()) contas.add(conta);
        else{
            System.out.println("Conta ja existente");
        }
    }

    public static void remove(Conta conta){
        Optional<Conta> OConta = contas.stream().filter(c -> c.getAgenciNum().equals(conta.getAgenciNum()) && c.getCpf().
                equals(conta.getCpf())).findFirst();
        if(OConta.isEmpty()) System.out.println("Problema ao achar sua conta em nosso banco de dados.");
        else{
            contas.remove(conta);
            System.out.println("Conta deletada com sucesso");
        }
    }

    public static ArrayList<Conta> getContas() {
        return contas;
    }
}
