package bookWise;



import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SocioManager{
    public static List<Socio> socios = new ArrayList<>();

    public static void openSociosWindow() {
        JFrame sociosFrame = new JFrame("Sócios");
        sociosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sociosFrame.setSize(400, 300);
        sociosFrame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            sociosFrame.dispose();

        });
        topPanel.add(backButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        JButton verSociosButton = new JButton("Ver todos os sócios");
        verSociosButton.addActionListener(e -> openVerSociosWindow(sociosFrame));

        JButton registarSocioButton = new JButton("Registar novo sócio");
        registarSocioButton.addActionListener(e -> openRegistarSocioWindow(sociosFrame));

        mainPanel.add(verSociosButton);
        mainPanel.add(registarSocioButton);

        sociosFrame.add(topPanel, BorderLayout.NORTH);
        sociosFrame.add(mainPanel, BorderLayout.CENTER);


        sociosFrame.setVisible(true);
    }

    private static void openVerSociosWindow(JFrame parentFrame) {
        JFrame verSociosFrame = new JFrame("Todos os Sócios");
        verSociosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verSociosFrame.setSize(600, 400);
        verSociosFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            verSociosFrame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        verSociosFrame.add(topPanel, BorderLayout.NORTH);


        if (socios.isEmpty()) {
            JLabel noSociosLabel = new JLabel("Ainda não existem sócios registados.", SwingConstants.CENTER);
            verSociosFrame.add(noSociosLabel, BorderLayout.CENTER);
        } else {
            String[] columnNames = {"Nome", "Número de Sócio", "NIF ou CC", "Morada", "Telefone", "Email"};
            String[][] data = new String[socios.size()][7];

            for (int i = 0; i < socios.size(); i++) {
                Socio socio = socios.get(i);
                data[i][0] = socio.getNome();
                data[i][1] = String.valueOf(socio.getNumeroSocio());
                data[i][2] = socio.getNifOuCc();
                data[i][3] = socio.getMorada();
                data[i][4] = socio.getTelefone();
                data[i][5] = socio.getEmail();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            verSociosFrame.add(scrollPane, BorderLayout.CENTER);
        }

        parentFrame.setVisible(false);
        verSociosFrame.setVisible(true);
    }

    private static void openRegistarSocioWindow(JFrame parentFrame) {
        JFrame registarSocioFrame = new JFrame("Registar Novo Sócio");
        registarSocioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registarSocioFrame.setSize(400, 300);
        registarSocioFrame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            registarSocioFrame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        registarSocioFrame.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JTextField nomeField = new JTextField();
        JTextField moradaField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField nifOuCcField = new JTextField();
        JTextField emailField = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Morada:"));
        formPanel.add(moradaField);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(telefoneField);
        formPanel.add(new JLabel("NIF ou CC:"));
        formPanel.add(nifOuCcField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String morada = moradaField.getText();
            String nifOuCc = nifOuCcField.getText();
            String telefone = telefoneField.getText();
            String email = emailField.getText();

            if (!telefone.matches("9\\d{8}")) {
                JOptionPane.showMessageDialog(registarSocioFrame, "Formato de telemóvel incorreto.");
                return;
            }
            if (!nifOuCc.matches("\\d{8,9}")) {
                JOptionPane.showMessageDialog(registarSocioFrame, "NIF ou CC em formato incorreto.");
                return;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(registarSocioFrame, "Email em formato incorreto.");
                return;
            }

            if(!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
                Socio socio = new Socio(nome, morada,nifOuCc, telefone, email, 0.0 );
                socios.add(socio);
                JOptionPane.showMessageDialog(registarSocioFrame, "Sócio registado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                registarSocioFrame.dispose();
                parentFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(registarSocioFrame, "Por favor preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        formPanel.add(new JLabel());
        formPanel.add(salvarButton);


        registarSocioFrame.add(formPanel, BorderLayout.CENTER);
        parentFrame.setVisible(false);
        registarSocioFrame.setVisible(true);
    }

    public boolean pagarMulta(int numeroSocio) {
        for (Socio socio : socios) {
            if (socio.getNumeroSocio() == numeroSocio) {
                socio.setValorMulta(0.0);
                return true;
            }
        }
        return false;
    }

    public List<Socio> getSociosComMulta() {
        List<Socio> sociosComMulta = new ArrayList<>();
        for (Socio socio : socios) {
            if (socio.getValorMulta() > 0) {
                sociosComMulta.add(socio);
            }
        }
        return sociosComMulta;
    }

    public Socio getSocioPorNumero(int numeroSocio) {
        for (Socio socio : socios) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return socio;
            }
        }
        return null;
    }

    public List<Socio> getSocios() {
        return socios;
    }
}
