package bookWise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PesquisasEStatisticas {

    public static void openPesquisasWindow() {
        JFrame frame = new JFrame("Pesquisas e Estatísticas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> frame.dispose());
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JButton pesquisaButton = new JButton("Pesquisa");
        JButton estatisticasButton = new JButton("Estatísticas");

        pesquisaButton.addActionListener(e -> openPesquisaWindow());
        estatisticasButton.addActionListener(e -> openEstatisticasWindow());

        mainPanel.add(pesquisaButton);
        mainPanel.add(estatisticasButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void openEstatisticasWindow(){
        JFrame estatisticasFrame = new JFrame("Estatísticas");
        estatisticasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estatisticasFrame.setSize(400, 400);
        estatisticasFrame.setLayout(new GridLayout(2, 2));


        estatisticasFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2));

        JButton geralButton = new JButton("Geral");
        JButton autorButton = new JButton("Autor");
        JButton editoraButton = new JButton("Editora");
        JButton generoButton = new JButton("Genero");

        geralButton.addActionListener(e -> openGeralWindow());
        autorButton.addActionListener(e -> openAutorWindow());
        editoraButton.addActionListener(e -> openEditoraWindow());
        generoButton.addActionListener(e -> openGeneroWindow());

        formPanel.add(new JLabel());
        formPanel.add(geralButton);
        formPanel.add(autorButton);
        formPanel.add(editoraButton);
        formPanel.add(generoButton);

        estatisticasFrame.add(formPanel, BorderLayout.CENTER);
        estatisticasFrame.setVisible(true);
    }


    private static void openPesquisaWindow() {
        JFrame pesquisaFrame = new JFrame("Pesquisa");
        pesquisaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pesquisaFrame.setSize(400, 400);
        pesquisaFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 1));
        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField generoField = new JTextField();
        JTextField editoraField = new JTextField();

        formPanel.add(new JLabel("Título:"));
        formPanel.add(tituloField);
        formPanel.add(new JLabel("Autor:"));
        formPanel.add(autorField);
        formPanel.add(new JLabel("Gênero:"));
        formPanel.add(generoField);
        formPanel.add(new JLabel("Editora:"));
        formPanel.add(editoraField);

        JButton pesquisarButton = new JButton("Pesquisar");
        pesquisarButton.addActionListener(e -> {
            String titulo = tituloField.getText().trim();
            String autor = autorField.getText().trim();
            String genero = generoField.getText().trim();
            String editora = editoraField.getText().trim();

            if (titulo.isEmpty() && autor.isEmpty() && genero.isEmpty() && editora.isEmpty()) {
                JOptionPane.showMessageDialog(pesquisaFrame, "Por favor, preencha pelo menos um campo.");
            } else {
                openPesquisaLivrosWindow(titulo, autor, genero, editora);
            }
        });

        formPanel.add(new JLabel());
        formPanel.add(pesquisarButton);

        pesquisaFrame.add(formPanel, BorderLayout.CENTER);
        pesquisaFrame.setVisible(true);
    }

    private static void openPesquisaLivrosWindow(String titulo, String autor, String genero, String editora) {
        JFrame resultadoFrame = new JFrame("Pesquisa Livros");
        resultadoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultadoFrame.setSize(600, 400);
        resultadoFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> resultadoFrame.dispose());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        resultadoFrame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Título", "Data de Aquisição", "Fornecedor", "Editora", "Preço", "Gênero", "SBN"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        List<Livro> livrosFiltrados = LivrosEAquisicoes.livros.stream()
                .filter(livro -> (titulo.isEmpty() || livro.getTitulo().equalsIgnoreCase(titulo)) &&
                        (autor.isEmpty() || livro.getFornecedor().equalsIgnoreCase(autor)) &&
                        (genero.isEmpty() || livro.getGenero().equalsIgnoreCase(genero)) &&
                        (editora.isEmpty() || livro.getEditora().equalsIgnoreCase(editora)))
                .collect(Collectors.toList());

        for (Livro livro : livrosFiltrados) {
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

        resultadoFrame.add(new JScrollPane(table), BorderLayout.CENTER);

        resultadoFrame.setVisible(true);
    }

    private static void openGeralWindow(){
        JFrame geralFrame = new JFrame("Estatísticas Gerais");
        geralFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        geralFrame.setSize(400, 400);
        geralFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> geralFrame.dispose());
        topPanel.add(backButton);

        geralFrame.add(topPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Estatísticas Gerais", SwingConstants.CENTER);
        geralFrame.add(label, BorderLayout.CENTER);

        geralFrame.setVisible(true);
    }

    private static void openAutorWindow(){
        JFrame autorFrame = new JFrame("Estatísticas por Autor");
        autorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        autorFrame.setSize(400, 400);
        autorFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> autorFrame.dispose());
        topPanel.add(backButton);

        autorFrame.add(topPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Estatísticas por Autor", SwingConstants.CENTER);
        autorFrame.add(label, BorderLayout.CENTER);

        autorFrame.setVisible(true);
    }

    private static void openEditoraWindow(){
        JFrame editoraFrame = new JFrame("Estatísticas por Editora");
        editoraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editoraFrame.setSize(400, 400);
        editoraFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> editoraFrame.dispose());
        topPanel.add(backButton);

        editoraFrame.add(topPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Estatísticas por Editora", SwingConstants.CENTER);
        editoraFrame.add(label, BorderLayout.CENTER);

        editoraFrame.setVisible(true);
    }

    private static void openGeneroWindow(){
        JFrame generoFrame = new JFrame("Estatísticas por Gênero");
        generoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        generoFrame.setSize(400, 400);
        generoFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> generoFrame.dispose());
        topPanel.add(backButton);

        generoFrame.add(topPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Estatísticas por Gênero", SwingConstants.CENTER);
        generoFrame.add(label, BorderLayout.CENTER);

        generoFrame.setVisible(true);
    }


}
