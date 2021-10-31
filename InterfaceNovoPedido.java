import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfaceNovoPedido extends JFrame{
    private final JTextArea txt_entrada = new JTextArea("Insira um produto.");
    private final JButton btn_obter = new JButton("Obter produtos");
    private final JButton btn_finalizar = new JButton("Finalizar Pedido");
    private final JTable tbl_produtos = new JTable();
    private Pedido p;
    InterfaceNovoPedido(Pedido pedido){
        super("Novo pedido");
        JPanel painel = new JPanel();
        JPanel topo = new JPanel();
        JPanel centro = new JPanel();
        painel.setLayout(new BorderLayout());
        topo.setLayout(new FlowLayout());
        centro.setLayout(new FlowLayout());
        add(painel);
        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);
        topo.add(txt_entrada);
        topo.add(btn_obter);
        topo.add(btn_finalizar);
        centro.add(tbl_produtos);
        setBounds(50, 50, 400, 550);
        this.p = pedido;
        btn_obter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                p = App.inserirProdutos(p, txt_entrada.getText());
                txt_entrada.setText("");
            }
        });
        btn_finalizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.finalizarPedido(p);
                txt_entrada.setText("");
            }
        });
    }
}
