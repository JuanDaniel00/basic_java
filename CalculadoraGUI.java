import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private calculadora calculadora;
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JTextField campoResultado;

    public CalculadoraGUI() {
        calculadora = new calculadora();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        campoNumero1 = new JTextField(10);
        campoNumero2 = new JTextField(10);
        campoResultado = new JTextField(10);
        campoResultado.setEditable(false);

        JButton botonSumar = new JButton("+");
        JButton botonRestar = new JButton("-");
        JButton botonMultiplicar = new JButton("×");
        JButton botonDividir = new JButton("÷");

        botonSumar.addActionListener(new OperacionListener());
        botonRestar.addActionListener(new OperacionListener());
        botonMultiplicar.addActionListener(new OperacionListener());
        botonDividir.addActionListener(new OperacionListener());

        panel.add(new JLabel("Número 1:"));
        panel.add(campoNumero1);
        panel.add(new JLabel("Número 2:"));
        panel.add(campoNumero2);
        panel.add(botonSumar);
        panel.add(botonRestar);
        panel.add(botonMultiplicar);
        panel.add(botonDividir);
        panel.add(new JLabel("Resultado:"));
        panel.add(campoResultado);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        pack();
    }

    private class OperacionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                double numero1 = Double.parseDouble(campoNumero1.getText());
                double numero2 = Double.parseDouble(campoNumero2.getText());
                double resultado = 0;
                String comando = evt.getActionCommand();

                switch (comando) {
                    case "+":
                        resultado = calculadora.sumar(numero1, numero2);
                        break;
                    case "-":
                        resultado = calculadora.restar(numero1, numero2);
                        break;
                    case "×":
                        resultado = calculadora.multiplicar(numero1, numero2);
                        break;
                    case "÷":
                        resultado = calculadora.dividir(numero1, numero2);
                        break;
                }

                campoResultado.setText(String.valueOf(resultado));
            } catch (NumberFormatException e) {
                campoResultado.setText("Entrada inválida");
            } catch (ArithmeticException e) {
                campoResultado.setText("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI().setVisible(true);
            }
        });
    }
}