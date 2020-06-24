package classes;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

public class Arquivo {
    
    String s1,s2,s3,s4;
    
    TreeSet<Funcionario> func = new TreeSet<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public void gravarArquivoTexto(TreeSet<Funcionario> conjunto, String nomeArq) {
        File fArquivo = null; // Metadados do arquivo
        FileWriter fwArquivo = null; // Fluxo de conexão
        BufferedWriter bw = null; // Fluxo de cadeia
        PrintWriter pw = null; // Fluxo de cadeia alternativo mas muito usado
        String matAux, nomeAux, salarioAux, dataAdmAux;
        try {
            fArquivo = new File(nomeArq);
            fwArquivo = new FileWriter(fArquivo); // Se não existir, cria o arquivo
            bw = new BufferedWriter(fwArquivo);
            pw = new PrintWriter(bw);
            for (Funcionario fu : conjunto) { // Transformar todos os dados em String
            matAux = String.format("%d", fu.getMat());
            nomeAux = fu.getNome();
            salarioAux = String.valueOf(fu.getSalario());
            dataAdmAux = fu.getDataAdm().format(formatter);
            pw.format("%s%n", matAux + ":" + nomeAux + ":" + salarioAux + ":" + dataAdmAux); // Grava no arquivo a string com o separador : e acrescenta o %n (fim de linha) na plataforma utilizada
            }
            //fecha o FileWriter, PrintWriter e BufferedWriter
            pw.close();
            bw.close();
            fwArquivo.close();
            }
            catch (IOException e) {
                System.out.println("Erro ao inserir linhas no arquivo: " + fArquivo);
            }
    }
    
    public TreeSet<Funcionario> lerArquivoTexto(String nomeArq) {
        String registro = null;
        File arquivo = null;
        int matAux;
        String nomeAux;
        double salarioAux;
        LocalDate localDate;
        arquivo = new File(nomeArq);
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo));){
            String[] campos;
            while ((registro = br.readLine()) != null) {
                campos = registro.split(":"); // Separa os campos da string registro
                matAux = Integer.parseInt(campos[0].trim());
                nomeAux = campos[1].trim();
                salarioAux = Double.parseDouble(campos[2].trim());
                localDate = LocalDate.parse(campos[3], formatter);
                func.add(new Funcionario(matAux, nomeAux, salarioAux, localDate));
            }
        }
        catch (FileNotFoundException e) { // tratando quando o arquivo não existe
            System.out.println("Erro: arquivo nao existe: " + arquivo);
            JOptionPane.showMessageDialog(null, "Não foi possivel carregar arquivo na coleção", "Arquivo não existe!", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e) { // tratando quando há erro no readLine()
            System.out.println("Erro: arquivo na leitura do arquivo: " + arquivo);
        }
        return func;
    }
    
    public void incluir(Funcionario f){
        func.add(f);
    }
    public void remover(Funcionario f){
        func.remove(f);
    }
    public String pesquisa(Funcionario f){
        if(!func.contains(f)){
            s1 = ("O funcionario não existe");
        } else {
            s1 = ("O funcionario já existe");
        }      
        return s1;
    }
    public void alterar(Funcionario f){
        try{
            for(Funcionario a : func){
                if(a.getMat() == f.getMat()){
                    func.remove(a);
                    func.add(f);
                }
            }
        }
        catch(ConcurrentModificationException cme){}
    }
    public TreeSet<Funcionario> getFunc() {
        return func;
    }    
    public void setFunc(TreeSet<Funcionario> func) {
        this.func = func;
    }
}
