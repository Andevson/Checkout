import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfaceMensagem extends JDialog{
    private final JLabel txt_mensagem = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceMensagem(String mensagem){
        //super("Cadastro de novo produto");
        JPanel painel = new JPanel();
        JPanel centro = new JPanel();
        JPanel sul = new JPanel();
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_mensagem.setText(mensagem);
        centro.add(txt_mensagem);
        sul.add(btn_ok);
        setBounds(500, 100, 250, 100);
        setResizable(false);
        btn_ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
