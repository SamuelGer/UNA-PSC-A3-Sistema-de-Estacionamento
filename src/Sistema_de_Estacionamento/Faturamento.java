package Sistema_de_Estacionamento;

public class Faturamento {
    private static final double PRIMEIRA_HORA = 12.00;
    private static final double HORA_ADICIONAL = 8.00;
    private double totalFaturado;

    // Construtor
    public Faturamento() {
        this.totalFaturado = 0.0;
    }

    //Calcula o valor a pagar baseado no tempo em minutos

    public double calcularValor(long minutos) {
        int horas = (int) Math.ceil(minutos / 60.0);

        if (horas <= 1) {
            return PRIMEIRA_HORA;
        } else {
            return PRIMEIRA_HORA + (HORA_ADICIONAL * (horas - 1));
        }
    }

    //Processa o pagamento de uma estadia e atualiza o total faturado

    public double registrarPagamento(long minutos) {
        double valor = calcularValor(minutos);
        adicionarAoTotal(valor);
        return valor;
    }

    //Formata o tempo em horas e minutos

    public String formatarTempo(long minutos) {
        long horas = minutos / 60;
        long minRestantes = minutos % 60;
        return String.format("%d horas e %d minutos", horas, minRestantes);
    }

    // Adicionar valores ao total faturado
    private void adicionarAoTotal(double valor) {
        if (valor > 0) {
            this.totalFaturado += valor;
        }
    }

    public double getTotalFaturado() {
        return this.totalFaturado;
    }

    // Retorna valor das tarifas
    public String getTarifas() {
        return String.format("Primeira hora: R$ %.2f | Hora adicional: R$ %.2f", PRIMEIRA_HORA, HORA_ADICIONAL);
    }

    // Zerar totalFaturado (Reinicia o dia)
    public void zerarFaturamento() {
        this.totalFaturado = 0.0;
    }
}