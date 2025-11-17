package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.Duration;

public class Veiculo {
    private String tipo;
    private static int opcao;
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

    //Construtor
    public Veiculo(int tipo, String placa,LocalDateTime hora) {
        this.tipo = selecionarTipo();
        this.placa = placa;
        this.horaEntrada = hora;

    }

    public static LocalDateTime capturarHora() {
    Scanner ler = new Scanner(System.in);
        //Central usada para dar as duas opções de captura a entrada do veiculo.
        System.out.println("Como você quer registrar a hora de entrada? ");
        System.out.println("(1) Informar a hora Manual: ");
        System.out.println("(2) Usar a hora atual: ");
        System.out.print("Opção: ");

        int opcao = ler.nextInt();
        ler.nextLine(); // Limpar Entrada

        switch (opcao) {
            case 1:
                return capturarHoraManual(ler);
            default:
                return capturarHoraAutomatica();
        }
    }

    public static LocalDateTime capturarHoraManual(Scanner Horaentrada) {

        //Bloco usado para capturar a hora INFORMADA PELO USUÁRIO.

        try {
            System.out.println("Digite a data e hora de entrada(Formato: dia/mês/ano e Horas:Minutos): ");
            String Hora_de_entrada= Horaentrada.nextLine();

            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(Hora_de_entrada, formatter);

        } catch (Exception e) {
            System.out.println("Formato Inválido! Usando hora atual. ");
            return LocalDateTime.now();
        }

    }

    public static LocalDateTime capturarHoraAutomatica() {

        //Bloco usado para usar a entrada na horário atual.
        System.out.println("------CAPTURADO A HORA REAL------");
        LocalDateTime horaAtual = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Hora de entrada registrada automaticamente: " + formatter.format(horaAtual));
        return horaAtual;
    }

    public void registrarHoraSaida(){
        this.horaSaida = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Hora da Saída: " + formatter.format(horaSaida));
    }

    public String getPlaca() {
        return placa;
    }

    public String getHoraEntrada() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        //System.out.println("Hora de entrada registrada automaticamente: " + formatter.format(horaEntrada));
        return formatter.format(horaEntrada);
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public  static int getOpcao() {
        return opcao;
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
