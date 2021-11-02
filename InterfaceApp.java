import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class InterfaceApp extends JFrame{
    private final JButton btn_novo_pedido = new JButton("Novo pedido");
    private final JButton btn_novo_produto = new JButton("Cadastrar produto");
    private JLabel lbl_background = new JLabel();
    Image backgroundImage;
    public void setBackground(){
        try{
            backgroundImage = ImageIO.read(new File("app_background.jpg"));
            lbl_background = new JLabel(new ImageIcon(backgroundImage));
        }catch(IOException e){
            App.lancarMensagem("E221");
            return;
        }
    }
    InterfaceApp(){
        super("Checkout");
        JPanel painel = new JPanel();
        JPanel topo = new JPanel();
        JPanel centro = new JPanel();
        painel.setLayout(new BorderLayout());
        topo.setLayout(new FlowLayout());
        centro.setLayout(new FlowLayout());
        setBackground();
        add(painel);
        painel.add(topo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(lbl_background);
        topo.add(btn_novo_pedido);
        topo.add(btn_novo_produto);
        setBounds(50, 50, 400, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        App.setIcone(this);
        btn_novo_pedido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.novoPedido();
            }
        });
        btn_novo_produto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                App.novoProduto("");
            }
        });
    }
}
