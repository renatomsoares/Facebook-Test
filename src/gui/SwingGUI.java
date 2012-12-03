package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SwingGUI extends JFrame {
	
	// elementos do painel_inicio
	private JButton botao_entrar;
	private JTextField campo_token;
	private JPanel painel_inicio;
	private JLabel rotulo_titulo;
	private JLabel rotulo_insereToken;
	private SpringLayout layout_painel_inicio;
	private JLabel logoTipo;
	
	// elementos do painel_home
	private JLabel rotulo_bemVindo;
	private JLabel rotulo_getFlirting;
	private JLabel descrição;
	private JButton voltar_token;
	private JButton botao_meusFiltros;
	private JButton botao_appFiltros;
	private SpringLayout layout_painel_home;
	private JPanel painel_home;
	
	
	public SwingGUI() {
		painel_inicio = new JPanel();
		painel_home = new JPanel();
		layout_painel_inicio = new SpringLayout();
		layout_painel_home = new SpringLayout();
		botao_entrar = new JButton("ENTRAR");
		botao_appFiltros = new JButton("Quero que o GetFlirting escolha para mim");
		botao_meusFiltros = new JButton("Quero escolher meu(s) próprio(s) filtro(s)");
		voltar_token = new JButton("Voltar");
		campo_token = new JTextField(30);
		rotulo_insereToken = new JLabel("Insira o seu token de acesso:");
		rotulo_titulo = new JLabel("GetFlirting");
		rotulo_bemVindo = new JLabel("Bem Vindo ao GetFlirting, Fulano!");
		rotulo_getFlirting = new JLabel("GetFlirting!");
		descrição = new JLabel("Para encontrar sua paquera ideal, escolhe entre uma das opções abaixo:");
		
		//Trocar a String "C:\\Users\\Macário\\Documents\\Facebook-API\\src\\view\\logoTipo1.jpg" pelo seu endereço
		ImageIcon logo = new ImageIcon("logoTipo1.jpg");
		logoTipo = new JLabel(logo);

		
	}
	
	public void initComponents() {
		//setando o local e o tamanho da Janela
		setLocation(200, 200);
		setSize(640, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initFonts();
		initPainelInicio();
		//initPainelHome();
		
		setVisible(true);

	}

	private void initFonts() {
		Font titulo = new Font("Arial", Font.BOLD, 30);
		rotulo_titulo.setFont(titulo);
		Font ins_token = new Font("Arial", Font.BOLD,20);
		rotulo_insereToken.setFont(ins_token);
		Font getFlirting = new Font("Arial", Font.BOLD, 18);
		rotulo_getFlirting.setFont(getFlirting);
		Font desc = new Font("Arial", Font.BOLD, 15);
		descrição.setFont(desc);
		
	}

	private void initPainelInicio() {
		layout_painel_inicio.putConstraint(SpringLayout.NORTH,rotulo_titulo,30,SpringLayout.NORTH, getContentPane());
		layout_painel_inicio.putConstraint(SpringLayout.WEST, rotulo_titulo,230,SpringLayout.WEST,getContentPane());
		layout_painel_inicio.putConstraint(SpringLayout.NORTH,logoTipo,50,SpringLayout.NORTH, rotulo_titulo);
		layout_painel_inicio.putConstraint(SpringLayout.WEST, logoTipo,230,SpringLayout.WEST,getContentPane());
		layout_painel_inicio.putConstraint(SpringLayout.NORTH,rotulo_insereToken,200,SpringLayout.NORTH,rotulo_titulo);
		layout_painel_inicio.putConstraint(SpringLayout.WEST,rotulo_insereToken,170,SpringLayout.WEST,getContentPane());
		layout_painel_inicio.putConstraint(SpringLayout.NORTH, campo_token, 40, SpringLayout.NORTH, rotulo_insereToken);
		layout_painel_inicio.putConstraint(SpringLayout.WEST, campo_token, 145, SpringLayout.WEST, getContentPane());
		layout_painel_inicio.putConstraint(SpringLayout.NORTH, botao_entrar, 40, SpringLayout.NORTH, campo_token);
		layout_painel_inicio.putConstraint(SpringLayout.WEST, botao_entrar, 265, SpringLayout.WEST, getContentPane());
		
		painel_inicio.setLayout(layout_painel_inicio);
		painel_inicio.add(rotulo_titulo);
		painel_inicio.add(logoTipo);
		painel_inicio.add(rotulo_insereToken);	
		painel_inicio.add(campo_token);
		painel_inicio.add(botao_entrar);
		painel_inicio.setBackground(new Color(240,249,200));
		getContentPane().add(painel_inicio);
	}
	
	private void initPainelHome() {
		layout_painel_home.putConstraint(SpringLayout.NORTH, rotulo_bemVindo, 30, SpringLayout.NORTH, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.WEST, rotulo_bemVindo, 10, SpringLayout.WEST, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.NORTH, rotulo_getFlirting, 30, SpringLayout.NORTH, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.WEST, rotulo_getFlirting, 500, SpringLayout.WEST, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.NORTH, descrição, 150, SpringLayout.NORTH, rotulo_bemVindo);
		layout_painel_home.putConstraint(SpringLayout.WEST, descrição, 50, SpringLayout.WEST, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.NORTH, botao_meusFiltros, 180, SpringLayout.NORTH, rotulo_bemVindo);
		layout_painel_home.putConstraint(SpringLayout.WEST, botao_meusFiltros, 160, SpringLayout.WEST, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.NORTH, botao_appFiltros, 220, SpringLayout.NORTH, rotulo_bemVindo);
		layout_painel_home.putConstraint(SpringLayout.WEST, botao_appFiltros, 160, SpringLayout.WEST, getContentPane());
		layout_painel_home.putConstraint(SpringLayout.NORTH, voltar_token, 300, SpringLayout.NORTH, campo_token);
		layout_painel_home.putConstraint(SpringLayout.WEST, voltar_token, 260, SpringLayout.WEST, getContentPane());
		
		
		
		painel_home.setLayout(layout_painel_home);
		painel_home.add(rotulo_bemVindo);
		painel_home.add(rotulo_getFlirting);
		painel_home.add(descrição);
		painel_home.add(botao_meusFiltros);
		painel_home.add(botao_appFiltros);
		painel_home.add(voltar_token);
		painel_home.setBackground(new Color(240,249,200));
		getContentPane().add(painel_home);
		
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SwingGUI().initComponents();
            }
        });
	}
}
