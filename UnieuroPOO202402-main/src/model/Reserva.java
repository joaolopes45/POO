package model;

public class Reserva {
    private int id; // Identificador único da reserva
    private String nomeCliente; // Nome do cliente que fez a reserva
    private String nomeLivro;   // Nome do livro reservado

    // Construtor
    public Reserva(int id, String nomeCliente, String nomeLivro) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeLivro = nomeLivro;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    @Override
    public String toString() {
        return "Reserva [ID=" + id + ", Cliente=" + nomeCliente + ", Livro=" + nomeLivro + "]";
    }
}
