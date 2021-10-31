import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InterfacePedido extends JFrame{
    private final JTextArea txt_entrada = new JTextArea("Insira um produto.");
    private final JLabel lbl_qty_restante = new JLabel("Finalizar Pedido");
    private final JTable tbl_produtos = new JTable();
    private Pedido p;
    InterfacePedido(Pedido pedido){
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
        topo.add(lbl_qty_restante);
        centro.add(tbl_produtos);
        setBounds(50, 50, 400, 550);
        this.p = pedido;
        lbl_qty_restante.setText("Quantidade: " + p.getQuantidade());
        txt_entrada.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String entrada = txt_entrada.getText();
                if(entrada.length() >= 14){
                    p = App.saida(pedido, entrada);
                    lbl_qty_restante.setText("Quantidade: " + p.getQuantidade());
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            txt_entrada.setText("");
                        }
                    });
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
}
