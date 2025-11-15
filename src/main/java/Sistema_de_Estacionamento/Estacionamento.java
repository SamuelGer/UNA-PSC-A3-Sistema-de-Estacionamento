package Sistema_de_Estacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.ArrayList;

public class Estacionamento {
    private int vagas = 15;
    private int vagasUsadas = 0;
    private ArrayList<Veiculo>  veiculos = new ArrayList<>();
    private ArrayList<Veiculo> fila = new ArrayList<>();
    private boolean aberto = false;






















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
