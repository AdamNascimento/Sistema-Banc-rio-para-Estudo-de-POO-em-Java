package ConceitosBasicos;
	public class ContaPoupanca extends ContaBancaria {

    private double taxaDeJuros;

    public ContaPoupanca(int numeroDaConta, String titular, double taxaDeJuros) {
        super(numeroDaConta, titular);
        this.taxaDeJuros = taxaDeJuros;
    }

    public void renderJuros() {
        double juros = this.saldo * this.taxaDeJuros;
        this.saldo += juros;
        System.out.println(">>> Juros de R$ " + String.format("%.2f", juros) + " aplicados.");
    }


    public void exibirExtrato() {
        System.out.println("\n--- Extrato Conta Poupança ---");
        System.out.println("Titular: " + this.titular);
        System.out.println("Número: " + this.numeroDaConta);
        System.out.println("Saldo: R$ " + String.format("%.2f", this.saldo));
        System.out.println("Taxa de Juros: " + (this.taxaDeJuros * 100) + "%");
        System.out.println("------------------------------\n");
    }
}