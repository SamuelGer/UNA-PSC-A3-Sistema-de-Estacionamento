package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.Duration;

public class Veiculo {
    private String tipo;
    private int opcao;
    private String placa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public String selecionarTipo(){

        //Bloco usado para escolher se o veículo é carro ou moto
        switch (opcao){
            case 1:
                this.tipo = "Moto";
                break;
            case 2:
                this.tipo = "Carro";
                break;
        }
        return tipo;
    }

    public Veiculo(String tipo, String placa,LocalDateTime hora) {
        this.tipo = selecionarTipo();
        this.placa = placa;
        this.horaEntrada = hora;

    }

    public LocalDateTime capturarHora(Scanner Horaentrada) {

        //Central usada para dar as duas opções de captura a entrada do veiculo.
        System.out.println("Como você quer registrar a hora de entrada? ");
        System.out.println("(1) Informar a hora Manual: ");
        System.out.println("(2) Usar a hora atual: ");
        System.out.println("Opção: ");

        int opcao = Horaentrada.nextInt();
        Horaentrada.nextLine(); // Limpar Entrada

        switch (opcao) {
            case 1:
                return capturarHoraManual(Horaentrada);
            default:
                return capturarHoraAutomatica();
        }
    }
    
    public LocalDateTime capturarHoraManual(Scanner Horaentrada) {
        
        //Bloco usado para capturar a hora informada pelo usuario.
        try {
            System.out.println("Digite a data e hora de entrada(Formato: dia/mês/ano e Horas:Minutos): ");
            String Hora_de_entrada = Hora_de_entrada.nextLine();
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(Hora_de_entrada, formatter);

        } catch (Exception e) {
            System.out.println("Formato Inválido! Usando hora atual. ");
            return LocalDateTime.now();
        }

    }

    public LocalDateTime capturarHoraAutomatica() {
        
        //Bloco usado para usar a entrada na horário atual.
        System.out.println("--CAPTURANDO HORA REAL");
        LocalDateTime horaAtual = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Hora de entrada registrada automaticamente: " + horaAtual);
        return horaAtual;
    }

    public void registrarHoraSaida(){
        this.horaSaida = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public long calcularPermanencia(){
        if (horaEntrada == null || horaSaida == null) {
            System.out.println("Hora de entrada ou saída não registrada!");
            return 0;
        }
        Duration d = Duration.between(horaEntrada, horaSaida);
        return d.toMinutes();
    }


}
