import br.com.vaiNuBank.banco.VaiNuBank;
import br.com.vaiNuBank.contas.Conta;
import br.com.vaiNuBank.contas.ContaCorrente;
import br.com.vaiNuBank.contas.ContaPoupanca;
import br.com.vaiNuBank.validador.CpfUtils;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        Conta usuarioAtual = null;
        boolean rodando = true;

        while(rodando) {
            System.out.println("=== Vai-Nu Bank ===");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar conta corrente");
            System.out.println("2. Criar conta poupança");
            System.out.println("3. Entrar em conta existente");
            System.out.println("4. Fechar sistema");

            while (true) {
                System.out.print("Opção: ");
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, insira um número válido para a opção.");
                }
            }

            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Você escolheu criar uma conta corrente.");

                        System.out.println("Digite seu nome completo: ");
                        String nome = scanner.nextLine();

                        System.out.println(nome);

                        System.out.println("Digite seu cpf: ");
                        String cpf = scanner.nextLine();

                        System.out.println(cpf);
                        if (!CpfUtils.isValid(cpf)) throw new Exception("Favor informar um CPF válido");

                        double defaultLimite = 2000.00;

                        cpf = cpf.trim().replace(".", "").replace("-", "");
                        ContaCorrente novaConta = new ContaCorrente(geradorAgencia(), nome, cpf, defaultLimite);
                        VaiNuBank.add(novaConta);

                        System.out.println("Conta criada com sucesso. Seu número de agência é: " + novaConta.getAgenciNum());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    try {
                        System.out.println("Você escolheu criar uma conta poupança.");

                        System.out.println("Digite seu nome completo: ");
                        String nome = scanner.nextLine();

                        System.out.println(nome);

                        System.out.println("Digite seu cpf: ");
                        String cpf = scanner.nextLine();

                        System.out.println(cpf);
                        if (!CpfUtils.isValid(cpf)) throw new Exception("Favor informar um CPF válido");

                        System.out.println("Digite o dia de seu nascimento: ");
                        int dia = scanner.nextInt();

                        System.out.println("Digite o mês de seu nascimento: ");
                        int mes = scanner.nextInt();

                        System.out.println("Digite o ano de seu nascimento: ");
                        int ano = scanner.nextInt();

                        LocalDate nascimento = LocalDate.of(ano, mes, dia);


                        cpf = cpf.trim().replace(".", "").replace("-", "");
                        ContaPoupanca novaConta = new ContaPoupanca(geradorAgencia(), nome, cpf, nascimento);
                        VaiNuBank.add(novaConta);

                        System.out.println("Conta criada com sucesso. Seu número de agência é: " + novaConta.getAgenciNum());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                        System.out.println("Sua conta não foi criada. Favor digitar apenas valores válidos");
                    }
                    break;
                case 3:
                    System.out.println("Você escolheu entrar em uma conta existente.");
                    System.out.println("Digite seu cpf: ");
                    String cpf = scanner.nextLine();

                    System.out.println("Digite o número de sua agência: ");
                    String agencia = scanner.next();

                    Optional<Conta> conta = VaiNuBank.getContas()
                            .stream()
                            .filter(c -> c.getCpf().equals(cpf) && c.getAgenciNum().equals(agencia))
                            .findFirst();

                    if (!conta.isEmpty()) usuarioAtual = conta.get();
                    else {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    menuConta(usuarioAtual);
                    break;

                case 4:
                    System.out.println("Fechando sistema.");

                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    public static String geradorAgencia(){
        int aleatorio = new Random().nextInt(900)+100;
        return String.valueOf(aleatorio);
    }

    public static void menuConta(Conta c){
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("=== Bem vindo " + c.getNomeTitular() + " ===");
        System.out.println("Agência: " + c.getAgenciNum());
        System.out.println("CPF: " + c.getCpf());
        System.out.println("Saldo atual: " + c.getValorAtual());

        if(c instanceof ContaPoupanca) System.out.println("Aniversário: " + ((ContaPoupanca) c).getAniversario());
        if(c instanceof ContaCorrente) System.out.println("Limite de crédito: " + ((ContaCorrente) c).getLimiteCred());

        System.out.println("Escolha uma opção:");
        System.out.println("1. Depositar");
        System.out.println("2. Sacar");
        System.out.println("3. Transferir");
        System.out.println("4. Deletar conta");
        System.out.print("Opção: ");

        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                try{
                    System.out.println("Digite valor para depósito. Caso tenha centavos, separe por um ponto (.) o valor" +
                            " inteiro dos centavos.");

                    double valorDeposito = scanner.nextDouble();

                    c.deposito(valorDeposito);
                    System.out.println("Valor depositado com sucesso. Seu saldo atual é de: " + c.getValorAtual());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Favor digitar valores válidos.");
                }
                break;
            case 2:
                try{
                    System.out.println("Digite valor para saque. Caso tenha centavos, separe por um ponto (.) o valor" +
                            " inteiro dos centavos.");

                    double valorSaque = scanner.nextDouble();

                    c.saque(valorSaque);
                    System.out.println("Valor sacado com sucesso. Seu saldo atual é de: " + c.getValorAtual());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Favor digitar valores válidos.");
                }
                break;
            case 3:
                try{
                    System.out.println("Digite valor para transferência. Caso tenha centavos, separe por um ponto (.) o valor" +
                            " inteiro dos centavos.");

                    double valorTransferencia = scanner.nextDouble();

                    System.out.println("Digite a agência do remetente: ");
                    String agencia = scanner.nextLine();

                    System.out.println("Digite o CPF do remetente: ");
                    String cpf = scanner.nextLine();

                    c.transferencia(valorTransferencia, agencia, cpf);
                    System.out.println("Valor transferido com sucesso. Seu saldo atual é de: " + c.getValorAtual());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Favor digitar valores válidos.");
                }
                break;
            case 4:
                Optional<Conta> conta = VaiNuBank.getContas()
                        .stream()
                        .filter(account -> account.getCpf().equals(c.getCpf()) && account.getAgenciNum().equals(c.getAgenciNum()))
                        .findFirst();

                VaiNuBank.remove(conta.get());
                break;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                break;
        }
    }
}
