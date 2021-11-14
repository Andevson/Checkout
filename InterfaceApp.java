import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class InterfaceApp extends JFrame{
    JPanel painel = new JPanel();
    JPanel topo = new JPanel();
    JPanel centro = new JPanel();
    private final JMenuBar menu = new JMenuBar();
    private final JMenu menu_checkout = new JMenu("Checkout");
    private final JMenuItem menu_item_novo_pedido = new JMenuItem("Novo pedido");
    private final JMenuItem menu_item_cadastrar_produto = new JMenuItem("Cadastrar produto");
    private final JMenuItem menu_item_sair = new JMenuItem("Sair");
    private final JMenu menu_opcoes = new JMenu("Configurações");
    private final JMenuItem menu_item_configuracoes = new JMenuItem("Configurações");
    private final JMenu menu_sobre = new JMenu("Sobre");
    private final JMenuItem menu_item_licenca = new JMenuItem("Licença");
    private final JButton btn_novo_pedido = new JButton("Novo pedido");
    private final JButton btn_novo_produto = new JButton("Cadastrar produto");
    private JLabel lbl_background = new JLabel();
    Image backgroundImage;
    public void setBackground(){
        try{
            backgroundImage = ImageIO.read(new File("app_background.jpg"));
            lbl_background = new JLabel(new ImageIcon(backgroundImage));
        }catch(IOException e){
            App.abrirMensagem("E221");
            return;
        }
    }
    InterfaceApp(){
        super("Checkout");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(50, 50, 400, 450);
        App.setIcone(this);
        setBackground();
        menu_checkout.add(menu_item_novo_pedido);
        menu_checkout.add(menu_item_cadastrar_produto);
        menu_checkout.add(menu_item_sair);
        menu_opcoes.add(menu_item_configuracoes);
        menu_sobre.add(menu_item_licenca);
        menu.add(menu_checkout);
        menu.add(menu_opcoes);
        menu.add(menu_sobre);
        setJMenuBar(menu);
        painel.setLayout(new BorderLayout());
        topo.setLayout(new FlowLayout());
        centro.setLayout(new FlowLayout());
        add(painel);
        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(lbl_background);
        topo.setBackground(Color.BLUE);
        btn_novo_pedido.setBackground(Color.GREEN);
        btn_novo_pedido.setOpaque(true);
        btn_novo_pedido.setForeground(Color.BLACK);
        btn_novo_pedido.setFocusPainted(false);
        btn_novo_pedido.setBorderPainted(true);
        btn_novo_produto.setBackground(Color.YELLOW);
        btn_novo_produto.setOpaque(true);
        btn_novo_produto.setForeground(Color.BLACK);
        btn_novo_produto.setFocusPainted(false);
        btn_novo_produto.setBorderPainted(true);
        topo.add(btn_novo_pedido);
        topo.add(btn_novo_produto);
        menu_item_novo_pedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirPedido();
            }
        });
        menu_item_cadastrar_produto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirCadastroDeProduto("");
            }
        });
        menu_item_sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu_item_configuracoes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirConfiguracoes();
            }
        });
        menu_item_licenca.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirLicenca();
            }
        });
        btn_novo_pedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirPedido();
            }
        });
        btn_novo_produto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.abrirCadastroDeProduto("");
            }
        });
    }
}
