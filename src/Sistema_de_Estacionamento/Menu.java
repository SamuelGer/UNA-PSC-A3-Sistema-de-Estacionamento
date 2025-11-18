package Sistema_de_Estacionamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int escolha = 0;
    private Scanner ler;
    private Estacionamento estacionamento;
    private boolean executando;


    //Construtor
        public Menu() {
        this.ler = new Scanner(System.in);
        this.estacionamento = new Estacionamento();
        this.executando = true;
        }

        public void openMenu() {
            Scanner ler = new Scanner(System.in);
            Estacionamento estacionamento = new Estacionamento();
            if(estacionamento.isAberto()){
                do {
                    System.out.println();
                    System.out.println("-------ESTACIONAMENTO-------");
                    System.out.println("[1] - Registrar Entrada de um Veículo"); //case 1
                    System.out.println("[2] - Registrar Saída de um Veículo"); //case 2
                    System.out.println("[3] - Quantidade de vagas disponíveis"); //case 3
                    System.out.println("[4] - Mostrar Veículos no Estacionamento"); //case 4
                    System.out.println("[5] - Pesquisar veículo por placa"); //case 5
                    System.out.println("[6] - Relatório de faturamento"); //case 6
                    System.out.println("[7] - Sair"); //case 7
                    System.out.print("Escolha a opção desejada: ");
                    escolha = lerOpcao();
                    //Implementar as funções de cada case
                        switch (escolha) {
                            case 1:
                                Clear();
                            estacionamento.registrarEntrada();
                                Clear();
                                break;
                            case 2:
                                Clear();
                            estacionamento.registrarSaida();
                                break;
                            case 3:
                                Clear();
                            estacionamento.quantidadeVagas();
                                break;
                            case 4:
                                Clear();
                            estacionamento.mostrarVeiculos();
                                break;
                            case 5:
                                Clear();
                            estacionamento.buscarVeiculoPorPlaca();
                                break;
                            case 6:
                            estacionamento.relatorioFaturamento();
                                break;
                            case 7:
                                System.out.println("Encerrando...");
                                closeMenu();
                                break;
                            default:
                                System.out.println("Opção inválida! ");
                        }

                }while (escolha > 0 && escolha <= 7);
                ler.close();
            }
        }

        //Tratamento de exceções
        private int lerOpcao() {
            try {
            return ler.nextInt();
            } catch (InputMismatchException err) {
            ler.nextLine(); // Limpar buffer
            return -1; // Valor inválido
            }
        }

        public void closeMenu() {
        escolha = 8;
        }


        //Método para organizar/limpar o console
        public static void Clear() {
            try {
                // Tenta sequências ANSI
                System.out.print("\033[H\033[2J");
                System.out.flush();

                // Espera um pouco
                Thread.sleep(50);

                // Imprime linha em branco como backup
                for (int i = 0; i < 2; i++) {
                    System.out.println();
                }
            } catch (Exception ex) {
                // Último fallback
                for (int i = 0; i < 2; i++) {
                    System.out.println();
                }
            }
              
        }     
    }

