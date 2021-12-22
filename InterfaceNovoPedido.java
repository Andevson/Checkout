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
    private Object[][] dados = {{"", "", ""}};
    private String[] header = {"Pedido", "Quantidade", "Código de barras"};
    private JTable tbl_produtos = new JTable(dados, header);
    InterfaceNovoPedido(JTable pedidos){
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
        centro.add(tbl_produtos.getTableHeader(), BorderLayout.NORTH);
        centro.add(tbl_produtos, BorderLayout.CENTER);
        txt_entrada.setMinimumSize(new Dimension(100, 20));
        txt_entrada.setPreferredSize(new Dimension(100, 20));
        txt_entrada.setMaximumSize(new Dimension(100, 20));
        centro_scroll.setPreferredSize(new Dimension(200, 500));
        topo.setBackground(Color.BLUE);
        App.setButtonColor(btn_obter, 96, 255, 96);
        tbl_produtos.setModel(pedidos.getModel());
        ((AbstractTableModel) tbl_produtos.getModel()).fireTableDataChanged();
        txt_entrada.setText("");
        btn_obter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Pedido p = App.getPedido(pedidos, txt_entrada.getText());
                if(p != null){
                    App.abrirConferencia(p);
                }else{
                    //Erro
                }
                txt_entrada.setText("");
                dispose();
            }
        });
    }
}
