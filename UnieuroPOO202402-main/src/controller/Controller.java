package controller;

import java.util.ArrayList;
import java.util.List;
import model.Reserva;

public class Controller {
    private List<Reserva> reservas = new ArrayList<>(); // Lista para armazenar as reservas
    private int proximoId = 1; // Variável para gerar IDs únicos para cada reserva

    // Adicionar uma nova reserva
    public void adicionarReserva(String nomeCliente, String nomeLivro) {
        Reserva novaReserva = new Reserva(proximoId++, nomeCliente, nomeLivro);
        reservas.add(novaReserva);
        System.out.println("Reserva adicionada: " + novaReserva);
    }

    // Alterar uma reserva existente
    public boolean alterarReserva(int id, String novoNomeCliente, String novoNomeLivro) {
        for (Reserva r : reservas) {
            if (r.getId() == id) { // Encontra a reserva pelo ID
                r.setNomeCliente(novoNomeCliente);
                r.setNomeLivro(novoNomeLivro);
                System.out.println("Reserva alterada: " + r);
                return true;
            }
        }
        return false; // Retorna false se a reserva não foi encontrada
    }

    // Excluir uma reserva pelo ID
    public boolean excluirReserva(int id) {
        boolean removida = reservas.removeIf(r -> r.getId() == id);
        if (removida) {
            System.out.println("Reserva com ID " + id + " foi excluída.");
        } else {
            System.out.println("Reserva com ID " + id + " não encontrada.");
        }
        return removida;
    }

    // Listar todas as reservas
    public List<Reserva> listarReservas() {
        return reservas;
    }
}
