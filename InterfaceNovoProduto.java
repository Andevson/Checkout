import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfaceNovoProduto extends JFrame{
    private final JLabel lbl_id = new JLabel("ID:");
    private final JTextField txt_id = new JTextField("");
    private final JLabel lbl_codigo = new JLabel("Código:");
    private final JTextField txt_codigo = new JTextField("");
    private final JLabel lbl_fator = new JLabel("Fator:");
    private final JTextField txt_fator = new JTextField("1");
    private final JButton btn_cadastrar = new JButton("Cadastrar produto");
    InterfaceNovoProduto(String id){
        super("Cadastro de novo produto");
        JPanel painel = new JPanel();
        JPanel centro = new JPanel();
        JPanel sul = new JPanel();
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        centro.add(lbl_id);
        centro.add(txt_id);
        centro.add(lbl_codigo);
        centro.add(txt_codigo);
        centro.add(lbl_fator);
        centro.add(txt_fator);
        sul.add(btn_cadastrar);
        setBounds(450, 50, 400, 120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        App.setIcone(this);
        txt_id.setMinimumSize(new Dimension(75, 25));
        txt_id.setPreferredSize(new Dimension(75, 25));
        txt_id.setMaximumSize(new Dimension(75, 25));
        txt_codigo.setMinimumSize(new Dimension(100, 25));
        txt_codigo.setPreferredSize(new Dimension(100, 25));
        txt_codigo.setMaximumSize(new Dimension(100, 25));
        txt_fator.setMinimumSize(new Dimension(50, 25));
        txt_fator.setPreferredSize(new Dimension(50, 25));
        txt_fator.setMaximumSize(new Dimension(50, 25));
        txt_id.setText(id);
        App.setButtonColor(btn_cadastrar, 96, 255, 96);
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                if(id.length() > 0){
                    txt_codigo.requestFocusInWindow();
                }else{
                    txt_id.requestFocusInWindow();
                }
            }
        });
        btn_cadastrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(App.cadastrarProduto(txt_id.getText(), txt_codigo.getText(), txt_fator.getText())){
                    dispose();
                }
            }
        });
    }
}
