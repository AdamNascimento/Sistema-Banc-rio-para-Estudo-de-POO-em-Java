package ConceitosBasicos;
	public class ContaCorrente extends ContaBancaria implements Tributavel {

    private double limiteChequeEspecial;

    public ContaCorrente(int numeroDaConta, String titular, double limiteChequeEspecial) {
        super(numeroDaConta, titular);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Polimorfismo: Sobrescrevendo o comportamento de sacar
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println(">>> Erro: Valor de saque deve ser positivo.");
            return;
        }
        if (valor > (this.saldo + this.limiteChequeEspecial)) {
            System.out.println(">>> Limite de cheque especial insuficiente.");
            return;
        }
        this.saldo -= valor;
        System.out.println(">>> Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
    }


    public void exibirExtrato() {
        System.out.println("\n--- Extrato Conta Corrente ---");
        System.out.println("Titular: " + this.titular);
        System.out.println("Número: " + this.numeroDaConta);
        System.out.println("Saldo: R$ " + String.format("%.2f", this.saldo));
        System.out.println("Limite Cheque Especial: R$ " + String.format("%.2f", this.limiteChequeEspecial));
        System.out.println("------------------------------\n");
    }

    // Implementação obrigatória da interface Tributavel
    public double getValorImposto() {
        // Imposto de 0.5% sobre saques que deixam a conta negativa
        //  calculo sobre o valor que excede o saldo.
        if (this.saldo < 0) {
            return Math.abs(this.saldo) * 0.005; // 0.5% sobre o saldo devedor
        }
        return 0;
    }
}