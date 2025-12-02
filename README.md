# PrepFAESA

Aplicação educacional open source que utiliza IA para gerar exercícios personalizados com base nos livros das disciplinas da FAESA. A plataforma calcula um score de preparação por tópico para acompanhar o desempenho dos alunos e apoiar sua evolução rumo às avaliações.

## O que é e o que inclui nesta primeira release

- **Objetivo**: oferecer um MVP funcional para criação e prática de exercícios personalizados por tópico.
- **Base da aplicação**: projeto Java com distribuição em arquivo executável .jar.
- **Release disponível**: ``Quizopedia-0.0.1-SNAPSHOT.jar`` na seção Releases do repositório.

## Requisitos

- **Java instalado**: é necessário ter o Java Runtime Environment (JRE) ou Java Development Kit (JDK) instalado na máquina.

- **Sistema operacional**: Windows, macOS ou Linux.

- **Permissão de execução**: capacidade de executar arquivos .jar.

## Download da aplicação

- **Baixe a release**: acesse a aba “Releases” do repositório e faça o download do arquivo ```Quizopedia-0.0.1-SNAPSHOT.jar```.

- **Guarde o arquivo**: salve o .jar em uma pasta fácil de encontrar (por exemplo, “Downloads” ou “Documentos”).

## Instalação e execução passo a passo

### Método 1: Duplo clique (o mais simples)

1. **Localize o arquivo .jar:**

 - **Ação**: vá até a pasta onde você salvou ``Quizopedia-0.0.1-SNAPSHOT.jar``.

2. **Execute com duplo clique:**

 - **Ação**: clique duas vezes no arquivo para abrir a aplicação.

3. **Se não abrir:**

 - **Ação**: siga o Método 2 abaixo para executar via Terminal/Prompt.

### Método 2: Terminal/Prompt de Comando

1. **Abrir Terminal/Prompt:**

 - **Windows**: pressione Win+R, digite cmd e tecle Enter.

 - **macOS**: abra “Terminal” pelo Spotlight.

 - **Linux**: abra o Terminal pelo menu de aplicativos.

2. **Ir até a pasta do arquivo:**

 - **Ação**: use o comando para mudar de diretório.

 - **Exemplos**:

    - **Windows**: cd C:\Users\SEU_USUARIO\Downloads

    - **macOS/Linux**: cd /Users/SEU_USUARIO/Downloads ou cd ~/Downloads

3. **Executar o .jar:**

 - **Comando**: ```java -jar Quizopedia-0.0.1-SNAPSHOT.jar```

4. **Permitir execução (se solicitado):**

 - **Windows/macOS**: confirme qualquer alerta de segurança.

 - **Linux**: se houver erro de permissão, rode ```chmod +x Quizopedia-0.0.1-SNAPSHOT.jar``` e execute novamente.

## Verificação rápida

 - **Java instalado?:**

  - **Comando**: ```java -version```

  - **Resultado esperado**: uma linha indicando a versão do Java (ex.: “java version 17…”).

## Vídeo de apresentação do MVP

Assista ao vídeo de overview do MVP para conhecer as principais funcionalidades e o fluxo de uso:

[Assista ao vídeo do MVP](https://youtu.be/icGKU2-EA5g)

## Perguntas frequentes

- **Não tenho Java, e agora?**

  - **Resposta**: instale o JRE/JDK conforme indicado na seção Requisitos e verifique com ```java -version```.

- **O duplo clique não funciona.**

  - **Resposta**: execute pelo Terminal com ```java -jar Quizopedia-0.0.1-SNAPSHOT.jar``` para ver mensagens de erro.
