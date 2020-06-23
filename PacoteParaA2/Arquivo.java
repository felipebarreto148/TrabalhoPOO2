package PacoteParaA2;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Arquivo {
    
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
            bw = new BufferedWriter(new FileWriter("funcionario.txt", true));
            pw = new PrintWriter(bw);
            for (Funcionario fu : conjunto) { // Transformar todos os dados em String
            matAux = String.format("%d", fu.getMat());
            nomeAux = fu.getNome();
            salarioAux = String.valueOf(fu.getSalario());
            dataAdmAux = fu.getDataAdm().format(formatter);
            // Grava no arquivo a string com o separador : e acrescenta o %n (fim de linha) na plataforma utilizada
            pw.format("==============================");
            pw.format("\n======== FUNCIONÁRIO =========");
            pw.format("\n==============================");
            pw.format("\nMatrícula: ", matAux);
            pw.format("\nNome: "+ nomeAux);
            pw.format("\nSalário: R$"+ salarioAux);
            pw.format("\nData de Admissão: "+ dataAdmAux);
            pw.format("\n==============================");
            pw.format("\n==============================");
            pw.format("\n==============================");
            
            }
            //fecha o FileWriter, PrintWriter e BufferedWriter
            pw.close();
            bw.close();
            fwArquivo.close();
            }
            catch (IOException e) {
                System.err.println("Erro ao inserir linhas no arquivo: " + fArquivo);
            }
    }
    
    public TreeSet<Funcionario> lerArquivoTexto(String nomeArq) {
        String registro = null;
        File arquivo = null;
        TreeSet<Funcionario> setFuncionario = new TreeSet<>();
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
                setFuncionario.add(new Funcionario(matAux, nomeAux, salarioAux, localDate)); // Necessário usar esse novo construtor para manter a matrícula ou seria gerada uma nova
            }
        }
        catch (FileNotFoundException e) { // tratando quando o arquivo não existe
            System.err.println("Erro: arquivo nao existe. " + arquivo);
        }
        catch (IOException e) { // tratando quando há erro no readLine()
            System.err.println("Erro: arquivo na leitura do arquivo: " + arquivo);
        }
        return setFuncionario;
    }
    
    public void Criacao(String nome, String matricula, String salario, String admissao){
        Arquivo teste = new Arquivo();
        TreeSet<Funcionario> set = new TreeSet<>();
        set.add(new Funcionario("Joao", 250, LocalDate.of(2012, 12, 10)));
        set.add(new Funcionario("Jose", 300, LocalDate.of(2014, 07, 20)));
        set.add(new Funcionario("Antonio", 250, LocalDate.of(2015, 03, 25)));
        teste.gravarArquivoTexto(set, "arqTexto.txt");
        set = teste.lerArquivoTexto("arqTexto.txt");
        for (Funcionario fu : set) {
            System.out.println("\nMatricula: " + fu.getMat());
            System.out.println("Nome: " + fu.getNome());
            System.out.printf("%s %,.2f\n", "Salario: ", fu.getSalario());
            System.out.printf("%s %s\n", "Data de Admissão: ", String.format("%td/%<tm/%<ty", fu.getDataAdm()));
        }
    }
}
