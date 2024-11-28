package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Reserva;

public class TelaReserva extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNomeCliente;
    private JTextField txtNomeLivro;
    private JTable table;
    private Controller reservaController;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaReserva frame = new TelaReserva();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaReserva() {
        reservaController = new Controller();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        setTitle("Sistema de Reservas");

        // Painel principal com cor de fundo
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(200, 220, 240)); // Cor de fundo azul clara
        setContentPane(contentPane);

        // Título estilizado
        JLabel lblTitulo = new JLabel("Gestão de Reservas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 10, 560, 40);
        lblTitulo.setForeground(new Color(0, 51, 102)); // Cor de texto azul escura
        contentPane.add(lblTitulo);

        // Campo Nome do Cliente
        JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
        lblNomeCliente.setBounds(20, 60, 120, 25);
        contentPane.add(lblNomeCliente);

        txtNomeCliente = new JTextField();
        txtNomeCliente.setBounds(140, 60, 200, 25);
        txtNomeCliente.setBackground(new Color(255, 255, 204)); // Fundo amarelo claro
        contentPane.add(txtNomeCliente);

        // Campo Nome do Livro
        JLabel lblNomeLivro = new JLabel("Nome do Livro:");
        lblNomeLivro.setBounds(20, 100, 120, 25);
        contentPane.add(lblNomeLivro);

        txtNomeLivro = new JTextField();
        txtNomeLivro.setBounds(140, 100, 200, 25);
        txtNomeLivro.setBackground(new Color(255, 255, 204)); // Fundo amarelo claro
        contentPane.add(txtNomeLivro);

        // Botão Adicionar
        JButton btnAdicionar = new JButton("Adicionar Reserva");
        btnAdicionar.setBounds(20, 140, 160, 30);
        btnAdicionar.setBackground(new Color(153, 204, 255)); // Fundo azul claro
        btnAdicionar.setForeground(Color.BLACK); // Texto preto
        contentPane.add(btnAdicionar);

        // Botão Excluir
        JButton btnExcluir = new JButton("Excluir Reserva");
        btnExcluir.setBounds(200, 140, 140, 30);
        btnExcluir.setBackground(new Color(255, 153, 153)); // Fundo vermelho claro
        btnExcluir.setForeground(Color.BLACK); // Texto preto
        contentPane.add(btnExcluir);

        // Tabela
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nome do Cliente", "Nome do Livro" }
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 200, 540, 200);
        contentPane.add(scrollPane);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> {
            String nomeCliente = txtNomeCliente.getText();
            String nomeLivro = txtNomeLivro.getText();

            if (!nomeCliente.isEmpty() && !nomeLivro.isEmpty()) {
                reservaController.adicionarReserva(nomeCliente, nomeLivro);
                txtNomeCliente.setText("");
                txtNomeLivro.setText("");
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                if (reservaController.excluirReserva(id)) {
                    atualizarTabela();
                    JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma reserva para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela

        for (Reserva r : reservaController.listarReservas()) {
            model.addRow(new Object[] { r.getId(), r.getNomeCliente(), r.getNomeLivro() });
        }
    }
}