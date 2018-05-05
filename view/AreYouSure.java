package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AreYouSure extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public AreYouSure() {
		Inicializa();
		EscreverVcTemCtz();
		BotaoSim();
		BotaoNao();
	}
	private void BotaoNao() {
		JButton btnNo = new JButton("N\u00E3o");
		btnNo.setForeground(new Color(255, 255, 255));
		btnNo.setBackground(new Color(0, 128, 0));
		btnNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNo.setBounds(123, 92, 89, 23);
		contentPane.add(btnNo);
	}
	private void BotaoSim() {
		JButton btnSim = new JButton("Sim");
		btnSim.setForeground(new Color(255, 255, 255));
		btnSim.setBackground(new Color(0, 128, 0));
		btnSim.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSim.setBounds(10, 92, 89, 23);
		contentPane.add(btnSim);
	}
	private void EscreverVcTemCtz() {
		JLabel lblVocTemCerteza = new JLabel("Voc\u00EA tem certeza?");
		lblVocTemCerteza.setForeground(new Color(255, 255, 255));
		lblVocTemCerteza.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		lblVocTemCerteza.setBounds(20, 28, 202, 53);
		contentPane.add(lblVocTemCerteza);
		
	}
	private void Inicializa() {
		setResizable(false);
		setTitle("Sair");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 150, 228, 168);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
