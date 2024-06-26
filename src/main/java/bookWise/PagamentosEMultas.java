package bookWise;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PagamentosEMultas {
    private static SocioManager socioManager = new SocioManager();
    private static JFrame frame;
    private static JTable table;
    private static DefaultTableModel tableModel;

    public static void openPagamentosWindow() {
        frame = new JFrame("Pagamentos e Multas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> frame.dispose());
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Nome", "Número de Sócio", "Valor da Multa", "Notificar"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JButton pagarMultaButton = new JButton("Pagar Multa");
        pagarMultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSocioStr = JOptionPane.showInputDialog("Digite o número de sócio:");
                if (numeroSocioStr != null) {
                    int numeroSocio = Integer.parseInt(numeroSocioStr);
                    if (socioManager.pagarMulta(numeroSocio)) {
                        JOptionPane.showMessageDialog(null, "Concluído!");
                        carregarDados();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: Sócio não encontrado ou sem multas.");
                    }
                }
            }
        });

        table.getColumn("Notificar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Notificar").setCellEditor(new ButtonEditor(new JCheckBox()));

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(pagarMultaButton, BorderLayout.SOUTH);

        carregarDados();

        frame.setVisible(true);
    }

    private static void carregarDados() {
        List<Socio> socios = socioManager.getSocios();
        List<Socio> sociosComMulta = socioManager.getSociosComMulta();
        tableModel.setRowCount(0);

        for (Socio socio : sociosComMulta) {
            tableModel.addRow(new Object[]{
                    socio.getNome(),
                    socio.getNumeroSocio(),
                    socio.getValorMulta(),
                    "Notificar"
            });
        }

        if (socios.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não existem sócios registados.");
        }
    }

    public static void notificarSocio(int numeroSocio) {
        Socio socio = socioManager.getSocioPorNumero(numeroSocio);
        if (socio != null && socio.getValorMulta() > 0) {
            JOptionPane.showMessageDialog(frame, "Sócio notificado!");
        } else {
            JOptionPane.showMessageDialog(frame, "Erro: Sócio não encontrado ou sem multas.");
        }
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int numeroSocio = (int) table.getValueAt(row, 1);
                    PagamentosEMultas.notificarSocio(numeroSocio);
                }
            });
            return button;
        }
    }
}
