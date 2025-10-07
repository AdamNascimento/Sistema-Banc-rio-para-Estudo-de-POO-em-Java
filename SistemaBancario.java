package ConceitosBasicos;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBancario {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<ContaBancaria> contas = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Sistema Bancário ---");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Acessar conta existente");
            System.out.println("0. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    acessarConta();
                    break;
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    return; // Encerra o programa
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void criarConta() {
        System.out.println("\n--- Criação de Conta ---");
        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do titular: ");
        String titular = scanner.nextLine();

        System.out.println("Qual o tipo de conta?");
        System.out.println("1. Poupança");
        System.out.println("2. Corrente");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            System.out.print("Digite a taxa de juros (ex: 0.01 para 1%): ");
            double juros = scanner.nextDouble();
            ContaPoupanca novaConta = new ContaPoupanca(numero, titular, juros);
            contas.add(novaConta);
        } else if (tipo == 2) {
            System.out.print("Digite o limite do cheque especial: ");
            double limite = scanner.nextDouble();
            ContaCorrente novaConta = new ContaCorrente(numero, titular, limite);
            contas.add(novaConta);
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }
        System.out.println(">>> Conta criada com sucesso!");
    }

    public static void acessarConta() {
        System.out.print("\nDigite o número da conta para acessar: ");
        int numero = scanner.nextInt();

        ContaBancaria contaEncontrada = null;
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numero) {
                contaEncontrada = conta;
                break;
            }
        }

        if (contaEncontrada == null) {
            System.out.println(">>> Conta não encontrada.");
            return;
        }

        while (true) {
            System.out.println("\n--- Menu da Conta " + contaEncontrada.getNumeroDaConta() + " (" + contaEncontrada.getTitular() + ") ---");
            System.out.println("Saldo atual: R$ " + String.format("%.2f", contaEncontrada.getSaldo()));
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir extrato");

            // Opções específicas para cada tipo de conta
            if (contaEncontrada instanceof ContaPoupanca) {
                System.out.println("4. Aplicar Juros");
            }
            if (contaEncontrada instanceof Tributavel) {
                System.out.println("5. Calcular Imposto");
            }
            
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para depositar: ");
                    double valorDeposito = scanner.nextDouble();
                    contaEncontrada.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.print("Digite o valor para sacar: ");
                    double valorSaque = scanner.nextDouble();
                    contaEncontrada.sacar(valorSaque);
                    break;
                case 3:
                    contaEncontrada.exibirExtrato();
                    break;
                case 4:
                    if (contaEncontrada instanceof ContaPoupanca) {
                        ((ContaPoupanca) contaEncontrada).renderJuros();
                    } else {
                        System.out.println("Opção inválida para este tipo de conta.");
                    }
                    break;
                case 5:
                    if (contaEncontrada instanceof Tributavel) {
                        double imposto = ((Tributavel) contaEncontrada).getValorImposto();
                        System.out.println(">>> Imposto devido: R$ " + String.format("%.2f", imposto));
                    } else {
                        System.out.println("Opção inválida para este tipo de conta.");
                    }
                    break;
                case 0:
                    return; // Volta ao menu principal
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}