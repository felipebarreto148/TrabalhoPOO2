package PacoteParaA2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Interface extends JFrame {
    
    
    //Define o painel
    private JPanel Painel                = new JPanel();
    
    //Define as Labels da inteface
    private JLabel LblMenu               = new JLabel("Menu");

    //Define os Botões da interface
    private JButton BtnAdicionar         = new JButton("Adicionar");
    private JButton BtnRemover           = new JButton("Remover");
    private JButton BtnAlterar           = new JButton("Alterar");
    private JButton BtnPesquisar         = new JButton("Pesquisar");
    private JButton BtnListar  = new JButton("Listar Funcionários");
    
    
    Interface(){
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //fecha programa ao fechar janela
        setTitle("Menu de Funcionários");
        setSize(570,600);
        this.setLocationRelativeTo(null);  
        
        this.setContentPane(Painel);
        
        Painel.setSize(620, 480);
        Painel.setVisible(true);
        Painel.setLayout(null);
        
        LblMenu.setFont(new Font("Times new Roman", Font.BOLD, 50));
        
        //Adicionando as labels
        Painel.add(LblMenu);
      
        
        //Adicionando os botões
        Painel.add(BtnAdicionar);
        Painel.add(BtnRemover);
        Painel.add(BtnAlterar);
        Painel.add(BtnPesquisar);
        Painel.add(BtnListar);
        
        //os valores do setbound são (eixo "X") (eixo "Y") (largura) (altura)
        
        LblMenu.setBounds(210, -100, 200, 275);
    
        BtnAdicionar.setBounds(200, 130, 150, 50);
        BtnRemover.setBounds(200, 200, 150, 50);
        BtnAlterar.setBounds(200, 270, 150, 50);
        BtnPesquisar.setBounds(200, 340, 150, 50);
        BtnListar.setBounds(200, 410, 150, 50);
        
        
        BtnAdicionar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                 new Adiconar().setVisible(true);
                
            } });
        
        BtnRemover.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                        
                
                
            } });
        
        BtnAlterar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                
                
                
                
            } });
        
        BtnPesquisar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                
                
                
                
            } });
        
        BtnListar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                
                
                
                
            } });
    }
    
    public static void main(String[] args) {
		new Interface();
	}
    
}
