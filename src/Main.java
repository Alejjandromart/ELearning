import java.io.File;
import java.util.Scanner;
import model.*;
 
public class Main {
    // IDs já cadastrados para evitar duplicidade entre Aluno, Professor e Atendente
    private static java.util.Set<Integer> idsCadastrados = new java.util.HashSet<>();
    private static boolean idJaCadastrado(int id) {
        return idsCadastrados.contains(id);
    }
    private static void registrarId(int id) {
        idsCadastrados.add(id);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        Aluno aluno = null;
        Atendente atendente = null;
        Professor professor = null;
        Curso curso = null;
        Assinatura assinatura = null;

        // Carrega cadastros do arquivo CSV
        java.util.List<Aluno> alunos = new java.util.ArrayList<>();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("bin/cadastros.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length > 1) {
                    String tipo = partes[1];
                    if ("Aluno".equalsIgnoreCase(tipo)) {
                        int id = Integer.parseInt(partes[0]);
                        String nome = partes[2];
                        long celular = partes[3].isEmpty() ? 0 : Long.parseLong(partes[3]);
                        alunos.add(new Aluno(id, nome, celular, new java.util.Date(), 0, 0));
                    }
                    // Adicione outros tipos conforme necessário
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar cadastros: " + e.getMessage());
        }

        // Exibe todos os alunos cadastrados
        if (!alunos.isEmpty()) {
            System.out.println("\nAlunos cadastrados:");
            for (Aluno a : alunos) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Celular: " + a.getCelular());
            }
        } else {
            System.out.println("Nenhum aluno cadastrado encontrado.");
        }
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
            System.out.println("4. Adicionar Curso ao Professor");
            System.out.println("5. Corrigir Exercício");
            System.out.println("6. Calcular Progresso");
            System.out.println("7. Emitir Fatura");
            System.out.println("8. Exibir Informações do Aluno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastrar Usuário ---");
                    System.out.println("1. Aluno");
                    System.out.println("2. Professor");
                    System.out.println("0. Voltar ao menu principal");
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
                        case 0:
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
                    System.out.println("4. Efetuar Assinatura");
                    System.out.println("5. Atender Usuário");
                    System.out.println("0. Voltar ao menu principal");
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
                        case 4:
                            efetuarAssinatura(atendente, aluno);
                            break;
                        case 5:
                            atenderUsuario(atendente, aluno);
                            break;
                        case 0:
                            // Voltar ao menu principal
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID do aluno que está entregando o exercício:");
                    int idAlunoEntrega = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o ID do exercício a ser entregue:");
                    int idExercicioEntrega = sc.nextInt();
                    sc.nextLine();
                    entregarExercicio(aluno, idAlunoEntrega, idExercicioEntrega);
                    break;
                case 4:
                    if (professor == null) {
                        System.out.println("Cadastre um professor primeiro!");
                        break;
                    }
                    if (curso == null) {
                        System.out.println("Cadastre um curso primeiro!");
                        break;
                    }
                    System.out.println("Digite o ID do curso a ser adicionado ao professor (8 dígitos):");
                    String idCursoStr = sc.nextLine();
                    if (!idCursoStr.matches("^\\d{8}$")) {
                        System.out.println("ID inválido! O ID do curso deve conter exatamente 8 dígitos numéricos.");
                        break;
                    }
                    int idCursoBusca = Integer.parseInt(idCursoStr);
                    if (curso.getIdCurso() != idCursoBusca) {
                        System.out.println("Curso não encontrado com esse ID. Cadastre o curso primeiro!");
                        break;
                    }
                    adicionarCursoAoProfessor(professor, curso);
                    break;
                case 5:
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado encontrado.");
                        break;
                    }
                    System.out.println("Digite o ID do aluno para corrigir a tarefa (8 dígitos):");
                    String idAlunoCorrigirStr = sc.nextLine();
                    if (!idAlunoCorrigirStr.matches("^\\d{8}$")) {
                        System.out.println("ID inválido! O ID do aluno deve conter exatamente 8 dígitos numéricos.");
                        break;
                    }
                    int idAlunoCorrigir = Integer.parseInt(idAlunoCorrigirStr);
                    boolean alunoExiste = false;
                    for (Aluno a : alunos) {
                        if (a.getId() == idAlunoCorrigir) {
                            alunoExiste = true;
                            break;
                        }
                    }
                    if (!alunoExiste) {
                        System.out.println("Aluno não encontrado com esse ID.");
                        break;
                    }
                    System.out.println("Digite o ID da tarefa a ser corrigida:");
                    int idTarefaCorrigir = sc.nextInt();
                    sc.nextLine();
                    corrigirExercicio(professor, idAlunoCorrigir, idTarefaCorrigir);
                    break;
                case 6:
                    calcularProgresso(curso, aluno);
                    break;
                case 7:
                    emitirFatura(assinatura, aluno);
                    break;
                case 8:
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado encontrado.");
                        break;
                    }
                    System.out.println("Digite o ID do aluno para exibir as informações (8 dígitos):");
                    String idAlunoInfoStr = sc.nextLine();
                    if (!idAlunoInfoStr.matches("^\\d{8}$")) {
                        System.out.println("ID inválido! O ID do aluno deve conter exatamente 8 dígitos numéricos.");
                        break;
                    }
                    int idAlunoInfo = Integer.parseInt(idAlunoInfoStr);
                    Aluno alunoInfo = null;
                    for (Aluno a : alunos) {
                        if (a.getId() == idAlunoInfo) {
                            alunoInfo = a;
                            break;
                        }
                    }
                    if (alunoInfo == null) {
                        System.out.println("Aluno não encontrado com esse ID.");
                        break;
                    }
                    exibirInformacoes(alunoInfo, atendente);
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
        // Coleta dados do aluno manualmente para tratar pontos como decimal
        int idAluno = 0;
        while (true) {
            System.out.println("Digite o ID do aluno (8 dígitos):");
            String idAlunoStr = sc.nextLine();
            if (!idAlunoStr.matches("^\\d{8}$")) {
                System.out.println("ID inválido! O ID do aluno deve conter exatamente 8 dígitos numéricos.");
                continue;
            }
            idAluno = Integer.parseInt(idAlunoStr);
            if (idJaCadastrado(idAluno)) {
                System.out.println("ID já utilizado por outro usuário! Digite um ID diferente.");
            } else {
                break;
            }
        }
        String nomeAluno = "";
        while (true) {
            System.out.println("Digite o nome do aluno (apenas letras e espaços, até 30 caracteres):");
            nomeAluno = sc.nextLine();
            if (!nomeAluno.matches("^[A-Za-zÀ-ÿ ]{1,30}$")) {
                System.out.println("Nome inválido! Digite apenas letras e espaços, até 30 caracteres.");
            } else {
                break;
            }
        }
        long celularAluno = 0;
        while (true) {
            System.out.println("Digite o celular do aluno (apenas números, padrão Brasil - 11 dígitos):");
            String celularAlunoStr = sc.nextLine();
            if (celularAlunoStr.matches("\\d{11}")) {
                celularAluno = Long.parseLong(celularAlunoStr);
                break;
            } else {
                System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (apenas números, padrão Brasil - 11 dígitos).");
            }
        }
        java.util.Date dataNascAluno = new java.util.Date();
        double pontos = 0.0;
        boolean pontosValidos = false;
        do {
            System.out.println("Digite os pontos do aluno (máximo 4 dígitos antes do ponto e até 2 após, ex: 10.00):");
            String pontosStr = sc.nextLine();
            if (pontosStr.matches("^\\d{1,4}(\\.\\d{1,2})?$")) {
                try {
                    pontos = Double.parseDouble(pontosStr);
                    pontosValidos = true;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido! Digite um número decimal válido.");
                }
            } else {
                System.out.println("Valor inválido! Digite até 4 dígitos antes do ponto e até 2 após (ex: 10.00).");
            }
        } while (!pontosValidos);
        int aulaAtual = 0;
        boolean aulaValida = false;
        do {
            System.out.println("Digite o número da aula atual do aluno:");
            String aulaStr = sc.nextLine();
            if (aulaStr.matches("^\\d{1,4}$")) {
                aulaAtual = Integer.parseInt(aulaStr);
                aulaValida = true;
            } else {
                System.out.println("Valor inválido! Digite até 4 dígitos numéricos.");
            }
        } while (!aulaValida);
        Aluno aluno = new Aluno(idAluno, nomeAluno, celularAluno, dataNascAluno, aulaAtual, pontos);
        registrarId(idAluno);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idAluno + ",Aluno," + nomeAluno + "," + celularAluno + ",,,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do aluno: " + e.getMessage());
        }
        return aluno;
    }

    private static Atendente cadastrarAtendente(Scanner sc) {
        int idAtendente = 0;
        while (true) {
            System.out.println("Digite o ID do atendente (8 dígitos):");
            String idAtendenteStr = sc.nextLine();
            if (!idAtendenteStr.matches("^\\d{8}$")) {
                System.out.println("ID inválido! O ID do atendente deve conter exatamente 8 dígitos numéricos.");
                continue;
            }
            idAtendente = Integer.parseInt(idAtendenteStr);
            if (idJaCadastrado(idAtendente)) {
                System.out.println("ID já utilizado por outro usuário! Digite um ID diferente.");
            } else {
                break;
            }
        }
        String nomeAtendente = "";
        while (true) {
            System.out.println("Digite o nome do atendente (apenas letras e espaços, até 30 caracteres):");
            nomeAtendente = sc.nextLine();
            if (!nomeAtendente.matches("^[A-Za-zÀ-ÿ ]{1,30}$")) {
                System.out.println("Nome inválido! Digite apenas letras e espaços, até 30 caracteres.");
            } else {
                break;
            }
        }
        long celularAtendente = 0;
        while (true) {
            System.out.println("Digite o celular do atendente (apenas números, padrão Brasil - 11 dígitos):");
            String celularAtendenteStr = sc.nextLine();
            if (celularAtendenteStr.matches("\\d{11}")) {
                celularAtendente = Long.parseLong(celularAtendenteStr);
                break;
            } else {
                System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (apenas números, padrão Brasil - 11 dígitos).");
            }
        }
        java.util.Date dataNascAtendente = new java.util.Date();
        System.out.println("Digite o cargo do atendente:");
        String cargo = sc.nextLine();
        System.out.println("Digite o número de matrícula do atendente:");
        int matricula = sc.nextInt();
        sc.nextLine();
        Atendente atendente = new Atendente(idAtendente, nomeAtendente, celularAtendente, dataNascAtendente, cargo, matricula);
        registrarId(idAtendente);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idAtendente + ",Atendente," + nomeAtendente + "," + celularAtendente + "," + dataNascAtendente + "," + cargo + "," + matricula + ",,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do atendente: " + e.getMessage());
        }
        return atendente;
    }

    private static Professor cadastrarProfessor(Scanner sc) {
        int idProfessor = 0;
        while (true) {
            System.out.println("Digite o ID do professor (8 dígitos):");
            String idProfessorStr = sc.nextLine();
            if (!idProfessorStr.matches("^\\d{8}$")) {
                System.out.println("ID inválido! O ID do professor deve conter exatamente 8 dígitos numéricos.");
                continue;
            }
            idProfessor = Integer.parseInt(idProfessorStr);
            if (idJaCadastrado(idProfessor)) {
                System.out.println("ID já utilizado por outro usuário! Digite um ID diferente.");
            } else {
                break;
            }
        }
        String nomeProfessor = "";
        while (true) {
            System.out.println("Digite o nome do professor (apenas letras e espaços, até 30 caracteres):");
            nomeProfessor = sc.nextLine();
            if (!nomeProfessor.matches("^[A-Za-zÀ-ÿ ]{1,30}$")) {
                System.out.println("Nome inválido! Digite apenas letras e espaços, até 30 caracteres.");
            } else {
                break;
            }
        }
        long celularProfessor = 0;
        while (true) {
            System.out.println("Digite o celular do professor (apenas números, padrão Brasil - 11 dígitos):");
            String celularProfessorStr = sc.nextLine();
            if (celularProfessorStr.matches("\\d{11}")) {
                celularProfessor = Long.parseLong(celularProfessorStr);
                break;
            } else {
                System.out.println("Celular inválido! Digite exatamente 11 dígitos numéricos (apenas números, padrão Brasil - 11 dígitos).");
            }
        }
        java.util.Date dataNascProfessor = new java.util.Date();
        Professor professor = new Professor(idProfessor, nomeProfessor, celularProfessor, dataNascProfessor);
        registrarId(idProfessor);
        try (java.io.FileWriter fw = new java.io.FileWriter("bin/cadastros.csv", true)) {
            fw.write(idProfessor + ",Professor," + nomeProfessor + "," + celularProfessor + "," + dataNascProfessor + ",,,,,\n");
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar cadastro do professor: " + e.getMessage());
        }
        return professor;
    }

    private static Curso cadastrarCurso(Scanner sc) {
        int idCurso = 0;
        while (true) {
            System.out.println("Digite o ID do curso (8 dígitos):");
            String idCursoStr = sc.nextLine();
            if (!idCursoStr.matches("^\\d{8}$")) {
                System.out.println("ID inválido! O ID do curso deve conter exatamente 8 dígitos numéricos.");
                continue;
            }
            idCurso = Integer.parseInt(idCursoStr);
            break;
        }
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
        int idAssinatura = 0;
        while (true) {
            System.out.println("Digite o ID da assinatura (8 dígitos):");
            String idAssinaturaStr = sc.nextLine();
            if (!idAssinaturaStr.matches("^\\d{8}$")) {
                System.out.println("ID inválido! O ID da assinatura deve conter exatamente 8 dígitos numéricos.");
                continue;
            }
            idAssinatura = Integer.parseInt(idAssinaturaStr);
            break;
        }
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

    private static void entregarExercicio(Aluno aluno, int idAluno, int idExercicio) {
        if (aluno != null) {
            aluno.entregarExercicio(idAluno, idExercicio);
        } else {
            System.out.println("Cadastre um aluno primeiro!");
        }
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

    private static void corrigirExercicio(Professor professor, int idAluno, int idTarefa) {
        if (professor != null) {
            professor.corrigirExercicio(idAluno, idTarefa, new java.util.Date(), true);
        } else {
            System.out.println("Cadastre um professor primeiro!");
        }
    }

    private static void calcularProgresso(Curso curso, Aluno aluno) {
        if (curso != null && aluno != null) {
            if (aluno.getId() == curso.getIdCurso()) {
                System.out.println("O ID do Aluno e o ID do Curso não podem ser iguais! Por favor, cadastre IDs diferentes.");
                return;
            }
            int[] progresso = curso.calcularProgresso(aluno.getId(), curso.getIdCurso());
            System.out.println("Progresso do aluno no curso:");
            System.out.println("ID Aluno: " + progresso[0]);
            System.out.println("ID Curso: " + progresso[1]);
            System.out.println("Progresso: " + progresso[2] + "%");
        } else {
            System.out.println("Cadastre curso e aluno primeiro!");
        }
    }

    private static void emitirFatura(Assinatura assinatura, Aluno aluno) {
        if (assinatura != null && aluno != null) {
            int[] fatura = assinatura.emitirFatura(assinatura.getIdAssinatura(), aluno.getId(), 5001, 1);
            System.out.println("\nFatura emitida com sucesso!");
            System.out.println("ID Assinatura: " + fatura[0]);
            System.out.println("ID Aluno: " + fatura[1]);
            System.out.println("Nota Fiscal: " + fatura[2]);
            System.out.println("Via: " + fatura[3]);
        } else {
            System.out.println("Cadastre assinatura e aluno primeiro!");
        }
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