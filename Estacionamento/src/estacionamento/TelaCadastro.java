package estacionamento;

//imports necessarios para o projeto
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class TelaCadastro extends javax.swing.JFrame {
    // Configuracao do Banco
    String url = "jdbc:mysql://localhost:3306/Estacionamento?serverTimezone=America/Sao_Paulo";
    String usuario = "root";
    String senha = "123";
    
    public TelaCadastro() {
        initComponents();
        carregarTabela();
        
        // this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // inicializa o programa maximizado
        
        // Escondendo algumas coisas do JFrame
        lblPlacaOriginal.setVisible(false); // essa label serve para atualizar a placa no banco de dados, guardando a original
        txtEntrada.setVisible(false); // essa e a linha de baixo guardam o horario de entrada e saida para preencher a tabela
        txtSaida.setVisible(false);
    }

    private void limparCampos() {
        txtNomeCliente.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtPlaca.setText("");
        txtEntrada.setText("");
        txtRecibo.setText("");  
        lblPlacaOriginal.setText("");
        txtNomeCliente.requestFocus();
    }
    
private java.sql.Timestamp getDataHora() {
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.SECOND, 0);
    cal.set(java.util.Calendar.MILLISECOND, 0);
    // Tive alguns bugs com o horario, o registro de saida marcava uma hora adiantada
    // adicionei o timezone no url do banco e erro parou de ocorrer, mas em outras maquinas mesmo com esse timezone o bug ocorria
    // caso timezone não funcione na sua maquina tambem, descomente a linha abaixo para subtrair 1 hora:
    cal.add(java.util.Calendar.HOUR_OF_DAY, -1);
    return new java.sql.Timestamp(cal.getTimeInMillis());
}

private void gerarPDF(String nomeCliente, String placa, String textoRecibo) {
    Document doc = new Document();
    
    try {
        // Gera um nome de arquivo único (ex: Recibo_ABC1234.pdf)
        // O arquivo será salvo na pasta raiz do seu projeto
        String nomeArquivo = "Recibo_" + placa + ".pdf";
        
        PdfWriter.getInstance(doc, new FileOutputStream(nomeArquivo));
        doc.open();
        
        // Adiciona um título
        com.itextpdf.text.Font fonteTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 20, com.itextpdf.text.Font.BOLD);
        Paragraph titulo = new Paragraph("Comprovante de Estacionamento", fonteTitulo);
        titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        doc.add(titulo);
        
        // Adiciona um espaço
        doc.add(new Paragraph(" "));
        
        // Adiciona o corpo do recibo (usamos uma fonte monoespaçada para ficar alinhado)
        com.itextpdf.text.Font fonteCorpo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12);
        doc.add(new Paragraph(textoRecibo, fonteCorpo));
        
        doc.close();
        
        JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!\nArquivo: " + nomeArquivo);
        
        // Opcional: Tentar abrir o arquivo automaticamente
        try {
            java.awt.Desktop.getDesktop().open(new java.io.File(nomeArquivo));
        } catch (Exception e) {
            // Se não conseguir abrir, não faz nada (o arquivo já foi salvo)
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao gerar PDF: " + e.getMessage());
    }
}

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblCarros.getModel(); // modelo para tabela
        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(new Object[]{"Cliente", "Marca", "Modelo", "Placa", "Entrada", "Saída"}); // colunas da tabela jframe

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Carro"); // acessa a tabela
        ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            modelo.addRow(new Object[]{ // utiliza os dados do banco criando um novo objeto
                rs.getString("NomeCliente"),
                rs.getString("Marca"),
                rs.getString("Modelo"),
                rs.getString("Placa"),
                rs.getString("HorarioEntrada"), 
                rs.getString("HorarioSaida")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar: " + e.getMessage());
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtNomeCliente = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        lblPlacaOriginal = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        txtSaida = new javax.swing.JTextField();
        btnSaida = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRecibo = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        btnVerRecibo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        tblCarros.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        tblCarros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCarros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCarros);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        jLabel2.setText("Marca:");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        jLabel3.setText("Modelo:");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        jLabel4.setText("Placa:");

        btnCadastrar.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        lblPlacaOriginal.setText("jLabel5");

        txtEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntradaActionPerformed(evt);
            }
        });

        txtSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaidaActionPerformed(evt);
            }
        });

        btnSaida.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnSaida.setText("Registrar Saída");
        btnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaidaActionPerformed(evt);
            }
        });

        txtRecibo.setColumns(20);
        txtRecibo.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtRecibo.setRows(5);
        jScrollPane2.setViewportView(txtRecibo);

        jTextField1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jTextField1.setText("ESTACIONAMENTO");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnVerRecibo.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        btnVerRecibo.setText("Gerar Recibo");
        btnVerRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReciboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlaca)
                            .addComponent(txtModelo))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnCadastrar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSaida))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnEditar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnExcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLimpar)))
                                .addGap(49, 49, 49))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblPlacaOriginal)
                                .addGap(18, 18, 18)
                                .addComponent(txtSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerRecibo)
                                .addGap(50, 50, 50))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addComponent(txtNomeCliente))
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastrar)
                            .addComponent(btnSaida))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar)
                            .addComponent(btnExcluir)
                            .addComponent(btnLimpar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(lblPlacaOriginal)
                            .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnVerRecibo)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCarrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarrosMouseClicked
        // quando clica na tabela preenche os formularios
        int linha = tblCarros.getSelectedRow();

    if (linha >= 0) {
        // preenche os campos
        txtNomeCliente.setText(tblCarros.getValueAt(linha, 0).toString());
        txtMarca.setText(tblCarros.getValueAt(linha, 1).toString());
        txtModelo.setText(tblCarros.getValueAt(linha, 2).toString());
        txtPlaca.setText(tblCarros.getValueAt(linha, 3).toString());
        lblPlacaOriginal.setText(tblCarros.getValueAt(linha, 3).toString());
        Object verEntrada = tblCarros.getValueAt(linha, 4);
        Object verSaida = tblCarros.getValueAt(linha, 5);
        
        txtEntrada.setText(verEntrada != null ? verEntrada.toString() : "");
        
        // Se a saída for null no banco, deixa a caixa vazia na tela
        txtSaida.setText(verSaida != null ? verSaida.toString() : "");

        // gera comprovante quando seleciona usuario na tabela
        String placa = tblCarros.getValueAt(linha, 3).toString();
        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            String sql = "SELECT NomeCliente, HorarioEntrada, HorarioSaida FROM Carro WHERE Placa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NomeCliente");
                java.sql.Timestamp entrada = rs.getTimestamp("HorarioEntrada");
                java.sql.Timestamp saida = rs.getTimestamp("HorarioSaida");
                
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
                StringBuilder sb = new StringBuilder();

                sb.append("============================================\n");
                // muda o titulo dependendo se ja saiu ou não
                if (saida == null) {
                    sb.append("      VEÍCULO NO PÁTIO (EM ABERTO)       \n");
                } else {
                    sb.append("      RECIBO DE SAÍDA (FECHADO)          \n");
                }
                sb.append("============================================\n");
                sb.append("Cliente: ").append(nome).append("\n");
                sb.append("Placa:   ").append(placa).append("\n");
                sb.append("--------------------------------------------\n");
                sb.append("Entrada: ").append(entrada != null ? sdf.format(entrada) : "--").append("\n");
                
                // logica do horario de saida
                if (saida == null) {
                    // se nao saiu, nao mostra hora, mostra aviso
                    sb.append("Saída:   -- AGUARDANDO SAÍDA --\n");
                    sb.append("Tempo:   -- Contando --\n");
                    sb.append("--------------------------------------------\n");
                    sb.append("TOTAL:   R$ --,--\n");
                } else {
                    // se ja saiu, mostra os calculos fechados
                    sb.append("Saída:   ").append(sdf.format(saida)).append("\n");
                    
                    long diferenca = saida.getTime() - entrada.getTime();
                    double horas = Math.ceil(diferenca / (1000.0 * 60 * 60));
                    if (horas == 0) horas = 1;
                    double valorTotal = horas * 15.00;
                    
                    sb.append("Tempo:   ").append((int)horas).append(" horas\n");
                    sb.append("--------------------------------------------\n");
                    sb.append("TOTAL:   R$ ").append(String.format("%.2f", valorTotal)).append("\n");
                }
                sb.append("============================================");
                
                txtRecibo.setText(sb.toString());
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblCarrosMouseClicked
    }
    
        
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
        Connection conn = DriverManager.getConnection(url, usuario, senha);
        String sql = "INSERT INTO Carro (NomeCliente, Marca, Modelo, Placa, HorarioEntrada, HorarioSaida) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, txtNomeCliente.getText());
        stmt.setString(2, txtMarca.getText());
        stmt.setString(3, txtModelo.getText());
        stmt.setString(4, txtPlaca.getText());
       
        stmt.setTimestamp(5, getDataHora());

        stmt.setNull(6, java.sql.Types.TIMESTAMP); // saida null

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Carro Cadastrado! Horário iniciado.");
        
        carregarTabela();
        limparCampos();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (lblPlacaOriginal.getText().isEmpty()) { // Verifica se alguma placa foi selecionada antes       
        JOptionPane.showMessageDialog(this, "Selecione um carro para editar.");
        return;
    }

    try {
        Connection conn = DriverManager.getConnection(url, usuario, senha);
        // SQL de atualizacao
        String sql = "UPDATE Carro SET NomeCliente=?, Marca=?, Modelo=?, Placa=?, HorarioSaida=? WHERE Placa=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, txtNomeCliente.getText());
        stmt.setString(2, txtMarca.getText());
        stmt.setString(3, txtModelo.getText());
        stmt.setString(4, txtPlaca.getText());

        // Se a saida estiver vazia na tela, manda NULL (vazio) pro banco, isso impede que ele grave a data atual ou "0000-00-00"
        if (txtSaida.getText().trim().isEmpty()) {
            stmt.setNull(5, java.sql.Types.TIMESTAMP);
        } else {
            stmt.setString(5, txtSaida.getText());
        }
        
        stmt.setString(6, lblPlacaOriginal.getText()); // < -- lbl da placa original aqui

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Dados Atualizados!");
        
        carregarTabela();
        limparCampos();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao editar (Verifique o formato da data): " + e.getMessage());
    }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (lblPlacaOriginal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um carro na tabela para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este veículo?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = DriverManager.getConnection(url, usuario, senha);
                String sql = "DELETE FROM Carro WHERE Placa=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, lblPlacaOriginal.getText()); // Usa a placa original para garantir que deleta o certo
 
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Carro Removido!");
                carregarTabela();
                limparCampos();
                conn.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaidaActionPerformed
        if (lblPlacaOriginal.getText().isEmpty()) { // verifica se selecionou um carro
        JOptionPane.showMessageDialog(this, "Selecione um carro na tabela para registrar a saída.");
        return;
    }

    String placa = lblPlacaOriginal.getText();

    try {
        Connection conn = DriverManager.getConnection(url, usuario, senha);
        
        // busca Entrada e Saida para verificar o status
        String sqlBusca = "SELECT HorarioEntrada, HorarioSaida, NomeCliente FROM Carro WHERE Placa = ?";
        PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
        stmtBusca.setString(1, placa);
        ResultSet rs = stmtBusca.executeQuery();
        
        if (rs.next()) {
            java.sql.Timestamp entrada = rs.getTimestamp("HorarioEntrada");
            java.sql.Timestamp saidaJaRegistrada = rs.getTimestamp("HorarioSaida"); // Pega a saida do banco
            String nomeCliente = rs.getString("NomeCliente");
            
            if (saidaJaRegistrada != null) {
                JOptionPane.showMessageDialog(this, "ERRO: Este veículo JÁ SAIU!\nA saída foi registrada em: " + saidaJaRegistrada);
                conn.close();
                return;
            }

            if (entrada == null) {
                JOptionPane.showMessageDialog(this, "ERRO: Carro sem horário de entrada. Não é possível calcular.");
                conn.close();
                return;
            }
            
            // Pega hora atual e calcula a diferença e o valor a ser pago
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.SECOND, 0);
            cal.set(java.util.Calendar.MILLISECOND, 0);
            java.sql.Timestamp saidaAgora = new java.sql.Timestamp(cal.getTimeInMillis());
            
            long diferenca = saidaAgora.getTime() - entrada.getTime();
            
            if (diferenca < 0) {
                 JOptionPane.showMessageDialog(this, "ERRO: Hora de saída anterior à entrada. Verifique seu relógio.");
                 conn.close();
                 return;
            }

            double horas = Math.ceil(diferenca / (1000.0 * 60 * 60));
            if (horas == 0) horas = 1;
            double valorTotal = horas * 15.00;
                     
            // Atualiza no Banco
            String sqlUpdate = "UPDATE Carro SET HorarioSaida = ? WHERE Placa = ?";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setTimestamp(1, saidaAgora);
            stmtUpdate.setString(2, placa);
            stmtUpdate.executeUpdate();
            
            // gera recibo:
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            StringBuilder sb = new StringBuilder();

            sb.append("============================================\n");
            sb.append("      ESTACIONAMENTO - RECIBO       \n");
            sb.append("============================================\n");
            sb.append("Cliente: ").append(nomeCliente).append("\n");
            sb.append("Placa:   ").append(placa).append("\n");
            sb.append("--------------------------------------------\n");
            
            if (entrada != null) {
                sb.append("Entrada: ").append(sdf.format(entrada)).append("\n");
            } else {
                sb.append("Entrada: -- Data Inválida --\n");
            }

            if (saidaAgora != null) {
                 sb.append("Saída:   ").append(sdf.format(saidaAgora)).append("\n");
            } else {
                 sb.append("Saída:   -- Erro na Saída --\n");
            }

            sb.append("Tempo:   ").append((int)horas).append(" horas\n");
            sb.append("--------------------------------------------\n");
            sb.append("TOTAL:   R$ ").append(String.format("%.2f", valorTotal)).append("\n");
            sb.append("============================================");

            txtRecibo.setText(sb.toString());

            // gera PDF
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja gerar o PDF do recibo?", "PDF", JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                gerarPDF(nomeCliente, placa, sb.toString());
            }

                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");
                carregarTabela();
                limparCampos();
            }

            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro SQL: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaidaActionPerformed

    private void txtSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaidaActionPerformed
    }//GEN-LAST:event_txtSaidaActionPerformed

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntradaActionPerformed
    }//GEN-LAST:event_txtEntradaActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnVerReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReciboActionPerformed
        if (lblPlacaOriginal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um carro na tabela para gerar o recibo.");
            return;
        }

        String placa = lblPlacaOriginal.getText();

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);

            // busca os dados (Entrada e Saida)
            String sql = "SELECT NomeCliente, HorarioEntrada, HorarioSaida FROM Carro WHERE Placa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NomeCliente");
                java.sql.Timestamp entrada = rs.getTimestamp("HorarioEntrada");
                java.sql.Timestamp saida = rs.getTimestamp("HorarioSaida");

                if (entrada == null) {
                    JOptionPane.showMessageDialog(this, "Erro: Este veículo não tem horário de entrada.");
                    conn.close(); return;
                }

                java.sql.Timestamp horaFinalCalculo;
                String statusRecibo;

                if (saida != null) {
                    horaFinalCalculo = saida;
                    statusRecibo = "      RECIBO (2ª VIA - FECHADO)     ";
                } else {
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(java.util.Calendar.SECOND, 0);
                    cal.set(java.util.Calendar.MILLISECOND, 0);
                    horaFinalCalculo = new java.sql.Timestamp(cal.getTimeInMillis());
                    statusRecibo = "   PRÉVIA DE PAGAMENTO (ABERTO)   ";
                }

                // matematica do valor
                long diferenca = horaFinalCalculo.getTime() - entrada.getTime();
                double horas = Math.ceil(diferenca / (1000.0 * 60 * 60));
                if (horas == 0) horas = 1;
                double valorTotal = horas * 15.00;

                // --- GERAÇÃO DO TEXTO ---
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
                StringBuilder sb = new StringBuilder();

                sb.append("============================================\n");
                sb.append(statusRecibo).append("\n");
                sb.append("============================================\n");
                sb.append("Cliente: ").append(nome).append("\n");
                sb.append("Placa:   ").append(placa).append("\n");
                sb.append("--------------------------------------------\n");
                sb.append("Entrada: ").append(sdf.format(entrada)).append("\n");
                sb.append("Saída:   ").append(sdf.format(horaFinalCalculo)).append("\n");
                sb.append("Tempo:   ").append((int)horas).append(" horas\n");
                sb.append("--------------------------------------------\n");
                sb.append("TOTAL:   R$ ").append(String.format("%.2f", valorTotal)).append("\n");
                sb.append("============================================");

                txtRecibo.setText(sb.toString());

                int opcao = JOptionPane.showConfirmDialog(this, "Deseja salvar este recibo em PDF?", "PDF", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    gerarPDF(nome, placa, sb.toString());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Carro não encontrado.");
            }
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVerReciboActionPerformed
                                     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSaida;
    private javax.swing.JButton btnVerRecibo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblPlacaOriginal;
    private javax.swing.JTable tblCarros;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextArea txtRecibo;
    private javax.swing.JTextField txtSaida;
    // End of variables declaration//GEN-END:variables
}
