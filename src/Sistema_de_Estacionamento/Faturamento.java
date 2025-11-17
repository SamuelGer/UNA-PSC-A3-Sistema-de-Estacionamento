package Sistema_de_Estacionamento;

public class Faturamento {
    private static final double primeiraHoraCarro = 12.00;
    private static final double horaAdiconalCarro = 8.00;
    private static final double primeiraHoraMoto = 8.00;
    private static final double horaAdiconalMoto = 5.00;

    private double totalFaturado;


    // Construtor
    public Faturamento() {
        this.totalFaturado = 0.0;
    }

    //Calcula o valor a pagar baseado no tempo em minutos

    public double calcularValor(long calcularPermanencia) {
        if (calcularPermanencia < 0) {
            throw new IllegalArgumentException("Tempo não pode ser negativo" + calcularPermanencia);
        }

        int horas = (int) Math.ceil(calcularPermanencia / 60.0);

        if (horas <= 1 && Veiculo.getOpcao() == 1) {
            return primeiraHoraMoto;
        } else if (horas <= 1 && Veiculo.getOpcao() == 2) {
            return primeiraHoraCarro;
        }
        if (horas > 1 && Veiculo.getOpcao() == 1) {
            return (horaAdiconalMoto * (horas - 1));
        } else {
            return (horaAdiconalCarro * (horas - 1));

        }}

        //Processa o pagamento de uma estadia e atualiza o total faturado

        public double registrarPagamento ( long calcularPermanencia){
            double valor = calcularValor(calcularPermanencia);
            adicionarAoTotal(valor);
            return valor;
        }

        //Formata o tempo em horas e minutos

        public String formatarTempo (long calcularPermanencia){
            long horas = calcularPermanencia / 60;
            long minRestantes = calcularPermanencia % 60;
            return String.format("%d horas e %d minutos", horas, minRestantes);
        }

        // Adicionar valores ao total faturado
        private void adicionarAoTotal ( double valor){
            if (valor > 0) {
                this.totalFaturado += valor;
            }
        }

        public double getTotalFaturado () {
            return this.totalFaturado;
        }

        // Retorna valor das tarifas
        public void  getTarifas () {
            System.out.printf("Primeira hora: R$ %.2f | Hora adicional: R$ %.2f", primeiraHoraCarro, horaAdiconalCarro);
            System.out.printf("Primeira hora: R$ %.2f | Hora adicional: R$ %.2f", primeiraHoraMoto, horaAdiconalMoto);
        }

        // Zerar totalFaturado (Reinicia o dia)
        public void zerarFaturamento () {
            this.totalFaturado = 0.0;
        }



}

