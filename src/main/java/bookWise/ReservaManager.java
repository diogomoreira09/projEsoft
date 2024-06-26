package bookWise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaManager {

    private static List<Reserva> reservas = new ArrayList<>();

    public static void openReservasWindow() {
        JFrame frame = new JFrame("Reservas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> frame.dispose());
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JButton realizarReservaButton = new JButton("Realizar reserva");
        JButton anularReservaButton = new JButton("Anular reserva");
        JButton verTodasReservasButton = new JButton("Ver todas as reservas");

        realizarReservaButton.addActionListener(e -> openRealizarReservaWindow());
        anularReservaButton.addActionListener(e -> openAnularReservaWindow());
        verTodasReservasButton.addActionListener(e -> openVerTodasReservasWindow());

        mainPanel.add(realizarReservaButton);
        mainPanel.add(anularReservaButton);
        mainPanel.add(verTodasReservasButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void openRealizarReservaWindow() {
        JFrame realizarReservaFrame = new JFrame("Realizar Reserva");
        realizarReservaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        realizarReservaFrame.setSize(400, 300);
        realizarReservaFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JTextField numeroSocioField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField tituloField = new JTextField();

        formPanel.add(new JLabel("Número de Sócio:"));
        formPanel.add(numeroSocioField);
        formPanel.add(new JLabel("Autor do Livro:"));
        formPanel.add(autorField);
        formPanel.add(new JLabel("Título do Livro:"));
        formPanel.add(tituloField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            String numeroSocio = numeroSocioField.getText().trim();
            String autor = autorField.getText().trim();
            String titulo = tituloField.getText().trim();

            if (numeroSocio.isEmpty() || autor.isEmpty() || titulo.isEmpty()) {
                JOptionPane.showMessageDialog(realizarReservaFrame, "Por favor, preencha todos os campos.");
                return;
            }

            // Verifica se o número de sócio é válido e se o sócio não tem multas pendentes
            boolean socioValido = false;
            for (Socio socio : SocioManager.socios) {
                if (String.valueOf(socio.getNumeroSocio()).equals(numeroSocio)) {
                    socioValido = true;
                    if (socio.getValorMulta() > 0) {
                        JOptionPane.showMessageDialog(realizarReservaFrame, "O sócio tem dívidas pendentes.");
                        return;
                    }
                }
            }
            if (!socioValido) {
                JOptionPane.showMessageDialog(realizarReservaFrame, "Número de sócio inválido.");
                return;
            }

            // Adiciona a reserva à lista de reservas
            reservas.add(new Reserva(numeroSocio, autor, titulo));
            JOptionPane.showMessageDialog(realizarReservaFrame, "Reserva realizada com sucesso!");
            realizarReservaFrame.dispose();
        });

        realizarReservaFrame.add(formPanel, BorderLayout.CENTER);
        realizarReservaFrame.add(salvarButton, BorderLayout.SOUTH);
        realizarReservaFrame.setVisible(true);
    }

    private static void openAnularReservaWindow() {
        JFrame anularReservaFrame = new JFrame("Anular Reserva");
        anularReservaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        anularReservaFrame.setSize(400, 300);
        anularReservaFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        JTextField autorField = new JTextField();
        JTextField tituloField = new JTextField();

        formPanel.add(new JLabel("Autor do Livro:"));
        formPanel.add(autorField);
        formPanel.add(new JLabel("Título do Livro:"));
        formPanel.add(tituloField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            String autor = autorField.getText().trim();
            String titulo = tituloField.getText().trim();

            if (autor.isEmpty() || titulo.isEmpty()) {
                JOptionPane.showMessageDialog(anularReservaFrame, "Por favor, preencha todos os campos.");
                return;
            }

            boolean reservaEncontrada = false;
            for (Reserva reserva : reservas) {
                if (reserva.getAutor().equals(autor) && reserva.getTitulo().equals(titulo)) {
                    reservas.remove(reserva);
                    reservaEncontrada = true;
                    JOptionPane.showMessageDialog(anularReservaFrame, "Reserva anulada com sucesso!");
                    anularReservaFrame.dispose();
                    break;
                }
            }

            if (!reservaEncontrada) {
                JOptionPane.showMessageDialog(anularReservaFrame, "Nenhuma reserva encontrada com o autor e título fornecidos.");
            }
        });

        anularReservaFrame.add(formPanel, BorderLayout.CENTER);
        anularReservaFrame.add(salvarButton, BorderLayout.SOUTH);
        anularReservaFrame.setVisible(true);
    }

    private static void openVerTodasReservasWindow() {
        JFrame verTodasReservasFrame = new JFrame("Ver Todas as Reservas");
        verTodasReservasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verTodasReservasFrame.setSize(600, 400);
        verTodasReservasFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> verTodasReservasFrame.dispose());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        verTodasReservasFrame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Nome", "Número de Sócio", "Título", "Autor", "SBN"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (Reserva reserva : reservas) {
            // Simular o nome do sócio como "Nome Sócio" + número de sócio
            String nomeSocio = "Nome Sócio " + reserva.getNumeroSocio();
            tableModel.addRow(new Object[]{
                    nomeSocio,
                    reserva.getNumeroSocio(),
                    reserva.getTitulo(),
                    reserva.getAutor(),
                    "SBN123" // Simulação do SBN
            });
        }

        verTodasReservasFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        verTodasReservasFrame.setVisible(true);
    }
}
