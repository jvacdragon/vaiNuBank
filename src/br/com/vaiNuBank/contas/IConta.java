package br.com.vaiNuBank.contas;

public interface IConta {
    double saque(double saque);
    double deposito(double deposito);
    double transferencia(double transferencia, String agencia, String cpf);
}
