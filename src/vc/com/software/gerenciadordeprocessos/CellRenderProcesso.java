package vc.com.software.gerenciadordeprocessos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CellRenderProcesso extends PanelTransparente implements ListCellRenderer {

    private Adapter binding;
    private final Border noFocusBorder;
    private Color messageColor;
    private Font nameLabelFont;
    private ImageIcon defaultBuddyIcon;
    private JLabel statusLabel;
    private JLabel nameLabel;
    private JLabel messageLabel;
    private JLabel messageLabel2;
    private JLabel buddyLabel;
    private final Insets zeroInsets = new Insets(0, 0, 0, 0);
    private Color nameColor = Color.BLACK;

    @Override
    public Component getListCellRendererComponent(
            JList jList, Object value_tmp, int index, boolean isSelected, boolean hasFocus) {

        Processo value = (Processo) value_tmp;
        setComponentOrientation(jList.getComponentOrientation());
        setForeground(Color.BLACK);
        setOpaque(false);
        Border border = null;
        if (isSelected) {
            border = new LineBorder(nameColor);
            setOpacity(0.5f);
        } else {
            setOpacity(0f);
            border = noFocusBorder;
        }

        setBorder(border);
        Adapter adapter = getAdapter(value);
        setToolTipText(adapter.getToolTipText());

        statusLabel.setBackground(getBackground());
        try {
            statusLabel.setText(adapter.getInsc());
            statusLabel.setFont(statusLabel.getFont().deriveFont(18.0f).deriveFont(Font.BOLD));
            statusLabel.setPreferredSize(new Dimension(20, 35));
        } catch (Exception e) {
        }

        nameLabel.setText(adapter.getName());
        nameLabel.setForeground(getForeground());
        messageLabel.setText(adapter.getLinha1());
        messageLabel2.setText(adapter.getLinha2());

        statusLabel.setIcon(adapter.getIcon());
        return this;
    }

    private String bTs(boolean b) {
        if (b) {
            return "1";
        } else {
            return "0";
        }
    }

    private void initGridBagConstraints(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = zeroInsets;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
    }

    public CellRenderProcesso(Adapter binding) {

        super(new GridBagLayout());

        this.binding = binding;
        noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        setBorder(noFocusBorder);
        messageColor = this.getForeground();
        statusLabel = new JLabel();
        nameLabel = new JLabel("Nome");
        nameLabel.setForeground(this.getForeground());
        messageLabel = new JLabel("Tipo");
        messageLabel2 = new JLabel("Endereco");
        buddyLabel = new JLabel(defaultBuddyIcon);
        buddyLabel.setBorder(new LineBorder(Color.black));
        nameLabelFont = nameLabel.getFont().deriveFont(12.0f).deriveFont(Font.BOLD);
        nameLabel.setFont(nameLabelFont);
        messageLabel.setForeground(messageColor);
        messageLabel2.setForeground(messageColor);
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout l = (GridBagLayout) getLayout();

        initGridBagConstraints(c);
        c.gridheight = 2;
        c.insets = new Insets(5, 10, 5, 10); // top, left, bottom, right;
        add(statusLabel, c);

        initGridBagConstraints(c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 2.0;
        c.ipadx = 10;
        c.insets = new Insets(0, 0, 0, 0); // top, left, bottom, right;
        add(nameLabel, c);

        initGridBagConstraints(c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 15;
        c.insets = new Insets(0, 0, 10, 0); // top, left, bottom, right;
        add(messageLabel, c);

        initGridBagConstraints(c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 15;
        c.insets = new Insets(15, 0, 0, 0); // top, left, bottom, right;
        add(messageLabel2, c);
    }

    public CellRenderProcesso() {
        this(new Adapter(null));
    }

    public Adapter getAdapter() {
        return binding;
    }

    public void setAdapter(Adapter binding) {
        if (binding == null) {
            throw new IllegalArgumentException("null binding");
        }
        this.binding = binding;
    }

    public final Adapter getAdapter(Processo value) {
        Adapter adapter = getAdapter();
        adapter.setValue(value);
        return adapter;
    }

    public static class Adapter {

        public Icon getIcon() {
            if (getInsc().trim().isEmpty() || getInsc().equals("- -")) {
                return new ImageIcon(getClass().getResource("/vc/com/software/gerenciadordeprocessos/img/pen-r-24.png"));
            }
            return new ImageIcon(getClass().getResource("/vc/com/software/gerenciadordeprocessos/img/pen-y-24.png"));
        }

        public String getInsc() {
            return "";
        }

        public Adapter(Processo celula) {
            setValue(celula);
        }

        public enum Status {

            PAGO, INATIVO, ATRASADO;
        };
        private Processo value;

        public Processo getValue() {
            return value;
        }

        public void setValue(Processo value) {
            this.value = value;
        }

        public String getName() {
            if (value == null) {
                return "";
            }
            return value.getNome();
        }

        public Status getStatus() {
            return Status.PAGO;
        }

        public String getLinha1() {
            if (value == null) {
                return "";
            }
            String saida = "";

            if (value.getCpf_cnpj() != null) {
                saida += "CPF/CNPJ: " + value.getCpf_cnpj().trim();
            }
            if (value.getCpf_cnpj() != null && value.getPlaca() != null) {
                saida += "  /  Placa: " + value.getPlaca().trim().toUpperCase();
            }
            if (value.getCpf_cnpj() == null && value.getPlaca() != null) {
                saida += "Placa: " + value.getPlaca().trim().toUpperCase();
            }
            return saida;
        }

        public String getLinha2() {
            if (value == null) {
                return "";
            }
            String saida = "";

            if (value.getCaixa() != null) {
                saida += "Caixa: " + value.getCaixa().trim();
            }
            if (value.getCpf_cnpj() != null && value.getSequencia() != null) {
                saida += "  /  Sequência: " + value.getSequencia().trim().toUpperCase();
            }
            if (value.getCpf_cnpj() == null && value.getSequencia() != null) {
                saida += "Sequência: " + value.getSequencia().trim().toUpperCase();
            }
            return saida;
        }

        public String getToolTipText() {
            return getName() + " - " + getLinha1();
        }
    }
}
