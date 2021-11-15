import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfaceMensagem extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    private final JLabel txt_mensagem = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceMensagem(String nome, String mensagem){
        setVisible(true);
        setTitle(nome);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        App.setIcone(this);
        setBounds(500, 100, 250, 100);
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_mensagem.setText(mensagem);
        centro.add(txt_mensagem);
        sul.add(btn_ok);
        App.setButtonColor(btn_ok, 128, 128, 255);
        btn_ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
