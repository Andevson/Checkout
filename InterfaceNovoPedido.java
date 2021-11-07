import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class InterfaceNovoPedido extends JFrame{
    JPanel painel = new JPanel();
    JPanel topo = new JPanel();
    JPanel centro = new JPanel();
    JScrollPane centro_scroll = new JScrollPane(centro);
    private final JTextArea txt_entrada = new JTextArea("");
    private final JButton btn_obter = new JButton("Obter produtos");
    private final JButton btn_finalizar = new JButton("Finalizar Pedido");
    private Object[][] dados = {{"", ""}};
    private String[] header = {"ID", "Código"};
    private JTable tbl_produtos = new JTable(dados, header);
    private Pedido p;
    InterfaceNovoPedido(Pedido pedido){
        super("Novo pedido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 50, 400, 500);
        App.setIcone(this);
        painel.setLayout(new BorderLayout());
        topo.setLayout(new FlowLayout());
        centro.setLayout(new BorderLayout());
        centro_scroll.getVerticalScrollBar().setUnitIncrement(6);
        add(painel);
        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro_scroll, BorderLayout.CENTER);
        topo.add(txt_entrada);
        topo.add(btn_obter);
        topo.add(btn_finalizar);
        centro.add(tbl_produtos.getTableHeader(), BorderLayout.NORTH);
        centro.add(tbl_produtos, BorderLayout.CENTER);
        txt_entrada.setMinimumSize(new Dimension(100, 20));
        txt_entrada.setPreferredSize(new Dimension(100, 20));
        txt_entrada.setMaximumSize(new Dimension(100, 20));
        centro_scroll.setPreferredSize(new Dimension(200, 500));
        this.p = pedido;
        btn_obter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                p = App.inserirProdutos(p, txt_entrada.getText());
                JTable nova_tbl_produtos = p.getTabelaProdutos();
                tbl_produtos.setModel(nova_tbl_produtos.getModel());
                ((AbstractTableModel) tbl_produtos.getModel()).fireTableDataChanged();
                txt_entrada.setText("");
            }
        });
        btn_finalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.finalizarPedido(p);
                txt_entrada.setText("");
                dispose();
            }
        });
    }
}
