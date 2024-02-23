package br.com.vaiNuBank.contas;

import br.com.vaiNuBank.banco.VaiNuBank;
import br.com.vaiNuBank.erros.ErrorDeposito;
import br.com.vaiNuBank.erros.ErrorTransferencia;
import br.com.vaiNuBank.erros.ErrorSaque;

import java.util.Optional;

public class Conta implements IConta{
    private String agenciNum;
    private String nomeTitular;
    private final String cpf;
    private double valorAtual;

    public Conta(String agenciNum, String nomeTitular, String cpf){
        this.agenciNum = agenciNum;
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
        this.valorAtual = 0;
    }

    @Override
    public double saque(double saque) {

        try {
            //Verifica se o valor para saque é válido (sendo menor que 0.01 ele é inválido).
            if(saque<0.01) throw new ErrorSaque("Favor deposite um valor válido.");

            //Verifica se tem saldo suficiente na conta para transferência.
            if(this.getValorAtual() < saque) throw new ErrorSaque("Sem valor suficiente na conta para saque.");

            this.setValorAtual(this.getValorAtual() - saque);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return saque;
    }

    @Override
    public double deposito(double deposito) {
        try{
            //Verifica se o valor para depósito é válido (sendo menor que 0.01 ele é inválido).
            if(deposito<0.01) throw new ErrorDeposito("Favor deposite um valor válido.");

            this.setValorAtual(deposito);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }

        return deposito;
    }

    @Override
    public double transferencia(double transferencia, String agencia, String cpf) {
        try{

            //Verifica se o valor para transferencia é válido (sendo menor que 0.01 ele é inválido).
            if(transferencia<0.01) throw new ErrorDeposito("Favor transfira um valor válido.");

            //Verifica se está transferindo para a mesma conta
            if(cpf.equals(this.getCpf()) && agencia.equals(this.getAgenciNum())) throw new ErrorTransferencia("Não é" +
                    " possível transferir para a mesma conta.");

            //Verifica se a conta para transferir existe no banco de dados de VaiNuBank.
            Optional<Conta> OConta = VaiNuBank.getContas().stream().filter(c -> c.getAgenciNum().equals(agencia) && c.getCpf().
                    equals(cpf)).findFirst();
            if(OConta.isEmpty()) throw new ErrorTransferencia("Conta para transferencia não existente em nosso banco de dados.");

            //Verifica se há valor suficiente na conta para transferencia.
            if(this.getValorAtual() < transferencia) throw new ErrorTransferencia("Sem valor suficiente na conta para transferencia.");

            //Transfere o valor caso passe pelas verificações acima
            OConta.get().setValorAtual(OConta.get().getValorAtual() + transferencia);
            this.setValorAtual(this.getValorAtual()-transferencia);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }

        return transferencia;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "agenciNum='" + agenciNum + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", cpf='" + cpf + '\'' +
                ", valorAtual=" + valorAtual +
                '}';
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getAgenciNum() {
        return agenciNum;
    }

    public void setAgenciNum(String agenciNum) {
        this.agenciNum = agenciNum;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }
}
