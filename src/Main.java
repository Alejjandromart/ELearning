import model.*;
import java.util.Scanner;
// ...existing code...
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        Aluno aluno = null;
        Atendente atendente = null;
        Professor professor = null;
        Curso curso = null;
        Assinatura assinatura = null;
        // Garante que o diretório bin existe para salvar o CSV
        File pastaBin = new File("bin");
        if (!pastaBin.exists()) {
            pastaBin.mkdirs();
        }
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Atendente");
            System.out.println("3. Entregar Exercício");
            System.out.println("4. Efetuar Assinatura");
            System.out.println("5. Atender Usuário");
            System.out.println("6. Adicionar Curso ao Professor");
            System.out.println("7. Corrigir Exercício");
            System.out.println("8. Calcular Progresso");
            System.out.println("9. Emitir Fatura");
            System.out.println("10. Exibir Informações do Aluno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastrar Usuário ---");
                    System.out.println("1. Aluno");
                    System.out.println("2. Professor");
                    System.out.print("Escolha o tipo de usuário: ");
                    int tipoUsuario = sc.nextInt();
                    sc.nextLine();
                    switch (tipoUsuario) {
                        case 1:
                            aluno = cadastrarAluno(sc);
                            break;
                        case 2:
                            professor = cadastrarProfessor(sc);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Atendente ---");
                    System.out.println("1. Cadastrar Atendente");
                    System.out.println("2. Cadastrar Curso");
                    System.out.println("3. Cadastrar Assinatura");
                    System.out.print("Escolha uma opção: ");
                    int opAtendente = sc.nextInt();
                    sc.nextLine();
                    switch (opAtendente) {
                        case 1:
                            atendente = cadastrarAtendente(sc);
                            break;
                        case 2:
                            curso = cadastrarCurso(sc);
                            break;
                        case 3:
                            assinatura = cadastrarAssinatura(sc, aluno);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case 3:
                    entregarExercicio(aluno);
                    break;
                case 4:
                    efetuarAssinatura(atendente, aluno);
                    break;
                case 5:
                    atenderUsuario(atendente, aluno);
                    break;
                case 6:
                    adicionarCursoAoProfessor(professor, curso);
                    break;
                case 7:
                    corrigirExercicio(professor);
                    break;
                case 8:
                    calcularProgresso(curso, aluno);
                    break;
                case 9:
                    emitirFatura(assinatura, aluno);
                    break;
                case 10:
                    exibirInformacoes(aluno, atendente);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        sc.close();
    }

    private static Aluno cadastrarAluno(Scanner sc) {
        Aluno aluno = Aluno.criarAlunoPorTeclado();
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(aluno.getId() + ",Aluno," + aluno.getNome() + "," + aluno.getCelular() + ",,,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do aluno: " + e.getMessage());
        }
        return aluno;
    }

    private static Atendente cadastrarAtendente(Scanner sc) {
        System.out.println("Digite o ID do atendente:");
        int idAtendente = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do atendente:");
        String nomeAtendente = sc.nextLine();
        System.out.println("Digite o celular do atendente (apenas números, padrão Brasil - 11 dígitos):");
        String celularAtendenteStr = sc.nextLine();
        long celularAtendente = 0;
        if (celularAtendenteStr.matches("\\d{11}")) {
            celularAtendente = Long.parseLong(celularAtendenteStr);
        } else {
            System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (ex: 11999999999).");
        }
        java.util.Date dataNascAtendente = new java.util.Date();
        System.out.println("Digite o cargo do atendente:");
        String cargo = sc.nextLine();
        System.out.println("Digite o número de matrícula do atendente:");
        int matricula = sc.nextInt();
        sc.nextLine();
        Atendente atendente = new Atendente(idAtendente, nomeAtendente, celularAtendente, dataNascAtendente, cargo, matricula);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idAtendente + ",Atendente," + nomeAtendente + "," + celularAtendente + "," + dataNascAtendente + "," + cargo + "," + matricula + ",,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do atendente: " + e.getMessage());
        }
        return atendente;
    }

    private static Professor cadastrarProfessor(Scanner sc) {
        System.out.println("Digite o ID do professor:");
        int idProfessor = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do professor:");
        String nomeProfessor = sc.nextLine();
        System.out.println("Digite o celular do professor (apenas números, padrão Brasil - 11 dígitos):");
        String celularProfessorStr = sc.nextLine();
        long celularProfessor = 0;
        if (celularProfessorStr.matches("\\d{11}")) {
            celularProfessor = Long.parseLong(celularProfessorStr);
        } else {
            System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (ex: 11999999999).");
        }
        java.util.Date dataNascProfessor = new java.util.Date();
        Professor professor = new Professor(idProfessor, nomeProfessor, celularProfessor, dataNascProfessor);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idProfessor + ",Professor," + nomeProfessor + "," + celularProfessor + "," + dataNascProfessor + ",,,,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do professor: " + e.getMessage());
        }
        return professor;
    }

    private static Curso cadastrarCurso(Scanner sc) {
        System.out.println("Digite o ID do curso:");
        int idCurso = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do curso:");
        String nomeCurso = sc.nextLine();
        Curso curso = new Curso(idCurso, nomeCurso);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idCurso + ",Curso," + nomeCurso + ",,,,,,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do curso: " + e.getMessage());
        }
        return curso;
    }

    private static Assinatura cadastrarAssinatura(Scanner sc, Aluno aluno) {
        System.out.println("Digite o ID da assinatura:");
        int idAssinatura = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o tipo de plano:");
        String tipoPlano = sc.nextLine();
        System.out.println("Digite o valor da assinatura:");
        double valorAssinatura = sc.nextDouble();
        sc.nextLine();
        Assinatura assinatura = null;
        if (aluno != null) {
            assinatura = new Assinatura(idAssinatura, aluno.getId(), tipoPlano, valorAssinatura);
            try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
                fw.write(idAssinatura + ",Assinatura,,,,,,," + tipoPlano + "," + valorAssinatura + "\n");
            } catch (java.io.IOException e) {
                System.out.println("Erro ao salvar cadastro da assinatura: " + e.getMessage());
            }
        } else {
            System.out.println("Cadastre um aluno primeiro!");
        }
        return assinatura;
    }

    private static void entregarExercicio(Aluno aluno) {
        if (aluno != null) aluno.entregarExercicio();
        else System.out.println("Cadastre um aluno primeiro!");
    }

    private static void efetuarAssinatura(Atendente atendente, Aluno aluno) {
        if (atendente != null && aluno != null) atendente.efetuarAssinatura(aluno.getId());
        else System.out.println("Cadastre atendente e aluno primeiro!");
    }

    private static void atenderUsuario(Atendente atendente, Aluno aluno) {
        if (atendente != null && aluno != null) atendente.atenderUsuario(aluno.getId());
        else System.out.println("Cadastre atendente e aluno primeiro!");
    }

    private static void adicionarCursoAoProfessor(Professor professor, Curso curso) {
        if (professor != null && curso != null) professor.adicionarCurso(curso.getNomeCurso());
        else System.out.println("Cadastre professor e curso primeiro!");
    }

    private static void corrigirExercicio(Professor professor) {
        if (professor != null) professor.corrigirExercicio(1, new java.util.Date(), true);
        else System.out.println("Cadastre um professor primeiro!");
    }

    private static void calcularProgresso(Curso curso, Aluno aluno) {
        if (curso != null && aluno != null) curso.calcularProgresso(aluno.getId(), curso.getIdCurso());
        else System.out.println("Cadastre curso e aluno primeiro!");
    }

    private static void emitirFatura(Assinatura assinatura, Aluno aluno) {
        if (assinatura != null && aluno != null) assinatura.emitirFatura(assinatura.getIdAssinatura(), aluno.getId(), 5001, 1);
        else System.out.println("Cadastre assinatura e aluno primeiro!");
    }

    private static void exibirInformacoes(Aluno aluno, Atendente atendente) {
        if (aluno != null) {
            System.out.println("\nInformações do Aluno:");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Aula Atual: " + aluno.getAulaAtual());
        } else {
            System.out.println("Cadastre um aluno primeiro!");
        }
        if (atendente != null) {
            System.out.println("\nHistórico de Atendimentos:");
            System.out.println(atendente.getHistorico());
        }
    }
}