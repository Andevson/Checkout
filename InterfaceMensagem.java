import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InterfaceMensagem extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    private final JLabel txt_mensagem = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    private void ajustarTamanho(int tamanho_mensagem){
        float altura;
        float largura;
        float largura_caractere = 7.5f;
        if(tamanho_mensagem <= 50){
            altura = 28;
            largura = tamanho_mensagem* largura_caractere;
        }else if(tamanho_mensagem <= 100){
            altura = 56;
            largura = 50* largura_caractere;
        }else{
            altura = 84;
            largura = 50* largura_caractere;
        }
        setBounds(500, 100, (int)largura + 50, (int)altura + 80);
        painel.setPreferredSize(new Dimension((int)largura + 50, (int)altura + 80));
        centro.setPreferredSize(new Dimension((int)largura, (int)altura));
        txt_mensagem.setPreferredSize(new Dimension((int)largura, (int)altura));
    }
    InterfaceMensagem(String nome, String mensagem){
        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle(nome);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        App.setIcone(this);
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        ajustarTamanho(mensagem.length());
        add(painel);
        painel.add(centro, BorderLayout.NORTH);
        painel.add(sul, BorderLayout.SOUTH);
        txt_mensagem.setText("<html><p>" + mensagem + "</p></html>");
        centro.add(txt_mensagem);
        sul.add(btn_ok);
        App.setButtonColor(btn_ok, 128, 128, 255);
        btn_ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
