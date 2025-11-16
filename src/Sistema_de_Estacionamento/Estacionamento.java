package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Estacionamento {
    private int vagas = 15;
    private int vagasUsadas = 0;
    private ArrayList<Veiculo>  veiculos = new ArrayList<>();
    private ArrayList<Veiculo> fila = new ArrayList<>();
    private boolean aberto = false;


    public void registrarEntrada() {
        Scanner input = new Scanner(System.in);
        System.out.print("Placa: ");
        String placa = input.nextLine();
        System.out.println("Tipo do Veiculo: " +
                "\n[1] - Moto" +
                "\n[2] - Carro");
        int tipo = input.nextInt();
        input.nextLine();

        System.out.println("Hora de entrada: ");
        LocalDateTime hora = Veiculo.capturarHora();
        Veiculo veic = new Veiculo(tipo, placa, hora);

        //Adicionando os veiculos as arrays.
        if (veiculos.size() < 15) {
            veiculos.add(veic);
            System.out.println("Veículo registrado com sucesso! Vaga: " + veiculos.size() + "/15");
        } else {
            System.out.println("Estacionamento cheio! Veículo adicionado na fila de espera."); //Se o estacionamento estiver cheio, cai na fila.
            fila.add(veic);
        }
    }

    public void registrarSaida(){
        Scanner input = new Scanner(System.in);
        System.out.print("Placa: ");
        String placa = input.nextLine();
        //Buscar o veiculo pela placa.
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado no estacionamento!");
            return;
        }else{  //remove o veículo do estacionamento e registra saida.
            veiculos.remove(veiculo);
            veiculo.registrarHoraSaida();
            System.out.println("Saída registrada com sucesso!");
        }
        if (!fila.isEmpty()) {
            Veiculo proximo = fila.remove(0);
            veiculos.add(proximo);
            System.out.println("Veículo da fila (" + proximo.getPlaca() + ") entrou no estacionamento!");
        }
    }

    public void quantidadeVagas(){
        System.out.println("Total: " + veiculos.size() + "/15 veículo(s)");
    }

    public void mostrarVeiculos(){
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo no estacionamento.");
            return;
        }
        System.out.println("\n=== VEÍCULOS NO ESTACIONAMENTO ===");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.println((i + 1) + ". " + v.getPlaca() +
                    " - " + (v.getOpcao() == 1 ? "Moto" : "Carro"));
        }
    }

    private Veiculo buscarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null; //Se não encontrar o veículo retorna nulo para o if do registrar saída
    }


    public void buscarVeiculoPorPlaca(String placa) {
        Scanner input = new Scanner(System.in);
        System.out.print("Placa: ");
        String placa1 = input.nextLine();
        //Buscar o veiculo pela placa.
        Veiculo veicu = buscarVeiculo(placa1);
        for (Veiculo veiculo : veiculos) {
            if (veicu.getPlaca().equalsIgnoreCase(placa1)) {
                System.out.println("Veículo presente no estacionamento! " + placa1);
                System.out.println("A Hora de Entrada é: " + veiculo.getHoraEntrada());
            } else {
                System.out.println("Veiculo não encontrado com a placa: " + placa1);
            }
        }
    }

    public void relatorioFaturamento(){


    }





    //Metodos Especiais

    public Estacionamento() {
        aberto = true;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public int getVagasUsadas() {
        return vagasUsadas;
    }

    public void setVagasUsadas(int vagasUsadas) {
        this.vagasUsadas = vagasUsadas;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}
