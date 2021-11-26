import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceResolucaoDeProblemas extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    JPanel resolucao_problemas = new JPanel();
    JScrollPane centro_scroll = new JScrollPane(resolucao_problemas);
    private final JLabel txt_resolucao_problemas = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceResolucaoDeProblemas(String html_resolucao_problemas){
        setVisible(true);
        setTitle("Como usar");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(true);
        App.setIcone(this);
        setBounds(50, 50, 400, 450);
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        centro_scroll.getVerticalScrollBar().setUnitIncrement(6);
        centro.setPreferredSize(new Dimension(385, 370));
        resolucao_problemas.setPreferredSize(new Dimension(365, 2000));
        txt_resolucao_problemas.setPreferredSize(new Dimension(365, 2000));
        centro_scroll.setPreferredSize(new Dimension(385, 370));
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_resolucao_problemas.setText(html_resolucao_problemas);
        resolucao_problemas.add(txt_resolucao_problemas);
        centro.add(centro_scroll);
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
