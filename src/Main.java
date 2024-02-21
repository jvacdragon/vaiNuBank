import banco.VaiNuBank;
import br.com.vaiNuBank.contas.ContaCorrente;
import br.com.vaiNuBank.contas.ContaPoupanca;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date data = new Date();
        LocalDate lData = LocalDate.of(2022, 5, 25);

        ContaCorrente joao = new ContaCorrente("144", "Joao Vitor", "18233437751", 2500.00);
        ContaPoupanca pedro = new ContaPoupanca("146", "Pedro Henrique", "355.186.990-18",
                LocalDate.of(2003, 5, 25));


        VaiNuBank.add(joao);
        VaiNuBank.add(pedro);
        joao.deposito(2000);
        joao.transferencia(1500,"146", "355.186.990-18");

        System.out.println(joao.getValorAtual());
        System.out.println(pedro.getValorAtual());
    }
}
