import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

public class InterfacePedido extends JFrame{
    JPanel painel = new JPanel();
    JPanel topo = new JPanel();
    JPanel centro = new JPanel();
    JScrollPane centro_scroll = new JScrollPane(centro);
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
        topo.add(lbl_entrada);
        topo.add(txt_entrada);
        topo.add(lbl_qty_restante);
        centro.add(tbl_produtos.getTableHeader(), BorderLayout.NORTH);
        centro.add(tbl_produtos, BorderLayout.CENTER);
        txt_entrada.setMinimumSize(new Dimension(100, 20));
        txt_entrada.setPreferredSize(new Dimension(100, 20));
        txt_entrada.setMaximumSize(new Dimension(100, 20));
        centro_scroll.setPreferredSize(new Dimension(200, 500));
        this.p = pedido;
        atualizaTabela(p);
        lbl_qty_restante.setText("Restante: " + p.getQuantidade());
        txt_entrada.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String entrada = txt_entrada.getText();
                topo.setBackground(new Color(222, 222, 222));
                if(entrada.charAt(entrada.length() - 1) == '\n'){
                    int qty = p.getQuantidadeAbsoluta();
                    p = App.removerProdutos(pedido, entrada);
                    if(qty > p.getQuantidadeAbsoluta()){
                        topo.setBackground(new Color(0, 255, 0));
                    }else{
                        topo.setBackground(new Color(255, 0, 0));
                    }
                    if(p.getQuantidade() <= 0){
                        App.abrirMensagem("A322");
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
