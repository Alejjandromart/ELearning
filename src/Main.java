import model.*;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Recebendo dados do Aluno
        System.out.println("Digite o ID do aluno:");
        int idAluno = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do aluno:");
        String nomeAluno = sc.nextLine();
        System.out.println("Digite o celular do aluno:");
        String celularAluno = sc.nextLine();
        Date dataNascAluno = new Date(); // Para simplificar, usa a data atual
        System.out.println("Digite a aula atual do aluno:");
        int aulaAtual = sc.nextInt();
        System.out.println("Digite os pontos do aluno:");
        int pontos = sc.nextInt();
        sc.nextLine();
        Aluno aluno = new Aluno(idAluno, nomeAluno, celularAluno, dataNascAluno, aulaAtual, pontos);

        // Recebendo dados do Atendente
        System.out.println("Digite o ID do atendente:");
        int idAtendente = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do atendente:");
        String nomeAtendente = sc.nextLine();
        System.out.println("Digite o celular do atendente:");
        String celularAtendente = sc.nextLine();
        Date dataNascAtendente = new Date();
        System.out.println("Digite o cargo do atendente:");
        String cargo = sc.nextLine();
        System.out.println("Digite o número de matrícula do atendente:");
        int matricula = sc.nextInt();
        sc.nextLine();
        Atendente atendente = new Atendente(idAtendente, nomeAtendente, celularAtendente, dataNascAtendente, cargo, matricula);

        // Recebendo dados do Professor
        System.out.println("Digite o ID do professor:");
        int idProfessor = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do professor:");
        String nomeProfessor = sc.nextLine();
        System.out.println("Digite o celular do professor:");
        String celularProfessor = sc.nextLine();
        Date dataNascProfessor = new Date();
        Professor professor = new Professor(idProfessor, nomeProfessor, celularProfessor, dataNascProfessor);

        // Recebendo dados do Curso
        System.out.println("Digite o ID do curso:");
        int idCurso = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do curso:");
        String nomeCurso = sc.nextLine();
        Curso curso = new Curso(idCurso, nomeCurso);

        // Recebendo dados da Assinatura
        System.out.println("Digite o ID da assinatura:");
        int idAssinatura = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o tipo de plano:");
        String tipoPlano = sc.nextLine();
        System.out.println("Digite o valor da assinatura:");
        double valorAssinatura = sc.nextDouble();
        sc.nextLine();
        Assinatura assinatura = new Assinatura(idAssinatura, aluno.getId(), tipoPlano, valorAssinatura);

        // Testando funcionalidades
        aluno.entregarExercicio();
        atendente.efetuarAssinatura(aluno.getId());
        atendente.atenderUsuario(aluno.getId());
        professor.adicionarCurso(nomeCurso);
        professor.corrigirExercicio(1, new Date(), true);
        curso.calcularProgresso(aluno.getId(), curso.getIdCurso());
        assinatura.emitirFatura(assinatura.getIdAssinatura(), aluno.getId(), 5001, 1);

        // Exibindo informações
        System.out.println("\nInformações do Aluno:");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Aula Atual: " + aluno.getAulaAtual());
        System.out.println("\nHistórico de Atendimentos:");
        System.out.println(atendente.getHistorico());
        sc.close();
    }
}