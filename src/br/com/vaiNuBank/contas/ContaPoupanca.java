package br.com.vaiNuBank.contas;

import erros.ErrorSaque;

import java.time.LocalDate;

public class ContaPoupanca extends Conta{
    private LocalDate aniversario;

        public ContaPoupanca(String agenciNum, String nomeTitular, String cpf, LocalDate aniversario) {
        super(agenciNum, nomeTitular, cpf);
        this.aniversario = aniversario;
    }

    @Override
    public double saque(double saque) {
        //Verifica se tem saldo suficiente na conta para transferência.
        try {
            if(this.getValorAtual() < saque) throw new ErrorSaque("Sem valor suficiente na conta para saque.");

            this.setValorAtual(this.getValorAtual() - saque);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return saque*0.98;
    }

    public LocalDate getAniversario() {
        return aniversario;
    }

    public void setAniversario(LocalDate aniversario) {
        this.aniversario = aniversario;
    }
}