# Sistema de Estacionamento - CRUD - ETECIA DS

<p align="center">
  <strong>Projeto desenvolvido em Java para gerenciamento de estacionamento</strong><br>
  Atividade de Desenvolvimento de Sistemas - 3º Módulo ETECIA
</p>

---

Sistema CRUD para gerenciamento de veículos em estacionamento com geração de PDF de comprovante, calculando valor baseado nas horas. Desenvolvido com Java Swing no NetBeans.

Proposta do projeto: (https://youtu.be/Hy_yS9zgyNA?si=XlsMMgBAbWWXU-pv)

---

## Screenshots

<div align="center">
    <img src="https://imgur.com/TxvVi4v.png" alt="tela" width="1278px" height="861px">
</div>


<div align="center">
    <img src="https://i.imgur.com/1k9clTG.png" alt="tela" width="839px" height="777px">
</div>

---

## Tecnologias Utilizadas

- **NetBeans** 8.0.2
- **JDK** 8.31
- **MySQL** 5.1.45
- **MySQL Connector** 5.1.16 (driver JDBC)
- **ITextPDF** 5.5.13.2

---

### Instalacao
- NetBeans 8.0.2
- MySQL 5.1.45
- JDK 8.31

### Configuraçao do Banco de Dados

Execute o seguinte script SQL no MySQL para criar a tabela necessária:

```sql
CREATE DATABASE Estacionamento;

USE Estacionamento;

CREATE TABLE Carro (
    NomeCliente VARCHAR(255) NOT NULL,
    Marca VARCHAR(100) NOT NULL,
    Modelo VARCHAR(100) NOT NULL,
    Placa VARCHAR(10) NOT NULL,
    HorarioEntrada DATETIME,
    HorarioSaida DATETIME DEFAULT NULL,
    PRIMARY KEY (Placa)
);
```

### Configuraçao NetBeans

1. Abra o projeto no NetBeans
2. Clique com o botão direito em Bibliotecas
3. Selecione Adicionar JAR/Pasta
4. Carregue o arquivo do MySQL Connector (driver JDBC)
5. Carregue o arquivo do ITextPDF

---

## Funcionalidades

**Entrada de Veículos:** Registro automático de data e hora de entrada. (sem tempo de tolerancia)

* **Cálculo de Cobrança:**
    * Lógica de preço: **R$ 15,00 por hora**.
    * Arredondamento inteligente (cobre frações de hora).
* **Gestão de Pátio:**
    * Visualização em tempo real dos carros estacionados.
    * Edição de dados cadastrais (protegendo o horário de entrada original).
    * Exclusão de registros.
* **Saída e Pagamento:**
    * Trava de segurança que impede cobrar o mesmo veículo duas vezes.
    * Geração de comprovante detalhado na tela.
* **Relatórios e Recibos:**
    * Geração automática de **PDF** dos recibos.
    * Botão de "2ª Via" para carros que já saíram.

---
