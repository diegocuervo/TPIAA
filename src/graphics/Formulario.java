package graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Formulario extends JFrame implements ActionListener{
    private JTextField textfield1,textfield2, textfield3;
    private JButton boton1;
    private JLabel label1,label2,label3,label4,label5;

    
    public Formulario() {
        setLayout(null);
        textfield1=new JTextField();
        textfield1.setBounds(120,100,100,30);
        add(textfield1);
        textfield2=new JTextField();
        textfield2.setBounds(120,150,100,30);
        add(textfield2);
        label1 = new JLabel();
        label1.setBounds(10,100,100,30);
        label1.setText("Ancho (en cm)");
        label1.setVisible(true);
        add(label1);
        label2 = new JLabel();
        label2.setBounds(10,150,100,30);
        label2.setText("Alto (en cm)");
        label2.setVisible(true);
        add(label2);
        boton1=new JButton("Siguiente");
        boton1.setBounds(60,320,100,30);
        add(boton1);
        boton1.addActionListener(this);
        label3 = new JLabel();
        label3.setBounds(50,50,1000,30);
        label3.setText("Dimensiones de la Cancha");
        label3.setVisible(true);
        add(label3);
        label4 = new JLabel();
        label4.setBounds(50,50,1000,350);
        label4.setText("Alcance del Aspersor");
        label4.setVisible(true);
        add(label4);
        label5 = new JLabel();
        label5.setBounds(10,150,100,250);
        label5.setText("Radio (en cm)");
        label5.setVisible(true);
        add(label5);
        textfield3=new JTextField();
        textfield3.setBounds(120,260,100,30);
        add(textfield3);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
          //aca iria el evento de crear una cancha y el aspersor
        	int ancho =Integer.parseInt(textfield1.getText());
        	int alto = Integer.parseInt(textfield2.getText()); 
        	Cancha cancha = new Cancha(ancho, alto);
        	cancha.dibujar();
        }
    }
    
    public static void main(String[] ar) {
        Formulario formulario1=new Formulario();
        formulario1.setBounds(0,0,320,400);
        formulario1.setVisible(true);
    }
}