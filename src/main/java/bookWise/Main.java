package bookWise;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static JFrame mainFrame;
    private static List<Socio> socios = new ArrayList<>();

    public static void main(String[] args) {
        // Criação da janela principal
        mainFrame = new JFrame("BookWise");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 2));

        // Criação dos botões
        JButton emprestimosButton = new JButton("Empréstimos");
        JButton sociosButton = new JButton("Sócios");
        JButton pesquisasButton = new JButton("Pesquisas e Estatísticas");
        JButton livrosButton = new JButton("Livros e Aquisições");
        JButton reservasButton = new JButton("Reservas");
        JButton pagamentosButton = new JButton("Pagamentos e Multas");


        // Adição dos botões à janela principal
        mainFrame.add(emprestimosButton);
        mainFrame.add(livrosButton);
        mainFrame.add(sociosButton);
        mainFrame.add(reservasButton);
        mainFrame.add(pesquisasButton);
        mainFrame.add(pagamentosButton);

        // Ações para os botões
        emprestimosButton.addActionListener(e -> EmprestimoManager.openEmprestimosWindow());
        sociosButton.addActionListener(e -> SocioManager.openSociosWindow());
        pesquisasButton.addActionListener(e -> PesquisasEStatisticas.openPesquisasWindow());
        livrosButton.addActionListener(e -> LivrosEAquisicoes.openLivrosEAquisicoesWindow());
        reservasButton.addActionListener(e -> ReservaManager.openReservasWindow());
        pagamentosButton.addActionListener(e -> PagamentosEMultas.openPagamentosWindow());

        // Tornar a janela visível
        mainFrame.setVisible(true);
    }

    private static void openNewWindow(String title) {
        JFrame newFrame = new JFrame(title);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(300, 200);
        newFrame.setLayout(new BorderLayout());

        // Criação do botão "Voltar"
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            newFrame.dispose();
            mainFrame.setVisible(true);
        });

        // Adiciona o botão "Voltar" e o label na janela
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        newFrame.add(topPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Página de " + title, SwingConstants.CENTER);
        newFrame.add(label, BorderLayout.CENTER);

        // Ocultar a janela principal e mostrar a nova janela
        mainFrame.setVisible(false);
        newFrame.setVisible(true);
    }

}
