import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceLicenca extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    JPanel licenca = new JPanel();
    JPanel info = new JPanel();
    JScrollPane centro_scroll = new JScrollPane(licenca);
    private final JLabel txt_licenca = new JLabel("");
    private final JLabel txt_info = new JLabel("<html><head></head><body><span>github.com/andevson/checkout</span></body></html>");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceLicenca(){
        setVisible(true);
        setTitle("Licença");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(true);
        App.setIcone(this);
        setBounds(50, 50, 400, 450);
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        centro.setPreferredSize(new Dimension(385, 430));
        licenca.setPreferredSize(new Dimension(365, 450));
        txt_licenca.setPreferredSize(new Dimension(365, 450));
        centro_scroll.setPreferredSize(new Dimension(385, 320));
        info.setPreferredSize(new Dimension(375, 50));
        sul.setLayout(new FlowLayout());
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_licenca.setText(App.getLicenca());
        licenca.add(txt_licenca);
        info.add(txt_info);
        centro.add(centro_scroll);
        centro.add(info);
        sul.add(btn_ok);
        btn_ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
