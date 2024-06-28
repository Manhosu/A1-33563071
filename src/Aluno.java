public class Aluno {
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
