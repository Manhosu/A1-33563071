import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Aluno> listaDeAlunos = new ArrayList<>();
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader("alunos.csv"))) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    int matricula = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    double nota = Double.parseDouble(dados[2].trim());
                    Aluno aluno = new Aluno(matricula, nome, nota);
                    listaDeAlunos.add(aluno);
                } else {
                    System.out.println("Linha mal formatada: " + linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int quantidadeAlunos = listaDeAlunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            somaNotas += nota;
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double mediaNotas = somaNotas / quantidadeAlunos;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resumo.csv"))) {
            bw.write("Quantidade de alunos na turma: " + quantidadeAlunos);
            bw.newLine();
            bw.write("Aprovados: " + aprovados);
            bw.newLine();
            bw.write("Reprovados: " + reprovados);
            bw.newLine();
            bw.write("Menor nota: " + menorNota);
            bw.newLine();
            bw.write("Maior nota: " + maiorNota);
            bw.newLine();
            bw.write(String.format("Média geral: %.2f", mediaNotas));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Aluno {
    private int matricula;
    private String nome;
    private double nota;

    public Aluno(int matricula, String nome, double nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }
}
