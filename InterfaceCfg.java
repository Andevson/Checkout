import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceCfg extends JFrame{
    JPanel painel = new JPanel();
    JPanel bottom = new JPanel();
    JPanel centro = new JPanel();
    JPanel centro_cabecalho = new JPanel();
    JPanel centro_ordem_id = new JPanel();
    JPanel centro_ordem_quantidade = new JPanel();
    private final JLabel lbl_cabecalho = new JLabel("Usar cabeçalho: ");
    private final static JCheckBox check_cabecalho = new JCheckBox();
    private final JLabel lbl_ordem_id = new JLabel("Ordem do ID: ");
    private final static JSpinner spinner_ordem_id = new JSpinner();
    private final JLabel lbl_ordem_quantidade = new JLabel("Ordem da quantidade: ");
    private final static JSpinner spinner_ordem_quantidade = new JSpinner();
    private final JButton btn_cancelar= new JButton("Cancelar");
    private final JButton btn_salvar = new JButton("Salvar");
    private static void carregarCfg(){
        String[] cfg = App.getCfg();
        Boolean usar_cabecalho = Boolean.parseBoolean(cfg[0]);
        int ordem_id = Integer.parseInt(cfg[1]);
        int ordem_quantidade = Integer.parseInt(cfg[2]);
        check_cabecalho.setSelected(usar_cabecalho);
        SpinnerNumberModel model_ordem_id = new SpinnerNumberModel(ordem_id, 1, 255, 1);
        SpinnerNumberModel model_ordem_quantidade = new SpinnerNumberModel(ordem_quantidade, 1, 255, 1);
        spinner_ordem_id.setModel(model_ordem_id);
        spinner_ordem_quantidade.setModel(model_ordem_quantidade);
    }
    InterfaceCfg(){
        super("Configurações");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(450, 50, 200, 200);
        App.setIcone(this);
        painel.setLayout(new BorderLayout());
        bottom.setLayout(new FlowLayout());
        centro.setLayout(new GridLayout(3, 1));
        centro_cabecalho.setLayout(new FlowLayout());
        centro_ordem_id.setLayout(new FlowLayout());
        centro_ordem_quantidade.setLayout(new FlowLayout());
        add(painel);
        painel.add(bottom, BorderLayout.SOUTH);
        painel.add(centro, BorderLayout.CENTER);
        centro.add(centro_cabecalho);
        centro.add(centro_ordem_id);
        centro.add(centro_ordem_quantidade);
        centro_cabecalho.add(lbl_cabecalho);
        centro_cabecalho.add(check_cabecalho);
        centro_ordem_id.add(lbl_ordem_id);
        centro_ordem_id.add(spinner_ordem_id);
        centro_ordem_quantidade.add(lbl_ordem_quantidade);
        centro_ordem_quantidade.add(spinner_ordem_quantidade);
        bottom.add(btn_cancelar);
        bottom.add(btn_salvar);
        spinner_ordem_id.setPreferredSize(new Dimension(40, 20));
        spinner_ordem_quantidade.setPreferredSize(new Dimension(40, 20));
        carregarCfg();
        btn_cancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btn_salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.setCfg(check_cabecalho.isSelected(), Byte.parseByte(spinner_ordem_id.getValue().toString()), Byte.parseByte(spinner_ordem_quantidade.getValue().toString()));
            }
        });
    }
}
