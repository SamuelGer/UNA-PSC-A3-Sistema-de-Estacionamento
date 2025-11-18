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

        public double calcularValor(int tipoVeiculo, long minutosPermanencia) {
            if (minutosPermanencia < 0) {
            throw new IllegalArgumentException("Tempo não pode ser negativo: " + minutosPermanencia);
            }
            // Calcula horas (arredondando para cima)
            int horas = (int) Math.ceil(minutosPermanencia / 60.0);
            // Garante pelo menos 1 hora
            if (horas == 0) horas = 1;
            if (tipoVeiculo == 1) { // MOTO
                return horas == 1 ? primeiraHoraMoto :
                        primeiraHoraMoto + (horaAdiconalMoto * (horas - 1));
            } else { // CARRO
                return horas == 1 ? primeiraHoraCarro :
                        primeiraHoraCarro + (horaAdiconalCarro * (horas - 1));
            }
        }


        public void registrarPagamento(double valor) {
            this.totalFaturado += valor;
        }

        //Formata o tempo em horas e minutos

        public double getTotalFaturado () {
            return this.totalFaturado;
        }

        // Retorna valor das tarifas
        public static void getTarifas () {
            System.out.printf("Primeira hora Moto: R$ %.2f | Hora adicional: R$ %.2f", primeiraHoraMoto, horaAdiconalMoto);
            System.out.printf("\nPrimeira hora Carro: R$ %.2f | Hora adicional: R$ %.2f", primeiraHoraCarro, horaAdiconalCarro);

        }
}

