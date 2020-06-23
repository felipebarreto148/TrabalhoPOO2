package PacoteParaA2;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario implements Comparable<Funcionario>, Serializable{
    
    private int mat;
    private String nome;
    private double salario;
    private LocalDate data_adm;
    public static int mat_aux = 0;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");   

    public int getMat() {
        return mat;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public void setDataAdm(LocalDate data_adm) {
        this.data_adm = data_adm;
    }
    public LocalDate getDataAdm() {
        return data_adm;
    }
    
    public Funcionario() {
        super();
        mat = ++mat_aux;
    }
    public Funcionario(String nome, double salario, LocalDate data_adm) {
        this();
        this.nome = nome;
        this.salario = salario;
        this.data_adm = data_adm;
    }
    // Criado para manter as matrículas gravadas e não gerar novas matrículas ao recriar os objetos
    public Funcionario(int mat, String nome, double salario, LocalDate data_adm) {
    this.mat = mat;
    this.nome = nome;
    this.salario = salario;
    this.data_adm = data_adm;
    }
    
    public String toString(){
        return "\nMat: " + mat + "\nNome: " + nome + "\nSalario: " + String.format("%,.2f", salario) + "\nData de Admissão: " + data_adm.format(formatador);
    }
    
    @Override
    public int compareTo(Funcionario i) {
            return mat - i.mat;
    }
}
