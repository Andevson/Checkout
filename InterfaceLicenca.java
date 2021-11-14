import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfaceLicenca extends JDialog{
    JPanel painel = new JPanel();
    JPanel centro = new JPanel();
    JPanel sul = new JPanel();
    private final JLabel txt_licenca = new JLabel("");
    private final JButton btn_ok = new JButton("Ok");
    InterfaceLicenca(){
        setVisible(true);
        setTitle("Licença");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        App.setIcone(this);
        setBounds(500, 100, 250, 250);
        painel.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        sul.setLayout(new FlowLayout());
        add(painel);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(sul, BorderLayout.SOUTH);
        txt_licenca.setText(App.getLicenca());
        centro.add(txt_licenca);
        sul.add(btn_ok);
        btn_ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
