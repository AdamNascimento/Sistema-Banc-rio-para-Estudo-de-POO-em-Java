package ConceitosBasicos;
	public abstract class ContaBancaria {

    protected int numeroDaConta;
    protected String titular;
    protected double saldo;

    public ContaBancaria(int numeroDaConta, String titular) {
        this.numeroDaConta = numeroDaConta;
        this.titular = titular;
        this.saldo = 0.0;
    }

    // Métodos que serão herdados por todos
    public int getNumeroDaConta() { return numeroDaConta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println(">>> Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
        } else {
            System.out.println(">>> Erro: Valor de depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println(">>> Erro: Valor de saque deve ser positivo.");
            return;
        }
        if (valor > this.saldo) {
            System.out.println(">>> Saldo insuficiente para este saque.");
            return;
        }
        this.saldo -= valor;
        System.out.println(">>> Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
    }

    // Método abstrato que força as filhas a terem sua própria implementação
    public abstract void exibirExtrato();
}
