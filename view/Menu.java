package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controle.Jogo;
import Controle.LoopPrincipal;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLayeredPane;

public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu() {
		Incializa();
		BotaoNovoJogo();
		BotaoRegras();
		BotaoSair();
		EscreverFrogger();
		DesenharImagemSapo();
	}

	private void DesenharImagemSapo() {
		JLabel lblSapoImg = new JLabel("New label");
		lblSapoImg.setBounds(0, 75, 444, 196);
		contentPane.add(lblSapoImg);
		ImageIcon sapo = new ImageIcon(Menu.class.getResource("/img/sapo.png"));
		Image imagem = sapo.getImage().getScaledInstance(lblSapoImg.getWidth(), lblSapoImg.getHeight(), Image.SCALE_DEFAULT);
		
		lblSapoImg.setIcon(new ImageIcon(imagem));
		
	}

	private void EscreverFrogger() {
		JLabel lblFrogger = new JLabel("Frogger");
		lblFrogger.setForeground(new Color(255, 255, 255));
		lblFrogger.setFont(new Font("Tempus Sans ITC", Font.BOLD, 99));
		lblFrogger.setBounds(29, -48, 392, 181);
		contentPane.add(lblFrogger);
	}

	private void BotaoSair() {
		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setBackground(new Color(0, 128, 0));
		btnSair.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreYouSure frame = new AreYouSure();
				frame.setVisible(true);
			}
		});
		btnSair.setBounds(310, 203, 89, 23);
		contentPane.add(btnSair);
		
	}

	private void BotaoRegras() {
		JButton btnRegras = new JButton("Regras");
		btnRegras.setForeground(new Color(255, 255, 255));
		btnRegras.setBackground(new Color(0, 128, 0));
		btnRegras.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		btnRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegras.setBounds(168, 203, 89, 23);
		contentPane.add(btnRegras);
		
	}

	private void BotaoNovoJogo() {
		JButton btnNovoJogo = new JButton("Novo jogo");
		btnNovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						Jogo frogger = new Jogo();
						frogger.setVisible(true);
						frogger.iniciarLoop();				
					}
				});
			}
		});
		
		btnNovoJogo.setBackground(new Color(0, 128, 0));
		btnNovoJogo.setForeground(new Color(255, 255, 255));
		btnNovoJogo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		btnNovoJogo.setBounds(21, 203, 100, 23);
		contentPane.add(btnNovoJogo);
	}

	private void Incializa() {
		setTitle("Frogger");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
