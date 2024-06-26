package bookWise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoManager {

    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public static void openEmprestimosWindow() {
        JFrame frame = new JFrame("Empréstimos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> frame.dispose());
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        JButton verEmprestimosButton = new JButton("Ver todos os empréstimos");
        JButton devolverEmprestimoButton = new JButton("Devolver empréstimo");
        JButton registrarEmprestimoButton = new JButton("Registrar empréstimo");
        JButton prolongarEmprestimoButton = new JButton("Prolongar empréstimo");

        verEmprestimosButton.addActionListener(e -> openVerEmprestimosWindow());
        devolverEmprestimoButton.addActionListener(e -> openDevolverEmprestimoWindow());
        registrarEmprestimoButton.addActionListener(e -> openRegistrarEmprestimoWindow());
        prolongarEmprestimoButton.addActionListener(e -> openProlongarEmprestimoWindow());

        mainPanel.add(verEmprestimosButton);
        mainPanel.add(devolverEmprestimoButton);
        mainPanel.add(registrarEmprestimoButton);
        mainPanel.add(prolongarEmprestimoButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void openVerEmprestimosWindow() {
        JFrame verEmprestimosFrame = new JFrame("Consultar Empréstimos");
        verEmprestimosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verEmprestimosFrame.setSize(600, 400);
        verEmprestimosFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> verEmprestimosFrame.dispose());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        verEmprestimosFrame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Título", "SBN", "Número de Sócio", "Data do Empréstimo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (Emprestimo emprestimo : emprestimos) {
            tableModel.addRow(new Object[]{
                    emprestimo.getTitulo(),
                    emprestimo.getSbn(),
                    emprestimo.getNumeroSocio(),
                    emprestimo.getDataEmprestimo()
            });
        }

        verEmprestimosFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        verEmprestimosFrame.setVisible(true);
    }

    private static void openDevolverEmprestimoWindow() {
        JFrame devolverEmprestimoFrame = new JFrame("Devolução de Empréstimo");
        devolverEmprestimoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        devolverEmprestimoFrame.setSize(400, 300);
        devolverEmprestimoFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JTextField numeroSocioField = new JTextField();
        JTextField codigoField = new JTextField();
        JTextField dataDevolucaoField = new JTextField();

        formPanel.add(new JLabel("Número de Sócio:"));
        formPanel.add(numeroSocioField);
        formPanel.add(new JLabel("Código do Livro:"));
        formPanel.add(codigoField);
        formPanel.add(new JLabel("Data de Devolução:"));
        formPanel.add(dataDevolucaoField);

        JButton devolverButton = new JButton("Devolver");
        devolverButton.addActionListener(e -> {
            String numeroSocio = numeroSocioField.getText().trim();
            String codigo = codigoField.getText().trim();
            String dataDevolucao = dataDevolucaoField.getText().trim();

            if (numeroSocio.isEmpty() || codigo.isEmpty() || dataDevolucao.isEmpty()) {
                JOptionPane.showMessageDialog(devolverEmprestimoFrame, "Por favor, preencha todos os campos.");
                return;
            }

            boolean socioExistente = false;
            boolean emprestimoEncontrado = false;

            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getNumeroSocio().equals(numeroSocio)) {
                    socioExistente = true;
                    if (emprestimo.getSbn().equals(codigo)) {
                        emprestimo.setDataDevolucao(dataDevolucao);
                        emprestimoEncontrado = true;
                        JOptionPane.showMessageDialog(devolverEmprestimoFrame, "Devolução concluída!");
                        devolverEmprestimoFrame.dispose();
                        return;
                    }
                }
            }

            if (!socioExistente) {
                JOptionPane.showMessageDialog(devolverEmprestimoFrame, "Número de sócio não encontrado.");
            } else if (!emprestimoEncontrado) {
                JOptionPane.showMessageDialog(devolverEmprestimoFrame, "Código do livro não corresponde ao empréstimo do sócio.");
            }
        });

        devolverEmprestimoFrame.add(formPanel, BorderLayout.CENTER);
        devolverEmprestimoFrame.add(devolverButton, BorderLayout.SOUTH);
        devolverEmprestimoFrame.setVisible(true);
    }

    private static void openRegistrarEmprestimoWindow() {
        JFrame registrarEmprestimoFrame = new JFrame("Registrar Empréstimo");
        registrarEmprestimoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrarEmprestimoFrame.setSize(400, 400);
        registrarEmprestimoFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField numeroSocioField = new JTextField();
        JTextField tituloField = new JTextField();
        JTextField codigoField = new JTextField();
        JTextField dataEmprestimoField = new JTextField();
        JTextField dataEntregaField = new JTextField();

        formPanel.add(new JLabel("Número de Sócio:"));
        formPanel.add(numeroSocioField);
        formPanel.add(new JLabel("Título do Livro:"));
        formPanel.add(tituloField);
        formPanel.add(new JLabel("Código do Livro:"));
        formPanel.add(codigoField);
        formPanel.add(new JLabel("Data do Empréstimo:"));
        formPanel.add(dataEmprestimoField);
        formPanel.add(new JLabel("Data de Entrega:"));
        formPanel.add(dataEntregaField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(e -> {
            String numeroSocio = numeroSocioField.getText().trim();
            String titulo = tituloField.getText().trim();
            String codigo = codigoField.getText().trim();
            String dataEmprestimo = dataEmprestimoField.getText().trim();
            String dataEntrega = dataEntregaField.getText().trim();

            if (numeroSocio.isEmpty() || titulo.isEmpty() || codigo.isEmpty() || dataEmprestimo.isEmpty() || dataEntrega.isEmpty()) {
                JOptionPane.showMessageDialog(registrarEmprestimoFrame, "Por favor, preencha todos os campos.");
                return;
            }

            // Verifica se o número de sócio é válido e se o sócio não tem dívidas pendentes
            boolean socioValido = false;
            for (Socio socio : SocioManager.socios) {
                if (String.valueOf(socio.getNumeroSocio()).equals(numeroSocio)) {
                    socioValido = true;
                    if (socio.getValorMulta() > 0) {
                        JOptionPane.showMessageDialog(registrarEmprestimoFrame, "O sócio tem dívidas pendentes.");
                        return;
                    }
                }
            }
            if (!socioValido) {
                JOptionPane.showMessageDialog(registrarEmprestimoFrame, "Número de sócio inválido.");
                return;
            }

            // Verifica se o livro está disponível
            boolean livroDisponivel = false;
            for (Livro livro : LivrosEAquisicoes.livros) {
                if (livro.getTitulo().equals(titulo) && livro.getSbn().equals(codigo)) {
                    livroDisponivel = true;
                    break;
                }
            }
            if (!livroDisponivel) {
                JOptionPane.showMessageDialog(registrarEmprestimoFrame, "Livro não disponível.");
                return;
            }

            emprestimos.add(new Emprestimo(titulo, codigo, numeroSocio, dataEmprestimo, dataEntrega));
            JOptionPane.showMessageDialog(registrarEmprestimoFrame, "Empréstimo registrado com sucesso!");
            registrarEmprestimoFrame.dispose();
        });

        registrarEmprestimoFrame.add(formPanel, BorderLayout.CENTER);
        registrarEmprestimoFrame.add(registrarButton, BorderLayout.SOUTH);
        registrarEmprestimoFrame.setVisible(true);
    }

    private static void openProlongarEmprestimoWindow() {
        JFrame prolongarEmprestimoFrame = new JFrame("Prolongar Empréstimo");
        prolongarEmprestimoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prolongarEmprestimoFrame.setSize(400, 300);
        prolongarEmprestimoFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JTextField numeroSocioField = new JTextField();
        JTextField codigoField = new JTextField();
        JTextField diasProlongarField = new JTextField();

        formPanel.add(new JLabel("Número de Sócio:"));
        formPanel.add(numeroSocioField);
        formPanel.add(new JLabel("Código do Livro:"));
        formPanel.add(codigoField);
        formPanel.add(new JLabel("Dias para Prolongar:"));
        formPanel.add(diasProlongarField);

        JButton prolongarButton = new JButton("Prolongar");
        prolongarButton.addActionListener(e -> {
            String numeroSocio = numeroSocioField.getText().trim();
            String codigo = codigoField.getText().trim();
            String diasProlongar = diasProlongarField.getText().trim();

            if (numeroSocio.isEmpty() || codigo.isEmpty() || diasProlongar.isEmpty()) {
                JOptionPane.showMessageDialog(prolongarEmprestimoFrame, "Por favor, preencha todos os campos.");
                return;
            }

            boolean socioExistente = false;
            boolean emprestimoEncontrado = false;

            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getNumeroSocio().equals(numeroSocio)) {
                    socioExistente = true;
                    if (emprestimo.getSbn().equals(codigo)) {
                        emprestimo.prolongarEmprestimo(Integer.parseInt(diasProlongar));
                        emprestimoEncontrado = true;
                        JOptionPane.showMessageDialog(prolongarEmprestimoFrame, "Empréstimo prolongado com sucesso!");
                        prolongarEmprestimoFrame.dispose();
                        return;
                    }
                }
            }

            if (!socioExistente) {
                JOptionPane.showMessageDialog(prolongarEmprestimoFrame, "Número de sócio não encontrado.");
            } else if (!emprestimoEncontrado) {
                JOptionPane.showMessageDialog(prolongarEmprestimoFrame, "Código do livro não corresponde ao empréstimo do sócio.");
            }
        });

        prolongarEmprestimoFrame.add(formPanel, BorderLayout.CENTER);
        prolongarEmprestimoFrame.add(prolongarButton, BorderLayout.SOUTH);
        prolongarEmprestimoFrame.setVisible(true);
    }
}
