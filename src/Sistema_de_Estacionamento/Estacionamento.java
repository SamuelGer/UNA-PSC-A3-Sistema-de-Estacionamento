package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;


public class Estacionamento {
    private final int vagas = 15;
    private final ArrayList<Veiculo> veiculos = new ArrayList<>();
    private final ArrayList<Veiculo> fila = new ArrayList<>();
    private final boolean aberto;



    public void registrarEntrada() {
        Scanner input = new Scanner(System.in);
        //Recebendo a placa do veículo
        System.out.print("Placa: ");
            String placa = input.nextLine();
        //Recebendo o tipo do veículo
            Faturamento.getTarifas();
        System.out.print("""
                
                Tipo do Veiculo: \
                
                [1] - Moto\
                
                [2] - Carro\
                
                Escolha:\s""");
            int tipo = input.nextInt();
            input.nextLine();
        //Recebendo a hora da entrada
        LocalDateTime hora = Veiculo.capturarHoraEntrada();
        //Instanciação do objeto
        Veiculo veic = new Veiculo(tipo, placa, hora);

        //Adicionando os veiculos as arrays
            if (veiculos.size() < vagas) {
                veiculos.add(veic);
                System.out.println("Veículo registrado com sucesso! Vagas: " + veiculos.size() + "/" + vagas);
            } else {
                    System.out.println("Estacionamento cheio! Veículo foi movido para a fila de espera.");
                    fila.add(veic); //Se o estacionamento estiver cheio, cai na fila.
            }
    }

    public void registrarSaida(){
        Scanner input = new Scanner(System.in);
        System.out.print("Placa: ");
        String placa = input.nextLine().toLowerCase();
        //Buscar o veiculo pela placa.
        Veiculo veiculo = buscarVeiculo(placa);
            if (veiculo == null) {
                System.out.println("Veículo não encontrado no estacionamento!");
                    return;
            } else{  //remove o veículo do estacionamento e registra saida.
                    veiculos.remove(veiculo);
                    veiculo.registrarHoraSaida();
                    System.out.println("Saída registrada com sucesso!");
            }
            if (!fila.isEmpty()) {
                Veiculo proximo = fila.removeFirst();
                veiculos.add(proximo);
                System.out.println("Veículo da fila (" + proximo.getPlaca() + ") entrou no estacionamento!");
            }
            /*Parte do bloco de código onde o usuário faz o pagamento do estacionamento
            e acumula os valores no atributo totalFaturado */
            Faturamento faturamento = new Faturamento();
            //Calculo do tempo de permanencia do veiuclo.
            long minutosPermanencia = veiculo.calcularPermanencia();
            //Metodo de faturamento onde calcula o valor com a diferença se é moto ou carro
            double valor = faturamento.calcularValor(veiculo.getOpcao(), minutosPermanencia);
            //Adiciona o valor ao totalFaturamento
            faturamento.registrarPagamento(valor);
            //Feedback ao usuario
            System.out.println("Tempo de permanência: "+ minutosPermanencia + " minutos");
            System.out.println("Valor pago: R$ "+ String.format("%.2f", valor));
    }

    public void quantidadeVagas(){
        System.out.println("Total: " + veiculos.size() + "/" + vagas +" veículo(s)"); // Saída vagas/15
    }

    public void mostrarVeiculos(){
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo no estacionamento.");
            return;
        }
        System.out.println("=== VEÍCULOS NO ESTACIONAMENTO ===");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.println((i + 1) + ". Placa: " + v.getPlaca() +
                    " - Tipo: " + (v.getOpcao() == 1 ? "Moto" : "Carro"));
        }          //A opção é 1 ? Se SIM, resposta é "Moto" : Senão, resposta é "Carro" (if --> ? else --> : .)
    }

    //Método auxiliar para a busca de veiculos no método mostrarVeiculo
    private Veiculo buscarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null; //Se não encontrar o veículo retorna nulo para o if do registrarSaída
    }


    public void buscarVeiculoPorPlaca() {
        Scanner input = new Scanner(System.in);
        System.out.print("Placa: ");
        String placa1 = input.nextLine().toLowerCase();

        Veiculo vc = buscarVeiculo(placa1);
        //Se não há veículos no estacionamento, imprimirá a mensagem ao usuário.
            if (veiculos.isEmpty()) {
                System.out.println("Este veículo não esta no estacionamento. O estacionamento está vazio.");
            }
            //Busca o veiculo pela placa.
            for (Veiculo veiculo : veiculos) {
                if (vc.getPlaca().equalsIgnoreCase(placa1)) {
                    System.out.println("Veículo presente no estacionamento! \nPlaca: " + placa1);
                    System.out.print("Hora da entrada: ");
                    System.out.println(veiculo.getHoraEntrada());
                } else {
                    System.out.println("Veiculo não encontrado com a placa: " + placa1 + "\n Portanto, ele não está no estacionamento!");
                }
            }
    }

    public void relatorioFaturamento(){
        System.out.println("Total arrecadado no dia: " +
                            "\n ------");
        Faturamento faturamento = new Faturamento();
        double totalFaturado = faturamento.getTotalFaturado();
        System.out.printf("Total faturado no dia: R$ %.2f", totalFaturado);

    }





    //Metodos Especiais
    public Estacionamento() {
        aberto = true;
    }

    public boolean isAberto() {
        return aberto;
    }
}
