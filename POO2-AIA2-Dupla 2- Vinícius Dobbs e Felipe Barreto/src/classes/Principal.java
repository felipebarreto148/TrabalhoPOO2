package classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class Principal extends JFrame {
    
    Arquivo arq;
    TreeSet<Funcionario> set;
    
    //Define o painel
    private final JPanel Painel                = new JPanel();
    
    //Define as Labels da inteface
    private final JLabel LblMenu               = new JLabel("Menu");
    private final JLabel LblNome               = new JLabel("Nome: ");
    private final JLabel LblSalario            = new JLabel("Salario: ");
    private final JLabel LblAdmissao           = new JLabel("Data de Admissão:");
    private final JLabel LblMatricula          = new JLabel("Matricula:");
    
    //Define o campo de texto da interface
    private JTextField TxtNome           = new JTextField(); 
    private JTextField TxtSalario        = new JTextField();
    private JTextField TxtMatricula      = new JTextField();
    private final JTextArea TxtConsole   = new JTextArea();
    JScrollPane scroll                   = new JScrollPane(TxtConsole);
    MaskFormatter mascaraData = null;
    
    
 
    //Define os Botões da interface
    private final JButton BtnAdicionar         = new JButton("Adicionar");
    private final JButton BtnAlterar           = new JButton("Alterar por matricula");
    private final JButton BtnRemover           = new JButton("Remover");
    private final JButton BtnPesquisar         = new JButton("Pesquisar");
    private final JButton BtnListar            = new JButton("Listar por matricula");
    private final JButton BtnListarN            = new JButton("Listar por nome");
    private final JButton BtnSair              = new JButton("Salvar e Sair");
    private final JButton BtnLimpar            = new JButton("Limpar");
    
    Principal(){
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //fecha programa ao fechar janela
        setTitle("Menu de Funcionários");
        setSize(725,700);
        this.setLocationRelativeTo(null);  
        
        this.setContentPane(Painel);
        
        Painel.setSize(620, 480);
        Painel.setVisible(true);
        Painel.setLayout(null);
        
        LblMenu.setFont(new Font("Times new Roman", Font.BOLD, 50));
        
        //Adicionando as labels
        Painel.add(LblMenu);
        Painel.add(LblNome);
        Painel.add(LblSalario);
        Painel.add(LblAdmissao);
        Painel.add(LblMatricula);
        try{
        mascaraData = new MaskFormatter("##/##/####");
        mascaraData.setPlaceholderCharacter('_');
        }
        catch(ParseException excp){
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }
        JFormattedTextField jFormattedTextData = new JFormattedTextField(mascaraData);
        
        //Adciona os campos
        Painel.add(TxtNome);
        Painel.add(TxtSalario);
        Painel.add(jFormattedTextData);
        Painel.add(TxtMatricula);
        Painel.add(scroll);

        //Adicionando os botões
        Painel.add(BtnAdicionar);
        Painel.add(BtnRemover);
        Painel.add(BtnAlterar);
        Painel.add(BtnPesquisar);
        Painel.add(BtnListar);
        Painel.add(BtnListarN);
        Painel.add(BtnSair);
        Painel.add(BtnLimpar);
        
        //os valores do setbound são (eixo "X") (eixo "Y") (largura) (altura)
        LblMenu.setBounds(280, -75, 200, 275);
        LblNome.setBounds(370, 125, 100, 75);
        LblSalario.setBounds(363, 175, 100, 75);
        LblMatricula.setBounds(350, 225, 100, 75);
        LblAdmissao.setBounds(300, 275, 125, 75);
        
        TxtNome.setBounds(420, 150, 150, 25);
        TxtSalario.setBounds(420, 200, 150, 25);
        TxtMatricula.setBounds(420, 250, 150, 25);
        jFormattedTextData.setBounds(420, 300, 150, 25);
        scroll.setBounds(300, 430, 325, 170);
    
        BtnAdicionar.setBounds(100, 130, 170, 50);
        BtnRemover.setBounds(100, 270, 170, 50);
        BtnAlterar.setBounds(100, 200, 170, 50);
        BtnPesquisar.setBounds(100, 340, 170, 50);
        BtnListar.setBounds(100, 410, 170, 50);
        BtnListarN.setBounds(100, 480, 170, 50);
        BtnSair.setBounds(100, 550, 170, 50);
        BtnLimpar.setBounds(420, 350, 150, 50);
        
        arq = new Arquivo();
        set = new TreeSet<>();
        set = arq.lerArquivoTexto("arqTexto.txt");
        
        BtnAdicionar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               try{
               boolean b = false;
               int mat     = Integer.parseInt(TxtMatricula.getText());
               String nome = TxtNome.getText();
               double sal  = Double.parseDouble(TxtSalario.getText());
               String d = jFormattedTextData.getText();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate data = LocalDate.parse(d,formatter);
               Funcionario f = new Funcionario(mat,nome,sal,data);
               for(Funcionario a : set){
                   if(a.getMat() == f.getMat()){
                       JOptionPane.showMessageDialog(null, "Matricula ja existente", "ERRO", JOptionPane.ERROR_MESSAGE);
                       b = true;
                   }
               }
               if(b == false){
                    arq.incluir(f);
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
               }
               }
               catch(NumberFormatException nfe){
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
               catch(DateTimeParseException dtpe){
                   JOptionPane.showMessageDialog(null, "Data errada!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
            } });
        
        BtnRemover.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try{ 
               int mat = Integer.parseInt(TxtMatricula.getText());
               String nome = TxtNome.getText();
               double sal = Double.parseDouble(TxtSalario.getText());
               String d = jFormattedTextData.getText();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate data = LocalDate.parse(d,formatter);               
               arq.remover(new Funcionario(mat,nome,sal,data));
               JOptionPane.showMessageDialog(null, "Funcionário removido!", "", JOptionPane.INFORMATION_MESSAGE);
               }
               catch(NumberFormatException nfe){
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
               catch(DateTimeParseException dtpe){
                   JOptionPane.showMessageDialog(null, "Data errada!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
            } });
        
        BtnAlterar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
               try{ 
               int mat = Integer.parseInt(TxtMatricula.getText());
               String nome = TxtNome.getText();
               double sal = Double.parseDouble(TxtSalario.getText());
               String d = jFormattedTextData.getText();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate data = LocalDate.parse(d,formatter);               
               arq.alterar(new Funcionario(mat,nome,sal,data));
               JOptionPane.showMessageDialog(null, "Funcionário alterado!", "", JOptionPane.INFORMATION_MESSAGE);
               }
               catch(NumberFormatException nfe){
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
               catch(DateTimeParseException dtpe){
                   JOptionPane.showMessageDialog(null, "Data errada!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
                
            } });
        
        BtnPesquisar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
               try{
               StringBuilder bd = new StringBuilder();
               String s = null;
               int mat = Integer.parseInt(TxtMatricula.getText());
               String nome = TxtNome.getText();
               double sal = Double.parseDouble(TxtSalario.getText());
               String d = jFormattedTextData.getText();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate data = LocalDate.parse(d,formatter);
               JOptionPane.showMessageDialog(null, arq.pesquisa(new Funcionario(mat,nome,sal,data)), "", JOptionPane.INFORMATION_MESSAGE);
               }
               catch(NumberFormatException nfe){
                   JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
               catch(DateTimeParseException dtpe){
                   JOptionPane.showMessageDialog(null, "Data errada!", "ERRO", JOptionPane.ERROR_MESSAGE);
               }
                
            } });
        
        BtnListar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            StringBuilder bd = new StringBuilder();
            for (Funcionario f : set) {
                bd.append(f.toString());
                }
            TxtConsole.setText(bd.toString());
            TxtConsole.setCaretPosition(0);
            } });
        
        BtnListarN.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            TreeSet<Funcionario> ts = new TreeSet(new Comparator<Funcionario>() {
                @Override
                public int compare(Funcionario f1, Funcionario f2) {
                    return f1.getNome().compareTo(f2.getNome());
                }
            });
            for (Funcionario fu : set) {
                ts.add(fu);
            }
            StringBuilder bd = new StringBuilder();
            for (Funcionario f : ts) {
                bd.append(f.toString());
                }
            TxtConsole.setText(bd.toString());
            TxtConsole.setCaretPosition(0);
        } });
        
        BtnSair.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                arq.gravarArquivoTexto(set, "arqTexto.txt");
                JOptionPane.showMessageDialog(null, "Arquivo Salvo!", "", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } });
         
        BtnLimpar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                TxtNome.setText("");
                TxtSalario.setText("");
                TxtMatricula.setText("");
                jFormattedTextData.setText("");
                TxtConsole.setText("");
            } });       
    }
    
    public static void main(String[] args) throws IOException {
		Principal pro = new Principal();
	}
    
}