import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

public class InterfacePedido extends JFrame{
    private final JLabel lbl_entrada = new JLabel("Entre com código de barras:");
    private final JTextArea txt_entrada = new JTextArea("");
    private final JLabel lbl_qty_restante = new JLabel("Finalizar Pedido");
    private Object[][] dados = {{"", ""}};
    private String[] header = {"ID", "Código"};
    private final JTable tbl_produtos = new JTable(dados, header);
    private Pedido p;
    void atualizaTabela(Pedido pedido){
        JTable nova_tbl_produtos = pedido.getTabelaProdutos();
        tbl_produtos.setModel(nova_tbl_produtos.getModel());
        ((AbstractTableModel) tbl_produtos.getModel()).fireTableDataChanged();
    }
    InterfacePedido(Pedido pedido){
        super("Pedido");
        JPanel painel = new JPanel();
        JPanel topo = new JPanel();
        JPanel centro = new JPanel();
        painel.setLayout(new BorderLayout());
        topo.setLayout(new FlowLayout());
        centro.setLayout(new BorderLayout());
        add(painel);
        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);
        topo.add(lbl_entrada);
        topo.add(txt_entrada);
        topo.add(lbl_qty_restante);
        centro.add(tbl_produtos.getTableHeader(), BorderLayout.NORTH);
        centro.add(tbl_produtos, BorderLayout.CENTER);
        setBounds(450, 50, 400, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        App.setIcone(this);
        txt_entrada.setMinimumSize(new Dimension(100, 20));
        txt_entrada.setPreferredSize(new Dimension(100, 20));
        txt_entrada.setMaximumSize(new Dimension(100, 20));
        this.p = pedido;
        atualizaTabela(p);
        lbl_qty_restante.setText("Restante: " + p.getQuantidade());
        txt_entrada.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String entrada = txt_entrada.getText();
                topo.setBackground(new Color(222, 222, 222));
                if(entrada.charAt(entrada.length() - 1) == '\n'){
                    int qty = p.getQuantidade();
                    p = App.saida(pedido, entrada);
                    if(qty > p.getQuantidade()){
                        topo.setBackground(new Color(0, 255, 0));
                    }else{
                        topo.setBackground(new Color(255, 0, 0));
                    }
                    if(p.getQuantidade() <= 0){
                        App.lancarMensagem("A322");
                        dispose();
                    }
                    atualizaTabela(p);
                    lbl_qty_restante.setText("Restante: " + p.getQuantidade());
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
