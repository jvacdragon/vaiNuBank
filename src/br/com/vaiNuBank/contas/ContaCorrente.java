package br.com.vaiNuBank.contas;

public class ContaCorrente extends Conta{
    private double limiteCred;

    public ContaCorrente(String agenciNum, String nomeTitular, String cpf, double limiteCred) {
        super(agenciNum, nomeTitular, cpf);
        this.limiteCred = limiteCred;
    }

    public double getLimiteCred() {
        return limiteCred;
    }

    public void setLimiteCred(double limiteCred) {
        this.limiteCred = limiteCred;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "limiteCred=" + limiteCred +
                "} " + super.toString();
    }
}
