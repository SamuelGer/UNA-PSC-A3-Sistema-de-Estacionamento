package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Duration;

public class Veiculo {
    private String tipo;
    private int opcao;
    private String placa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    //Construtor
    public Veiculo(int opcao, String placa,LocalDateTime hora) {
        this.opcao = opcao;
        this.tipo = selecionarTipo();
        this.placa = placa;
        this.horaEntrada = hora;

    }

    public String selecionarTipo(){
        //Bloco usado para escolher se o veículo é carro ou moto
        switch (opcao){
            case 1:
                this.tipo = "Moto";
                break;
            case 2:
                this.tipo = "Carro";
                break;
            default:
                System.out.println("Digite somente 1 ou 2.");
        }
        return tipo;
    }

    //ENTRADAS ---------------------------------------------------------------------------------------
    public static LocalDateTime capturarHoraEntrada() {
    Scanner ler = new Scanner(System.in);
        //Central usada para dar as duas opções de captura a entrada do veiculo.
        System.out.println("Registre a hora de entrada: ");
        System.out.println("(1) Informar a hora Manualmente: ");
        System.out.println("(2) Usar a hora atual: ");
        System.out.print("Opção: ");

        int opcao1 = ler.nextInt();
        ler.nextLine(); // Limpar Entrada

        switch (opcao1) {
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
            System.out.println("Formato Inválido! Usando a sua hora atual. ");
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
    //----------------------------------------------------------------------------------------------


    //SAIDAS ---------------------------------------------------------------------------------------
    public LocalDateTime registrarHoraSaida(){
        Scanner ler = new Scanner(System.in);
        //Central usada para dar as duas opções de captura a saida do veiculo.
        System.out.println("Registre a hora de saída. ");
        System.out.println("(1) Informar a hora Manualmente: ");
        System.out.println("(2) Usar a hora atual: ");
        System.out.print("Opção: ");

        int opcao1 = ler.nextInt();
        ler.nextLine(); // Limpar Entrada

        switch (opcao1) {
            case 1:
                return registrarHoraSaidaManual(ler);
            default:
                return registrarHoraSaidaAutomatica();
        }
    }

    public LocalDateTime registrarHoraSaidaManual(Scanner Horasaida) {
        //Bloco usado para capturar a hora INFORMADA PELO USUÁRIO.
        try {
            System.out.println("Digite a data e hora de saída(Formato: dia/mês/ano e Horas:Minutos): ");
            String Hora_de_saida= Horasaida.nextLine();

            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return horaSaida = LocalDateTime.parse(Hora_de_saida, formatter);

        } catch (Exception e) {
            System.out.println("Formato Inválido! Usando a sua hora atual. ");
            return LocalDateTime.now();
        }

    }


    public LocalDateTime registrarHoraSaidaAutomatica(){
        horaSaida = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Hora da Saída: " + formatter.format(horaSaida));
        return null;
    }
    //----------------------------------------------------------------------------------------------


    public String getPlaca() {
        return placa;
    }

    public String getHoraEntrada() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return formatter.format(horaEntrada);
    }

    public int getOpcao() {
            return this.opcao;
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
