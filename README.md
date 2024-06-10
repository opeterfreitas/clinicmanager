# Clinic Manager

## Visão Geral

O **Clinic Manager** é um sistema SaaS desenvolvido para gerenciar clínicas médicas e fornecer funcionalidades de telemedicina. Utilizando Java 21, Quarkus, Maven e desenvolvido no IntelliJ IDEA Community Edition, o projeto visa proporcionar uma experiência eficiente e segura tanto para médicos quanto para pacientes.

## Funcionalidades Previstas

Ao final do desenvolvimento, o sistema Clinic Manager deverá incluir as seguintes funcionalidades:

### Funcionalidades para Pacientes

- **Cadastro e Login**
   - Registro de novos pacientes
   - Login seguro com autenticação multifator
- **Perfil do Paciente**
   - Visualização e edição de informações pessoais
   - Histórico médico
- **Agendamento de Consultas**
   - Marcação de consultas online e presenciais
   - Notificações automáticas de lembrete
- **Consultas Online**
   - Videoconferência com médicos
   - Chat e troca de mensagens com médicos
- **Pagamentos**
   - Processamento de pagamentos para consultas e serviços
   - Histórico de pagamentos
- **Notificações e Lembretes**
   - Notificações por e-mail e SMS para lembretes de consultas e informações importantes

### Funcionalidades para Médicos

- **Cadastro e Login**
   - Registro de novos médicos
   - Login seguro com autenticação multifator
- **Perfil do Médico**
   - Visualização e edição de informações profissionais
   - Agenda de consultas
- **Gestão de Consultas**
   - Gerenciamento de consultas marcadas
   - Videoconferência com pacientes
   - Chat e troca de mensagens com pacientes
- **Prontuário Eletrônico**
   - Acesso e atualização de prontuários médicos
   - Prescrição eletrônica

### Funcionalidades para Administradores da Clínica

- **Cadastro e Gestão de Usuários**
   - Gerenciamento de contas de pacientes e médicos
   - Controle de permissões de acesso
- **Agenda e Consultas**
   - Visualização e gerenciamento da agenda de consultas da clínica
   - Reatribuição de consultas entre médicos
- **Relatórios e Análises**
   - Geração de relatórios financeiros e de atendimento
   - Análise de desempenho da clínica
- **Configurações da Clínica**
   - Definição de horários de atendimento
   - Configuração de serviços e preços

### Funcionalidades Gerais

- **Segurança e Privacidade**
   - Criptografia de ponta a ponta para dados sensíveis
   - Conformidade com LGPD e outras regulamentações
- **Integração com Dispositivos de Saúde**
   - Monitoramento contínuo de dispositivos de saúde conectados
   - Telemetria de dados de saúde em tempo real
- **Suporte ao Usuário**
   - Suporte técnico 24/7 via e-mail, telefone e chat ao vivo
   - Base de conhecimento e FAQs
- **Acessibilidade**
   - Interface amigável e acessível

## Tecnologias Utilizadas

- **Java 21**
- **Quarkus**
- **Maven**
- **IntelliJ IDEA Community Edition**

## Estrutura do Projeto

```plaintext
ClinicManager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   └── test/
│       ├── java/
│       └── resources/
├── target/
├── .gitignore
├── pom.xml
└── README.md
```

## Configuração do Ambiente

### Requisitos

- **Java 21**
- **Maven**
- **Docker** (para serviços de banco de dados e administração)

### Passos para Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/clinicmanager.git
    cd clinicmanager
    ```

2. Configure o banco de dados PostgreSQL com Docker:
    ```bash
    docker run --name clinicmanager --network pgnetwork -e POSTGRES_DB=clinicmanager -e POSTGRES_PASSWORD=SUA_SENHA_SEGURA -d postgres
    ```

3. Configure o pgAdmin com Docker:
    ```bash
    docker run --name pgadmin --network pgnetwork -e PGADMIN_DEFAULT_EMAIL=SEU_EMAIL -e PGADMIN_DEFAULT_PASSWORD=SUA_SENHA_SEGURA -p 80:80 -d dpage/pgadmin4
    ```

4. Compile e rode o projeto:
    ```bash
    mvn clean install
    mvn quarkus:dev
    ```

## Comparativo dos Planos

| Funcionalidades                  | Período de Teste Gratuito  | Plano Básico     | Plano Avançado      | Plano Premium  |
|----------------------------------|----------------------------|------------------|---------------------|---------------|
| Cadastro e login                 | ✓                          | ✓                | ✓                   | ✓             |
| Perfil do paciente e médico      | ✓                          | ✓                | ✓                   | ✓             |
| Agendamento de consultas         | ✓                          | ✓                | ✓                   | ✓             |
| Notificações automáticas         | ✓ (e-mail, SMS e WhatsApp) | ✓ (e-mail e SMS) | ✓ (e-mail, SMS e WhatsApp) | ✓ (e-mail, SMS e WhatsApp)  |
| Videoconferência                 | ✓                          | ✓                | ✓                   | ✓             |
| Chat e troca de mensagens        | ✓                          | ✓                | ✓                   | ✓             |
| Prontuário eletrônico            | ✓                          | ✓                | ✓                   | ✓             |
| Relatórios e análises            | ✓ (customizados)           | ✓ (básicos)      | ✓ (avançados)       | ✓ (customizados)  |
| Integração com dispositivos      | ✓                          | ✗                | ✓                   | ✓             |
| Pagamentos integrados            | ✓                          | ✗                | ✓                   | ✓             |
| Suporte                          | ✓ (24/7 e-mail, telefone e chat ao vivo) | E-mail e chat    | 24/7 e-mail, telefone e chat ao vivo | Suporte prioritário 24/7 com gerente de conta dedicado |
| Limite de pacientes              | Sem limite durante o teste | 100              | 300                 | Ilimitado     |
| Limite de armazenamento          | Sem limite durante o teste | 10 GB            | 30 GB               | Ilimitado     |

## Desenvolvimento Contínuo

O Clinic Manager ainda está em desenvolvimento ativo. Planejamos adicionar novas funcionalidades e melhorias com base no feedback dos usuários e nas necessidades do mercado. Fique atento para atualizações e novas versões do nosso software.

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Fork o projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Contato

Para dúvidas ou suporte, entre em contato com Peter Freitas em [opeterfreitas@gmail.com](mailto:opeterfreitas@gmail.com) ou pelo telefone +55 34 99730-0797.

## Notas de Segurança

- **Variáveis de Ambiente**: Evite colocar informações sensíveis diretamente nos comandos. Use variáveis de ambiente para armazenar dados como senhas e e-mails.
- **Configuração Segura**: Assegure-se de que todas as informações sensíveis estejam protegidas e acessíveis apenas para usuários autorizados.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.

## Referências

- [Repositório Oficial do Quarkus](https://github.com/quarkusio/quarkus)
- [Exemplos de .gitignore para Maven](https://github.com/github/gitignore/blob/main/Maven.gitignore)
- [Documentação de Configuração de Ambiente IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Gist com Exemplos de .gitignore para IntelliJ, Maven e Eclipse](https://gist.github.com/lordofthelake/5833336)
- [Guia de Boas Práticas para README.md](https://www.makeareadme.com/)

---