import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceComoUsar extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    JPanel como_usar = new JPanel();
    JScrollPane centro_scroll = new JScrollPane(como_usar);
    private final JLabel txt_como_usar = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceComoUsar(String html_como_usar){
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
        como_usar.setPreferredSize(new Dimension(365, 1700));
        txt_como_usar.setPreferredSize(new Dimension(365, 1700));
        centro_scroll.setPreferredSize(new Dimension(385, 370));
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_como_usar.setText(html_como_usar);
        como_usar.add(txt_como_usar);
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
