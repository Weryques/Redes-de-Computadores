package br.com.clientehttp.visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import br.com.clientehttp.controlador.Controle;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class InterfaceNavegador {

	private JFrame janela;
	private JTextField barraEndereco;
	/**
	 * Launch the application.
	 */
	public void iniciarInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceNavegador window = new InterfaceNavegador();
					window.janela.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public InterfaceNavegador(){
		abrirJanela();
	}

	private void abrirJanela() {
		janela = new JFrame("Grupo 3 - Navegador");
		
		janela.setBounds(100, 100, 982, 692);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(true);
		
		JPanel painelBarra = new JPanel();
		painelBarra.setBackground(Color.LIGHT_GRAY);
		janela.getContentPane().add(painelBarra, BorderLayout.NORTH);
		FlowLayout fl_painelBarra = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_painelBarra.setAlignOnBaseline(true);
		painelBarra.setLayout(fl_painelBarra);

		JPanel painelPagina = new JPanel();
		janela.getContentPane().add(painelPagina, BorderLayout.CENTER);
		painelPagina.setLayout(new BorderLayout(0, 0));
		
		JScrollPane painelComRolagem = new JScrollPane();
		painelPagina.add(painelComRolagem);
		
		JTextPane pagina = new JTextPane();
		pagina.setEditable(false);
		painelComRolagem.setViewportView(pagina);
		
		barraEndereco = new JTextField();
		barraEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		barraEndereco.setText("http://");
		barraEndereco.setToolTipText("Digite ou cole a URL aqui");
		barraEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		barraEndereco.setColumns(40);
		painelBarra.add(barraEndereco);
		
		JButton botaoIr = new JButton("Carregar");
		botaoIr.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		botaoIr.setForeground(new Color(0, 0, 0));
		botaoIr.setBackground(new Color(135, 206, 235));
		botaoIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controle controle = new Controle();
				String urlBruta = barraEndereco.getText();
				
				try {
					String conteudo = controle.requisitaConteudo(urlBruta);
					 
					pagina.setText(conteudo);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		painelBarra.add(botaoIr);
	}
}
