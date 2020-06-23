package PacoteParaA2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Adiconar extends JFrame {
    
    //Define o painel
    private JPanel Painel                = new JPanel();
    
    //Define as Labels da inteface
    private JLabel LblNome               = new JLabel("Nome: ");
    private JLabel LblSalario            = new JLabel("Salario: ");
    private JLabel LblMatricula          = new JLabel("Matricula: ");
    private JLabel LblAdmissao           = new JLabel("Admissão:");
    
    //Define o campo de texto da interface
    private JTextField TxtNome           = new JTextField(""); 
    private JTextField TxtSalario        = new JTextField(""); 
    private JTextField TxtMatricula      = new JTextField(""); 
    private JTextField TxtAdmissao       = new JTextField("");
    
    
    //Define os Botões da interface
    private JButton BtnOk                = new JButton("Adicionar");
    
    int cont = 1;
    
    Adiconar(){
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setTitle("Adiconar Funcionário");
        setSize(570,300);
        this.setLocationRelativeTo(null);  
        
        this.setContentPane(Painel);
        
        Painel.setSize(620, 480);
        Painel.setVisible(true);
        Painel.setLayout(null);
        
        //Adicionando as labels
        Painel.add(LblNome);
        Painel.add(LblSalario);
        Painel.add(LblMatricula);
        Painel.add(LblAdmissao);
        
        //Adicionando o campo Text
        Painel.add(TxtNome);
        Painel.add(TxtSalario);
        Painel.add(TxtMatricula);
        Painel.add(TxtAdmissao);
        
        Painel.add(BtnOk);
        
        //os valores do setbound são (eixo "X") (eixo "Y") (largura) (altura)
        
        LblNome.setBounds(25, 0, 100, 75);
        LblSalario.setBounds(25, 50, 100, 75);
        LblMatricula.setBounds(300, 0, 100, 75);
        LblAdmissao.setBounds(300, 50, 100, 75);
        
        TxtNome.setBounds(80, 25, 150, 25);
        TxtSalario.setBounds(80, 75, 150, 25);
        TxtMatricula.setBounds(370, 25, 150, 25);
        TxtAdmissao.setBounds(370, 75, 150, 25);   
        
        BtnOk.setBounds(200, 150, 150, 50);
        
        BtnOk.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                if (TxtNome.getText().isEmpty() || TxtSalario.getText().isEmpty() 
                    || TxtMatricula.getText().isEmpty() || TxtAdmissao.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                    String nome = TxtNome.getText();
                    double sal  = Double.parseDouble(TxtSalario.getText());
                    int mat  = Integer.parseInt(TxtMatricula.getText());
                    
              
                    TreeSet<Funcionario> conjunto = new TreeSet<>();
                    conjunto.add(new Funcionario(nome, sal, LocalDate.of(2015, 03, 25)));
                   
                    Arquivo teste = new Arquivo();
                    
                    teste.gravarArquivoTexto(conjunto, "funcionario");
                    
                    
                    
                    
                   System.out.printf("\nO arquivo foi gravado com sucesso em na pasta do seu projeto");
                    
                   JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                   dispose();
                }             
            } });
        
    }
}