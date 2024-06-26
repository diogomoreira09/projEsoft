package bookWise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LivrosEAquisicoes {
    public static List<Livro> livros = new ArrayList<>();
    private static JFrame frame;
    private static JTable table;
    private static DefaultTableModel tableModel;

    public static void openLivrosEAquisicoesWindow() {
        frame = new JFrame("Livros e Aquisições");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> frame.dispose());
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JButton registarAquisicaoButton = new JButton("Registar Aquisição");
        JButton verLivrosButton = new JButton("Ver Livros e Aquisições");
        JButton verFornecedores = new JButton("Ver Fornecedores");

        registarAquisicaoButton.addActionListener(e -> openRegistarAquisicaoWindow());
        verLivrosButton.addActionListener(e -> openVerLivrosWindow());
        verFornecedores.addActionListener(e -> openVerFornecedoresWindow());

        mainPanel.add(registarAquisicaoButton);
        mainPanel.add(verLivrosButton);
        mainPanel.add(verFornecedores);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void openRegistarAquisicaoWindow() {
        JFrame registarFrame = new JFrame("Registar Aquisição");
        registarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registarFrame.setSize(400, 400);
        registarFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JTextField tituloField = new JTextField();
        JTextField dataAquisicaoField = new JTextField();
        JTextField fornecedorField = new JTextField();
        JTextField editoraField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField sbnField = new JTextField();

        formPanel.add(new JLabel("Título:"));
        formPanel.add(tituloField);
        formPanel.add(new JLabel("Data de Aquisição:"));
        formPanel.add(dataAquisicaoField);
        formPanel.add(new JLabel("Fornecedor:"));
        formPanel.add(fornecedorField);
        formPanel.add(new JLabel("Editora:"));
        formPanel.add(editoraField);
        formPanel.add(new JLabel("Preço:"));
        formPanel.add(precoField);
        formPanel.add(new JLabel("Gênero:"));
        formPanel.add(generoField);
        formPanel.add(new JLabel("SBN:"));
        formPanel.add(sbnField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            String titulo = tituloField.getText();
            String dataAquisicao = dataAquisicaoField.getText();
            String fornecedor = fornecedorField.getText();
            String editora = editoraField.getText();
            double preco;
            try {
                preco = Double.parseDouble(precoField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(registarFrame, "Preço inválido.");
                return;
            }
            String genero = generoField.getText();
            String sbn = sbnField.getText();

            if (titulo.isEmpty() || dataAquisicao.isEmpty() || fornecedor.isEmpty() || editora.isEmpty() || genero.isEmpty() || sbn.isEmpty()) {
                JOptionPane.showMessageDialog(registarFrame, "Por favor, preencha todos os campos.");
            } else {
                Livro livro = new Livro(titulo, dataAquisicao, fornecedor, editora, preco, genero, sbn);
                livros.add(livro);
                JOptionPane.showMessageDialog(registarFrame, "Aquisição registrada com sucesso!");
                registarFrame.dispose();
            }
        });

        formPanel.add(new JLabel());
        formPanel.add(salvarButton);

        registarFrame.add(formPanel, BorderLayout.CENTER);
        registarFrame.setVisible(true);
    }

    private static void openVerLivrosWindow() {
        JFrame verLivrosFrame = new JFrame("Livros e Aquisições");
        verLivrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verLivrosFrame.setSize(600, 400);
        verLivrosFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> verLivrosFrame.dispose());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        verLivrosFrame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Título", "Data de Aquisição", "Fornecedor", "Editora", "Preço", "Gênero", "SBN"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        for (Livro livro : livros) {
            tableModel.addRow(new Object[]{
                    livro.getTitulo(),
                    livro.getDataAquisicao(),
                    livro.getFornecedor(),
                    livro.getEditora(),
                    livro.getPreco(),
                    livro.getGenero(),
                    livro.getSbn()
            });
        }

        verLivrosFrame.add(new JScrollPane(table), BorderLayout.CENTER);

        verLivrosFrame.setVisible(true);
    }

    private static void openVerFornecedoresWindow() {
        JFrame verFornecedoresFrame = new JFrame("Fornecedores");
        verFornecedoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verFornecedoresFrame.setSize(600, 400);
        verFornecedoresFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> verFornecedoresFrame.dispose());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        verFornecedoresFrame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Fornecedor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        for (Livro livro : livros) {
            tableModel.addRow(new Object[]{
                    livro.getFornecedor()
            });
        }

        verFornecedoresFrame.add(new JScrollPane(table), BorderLayout.CENTER);

        verFornecedoresFrame.setVisible(true);
    }
}
