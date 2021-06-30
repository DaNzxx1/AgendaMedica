package Persistencia;

import java.sql.SQLException;

public class BuildDeTabelas extends ConexaoComBancoDeDados {
    
    private final String tabelaPerfil = "CREATE TABLE IF NOT EXISTS PERFIL ("
            +"CODIGO INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 101, INCREMENT BY 1),"
            +"NOME VARCHAR(50) NOT NULL,"
            +"DESCRICAO VARCHAR(100) NOT NULL,"
            +"PRIMARY KEY(CODIGO));";
    
    private final String tabelaUsuario = "CREATE TABLE IF NOT EXISTS USUARIO ("
            +"ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 100, INCREMENT BY 1),"
            +"CPF VARCHAR(13) NOT NULL,"
            +"NOME VARCHAR(50) NOT NULL,"
            +"USERNAME VARCHAR(30) NOT NULL,"
            +"SENHA VARCHAR(100) NOT NULL,"
            +"CODIGO_PERFIL INTEGER NOT NULL,"
            +"TELEFONE VARCHAR(15) NOT NULL,"
            +"MATRICULA VARCHAR(5) NOT NULL,"
            +"DATA_NASCIMENTO DATE NOT NULL,"
            +"PRIMARY KEY(ID),"
            +"FOREIGN KEY(CODIGO_PERFIL) REFERENCES PERFIL(CODIGO));";
    
    private final String tabelaMedico = "CREATE TABLE IF NOT EXISTS MEDICO ("
            +"CRM INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 122, INCREMENT BY 1),"
            +"NOME VARCHAR(50) NOT NULL,"
            +"ESPECIALIDADE VARCHAR(20) NOT NULL,"
            +"SEXO CHAR NOT NULL,"
            +"DATA_DE_NASCIMENTO DATE NOT NULL,"
            +"SALARIO VARCHAR(15) NOT NULL,"
            +"PRIMARY KEY (CRM));";
        
    private final String tabelaPaciente = "CREATE TABLE IF NOT EXISTS PACIENTE("
            +"ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            +"CPF VARCHAR(13) NOT NULL,"
            +"NOME VARCHAR(50) NOT NULL,"
            +"TELEFONE VARCHAR(15) NOT NULL,"
            +"DATA_DE_NASCIMENTO DATE NOT NULL,"
            +"ENDERECO VARCHAR(70) NOT NULL,"
            +"SEXO CHAR NOT NULL,"
            +"PRIMARY KEY (ID));";
    
    private final String tabelaPlanoDeSaude = "CREATE TABLE IF NOT EXISTS PLANODESAUDE ("
            +"ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            +"CODIGO_PLANO VARCHAR(10) NOT NULL,"
            +"OPERADORA VARCHAR(15) NOT NULL,"
            +"TELEFONE VARCHAR(15) NOT NULL,"
            +"ENDERECO VARCHAR(70) NOT NULL,"
            +"REGISTRO_ANS VARCHAR(10) NOT NULL,"
            +"PRIMARY KEY (ID));";
    
    private final String tabelaConsulta = "CREATE TABLE IF NOT EXISTS CONSULTA ("
            +"ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 100, INCREMENT BY 1),"
            +"ID_PLANO INTEGER NOT NULL,"
            +"ID_PACIENTE INTEGER NOT NULL,"
            +"CRM_MEDICO INTEGER NOT NULL,"
            +"SALA VARCHAR(3) NOT NULL,"
            +"DATA_DA_CONSULTA DATE NOT NULL,"
            +"HORA_DA_CONSULTA VARCHAR(50) NOT NULL,"
            +"PRIMARY KEY (ID),"
            +"FOREIGN KEY(ID_PLANO) REFERENCES PLANODESAUDE(ID),"
            +"FOREIGN KEY(ID_PACIENTE) REFERENCES PACIENTE(ID),"
            +"FOREIGN KEY(CRM_MEDICO) REFERENCES MEDICO(CRM));";
    
    
    private final String sequenciaMatriculaTbUsuario = "CREATE SEQUENCE IF NOT EXISTS MATRICULA_SEQ AS BIGINT START WITH 1000 INCREMENT BY 5;";
    
    private final String insertPerfis = "INSERT INTO PERFIL("
            +"NOME, DESCRICAO) VALUES "
            +"('ADMINISTRADOR', 'SUPER USUÁRIO'),"
            +"('COMUM', 'USUÁRIO SOMENTE CONSULTA'),"
            +"('AVANÇADO', 'USUÁRIO CONSULTA E CADASTRA');";
    
    private final String insertMedico = "INSERT INTO MEDICO"
            +"(NOME, ESPECIALIDADE, SEXO, DATA_DE_NASCIMENTO, SALARIO) VALUES"
            +"('VANESSA', 'CLÍNICA MÉDICA', 'F', '1965-01-01', '52750.00'),"
            +"('MICHAEL', 'OTORRINO', 'M', '1973-10-10', '41250.00'),"
            +"('PAULO', 'PEDIATRA', 'M', '1975-04-05', '38250.00'),"
            +"('FERNANDA', 'CARDIOLOGISTA', 'F', '1973-09-14', '32250.00'),"
            +"('RUBENS', 'DERMATOLOGISTA', 'M', '1980-09-30', '36170.00'),"
            +"('HELENA', 'PEDIATRA', 'F', '1973-06-01', '40300.00');";
    
    private final String insertPaciente = "INSERT INTO PACIENTE"
            +"(CPF, NOME, TELEFONE, DATA_DE_NASCIMENTO, ENDERECO, SEXO) VALUES"
            +"('85868936199', 'MARIA DO CARMO', '(61) 98564-3322', '1945-06-16', 'QN-07 CONJUNTO 02 CASA 03 AGUAS CLARAS - DF', 'F'),"
            +"('03388345577', 'LUIS CARLOS', '(61) 3409-8766', '1993-08-10', 'SQS 110 BLOCO A APT 104 ASA SUL -DF', 'M'),"
            +"('01229877654', 'FABIO JUNIOR', '(61) 5857-4583', '1975-12-29', 'QNN-04 CONJUNTO A CASA 35 CEILANDIA NORTE - DF', 'M'),"
            +"('56788995567', 'ANA LUISA', '(61) 97654-4563', '1983-09-01', 'QS-09 CONJUNTO 05 CASA 22 NÚCLEO BANDEIRANTE - DF', 'F'),"
            +"('22214557669', 'HAILTON MORAIS', '(61) 77654-4987', '1986-09-22', 'AVENIDA CENTRAL AE 19 LOTE A APARTAMENTO 102 AREAL - DF', 'M'),"
            +"('46812345567', 'KATIA AZEVEDO', '(61) 3373-2775', '1973-06-01', 'QSD 87 CASA 32 TAGUATINGA SUL - DF', 'F');";
    
    private final String insertConsulta = "INSERT INTO CONSULTA"
            +"(ID_PLANO, ID_PACIENTE, CRM_MEDICO, SALA, DATA_DA_CONSULTA, HORA_DA_CONSULTA) VALUES"
            +"('5', '1', '127', 'A2', '2017-05-16', '16:30'),"
            +"('6', '2', '123', 'A7', '2016-08-10', '09:45'),"
            +"('4', '3', '124', 'B9', '2017-01-29', '10:00'),"
            +"('1', '4', '125', 'C4', '2017-06-01', '16:30'),"
            +"('3', '5', '122', 'A2', '2015-09-22', '17:10'),"
            +"('2', '6', '126', 'B3', '2015-05-01', '07:45');";
    
    private final String insertPlanoDeSaude = "INSERT INTO PLANODESAUDE"
            + "(CODIGO_PLANO, OPERADORA, TELEFONE, ENDERECO, REGISTRO_ANS) VALUES"
            + "('AM14', 'AMIL', '87563327', 'QN-03 CONJUNTO 05 CASA 23 AGUAS CLARAS - DF', 'ANS00133'),"
            + "('ME23', 'MEDIAL', '36098768', 'SQS 410 BLOCO F APT 101 ASA SUL - DF', 'ANS00135'),"
            + "('CAS01', 'CASSI', '58576554', 'QNO - 04 CONJUNTO D CASA 35 CEILANDIA NORTE - DF', 'ANS00137'),"
            + "('ALL03', 'ALLIANCE', '976875433', 'QJ - 10 CONJUNTO 11 CASA 01 NÚCLEO BANDEIRANTE - DF', 'ANS00139'),"
            + "('BRA81', 'BRADESCO', '987774566', 'AVENIDA CENTRAL AE 19 LOTE A APARTAMENTO 102 AREAL - DF', 'ANS00141'),"
            + "('SUL67', 'SUL-AMÉRICA', '36554766', 'QSF 87 CASA 65 TAGUATINGA SUL - DF', 'ANS00143');";
    
    public void construirTabelas() throws SQLException {
        executarScript(tabelaPerfil);
        System.out.println("Tabela Perfil Criada com sucesso");
        
        executarScript(tabelaUsuario);
        System.out.println("Tabela Usuário Criada com sucesso");
        
        executarScript(sequenciaMatriculaTbUsuario);
        System.out.println("Sequencia Matrícula Usuário criada com sucesso");
        
        executarScript(tabelaMedico);
        System.out.println("Tabela Médico Criada com sucesso");
        
        executarScript(tabelaPaciente);
        System.out.println("Tabela Paciênte Criada com sucesso");
        
        executarScript(tabelaPlanoDeSaude);
        System.out.println("Tabela PlanoDeSaude Criada com sucesso");
        
        executarScript(tabelaConsulta);
        System.out.println("Tabela Consulta Criada com sucesso");
    }
    
    public void inserirDados() throws SQLException {
        
        System.out.println("Inserindo dados na tabela Perfil....");
        executarScript(insertPerfis);
        System.out.println("Dados de perfil inseridos com sucesso");
        
        executarScript(insertMedico);
        System.out.println("Dados de Medico inseridos com sucesso");
        
        executarScript(insertPaciente);
        System.out.println("Dados de Paciênte inseridos com sucesso");
        
        executarScript(insertPlanoDeSaude);
        System.out.println("Dados de PlanoDeSaude inseridos com sucesso");
        
        executarScript(insertConsulta);
        System.out.println("Dados de Consulta inseridos com sucesso");
    }
}
