package br.edu.ifpr.irati.ads.visao;

import br.edu.ifpr.irati.ads.dao.ProvaDAO;
import br.edu.ifpr.irati.ads.modelo.Prova;
import br.edu.ifpr.irati.ads.modelo.Questao;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class SimuladoGUI extends javax.swing.JFrame {

    private String cpfResposta;

    private Questao questaoAtual;
    private Prova prova;
    private int somaAtual = 0;
    private JCheckBox checkBoxes[];
    private int pontuacao = 60;

    public SimuladoGUI() {
        initComponents();
        String cpf = JOptionPane.showInputDialog(this, "Informe seu CPF", "Identificação do Usuário", JOptionPane.QUESTION_MESSAGE);
        this.cpfResposta = cpf;
        //validar cpf e verificar se tem respostas no banco de dados
        this.cpfResposta = "077.422.381-74";
        initialAdjustments();
        ProvaDAO provaDAO = new ProvaDAO();
        prova = provaDAO.carregarProva();
        DefaultComboBoxModel model
                = new DefaultComboBoxModel<>(prova.getQuestoes().toArray());
        jComboBoxEscolhaQuestao.setModel(model);
        jComboBoxEscolhaQuestao.addItemListener(new ComboBoxHandler());
        this.questaoAtual = prova.getQuestoes().get(0);
        this.atualizarQuestaoTela();

        CheckBoxHandler boxHandler = new CheckBoxHandler();
        jCheckBoxOp01.addItemListener(boxHandler);
        jCheckBoxOp02.addItemListener(boxHandler);
        jCheckBoxOp04.addItemListener(boxHandler);
        jCheckBoxOp08.addItemListener(boxHandler);

        checkBoxes = new JCheckBox[4];
        checkBoxes[0] = jCheckBoxOp01;
        checkBoxes[1] = jCheckBoxOp02;
        checkBoxes[2] = jCheckBoxOp04;
        checkBoxes[3] = jCheckBoxOp08;

    }

    public void initialAdjustments() {
        //deixei esse código caso queiram experimentar outros look and feels
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } else {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                }
            }
        } catch (Exception e) {
        }
        jCheckBoxOp000.setVisible(false);
        jCheckBoxOp16.setVisible(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/assets/prova.png")).getImage());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("Simulado do PSS [" + cpfResposta + "]");
    }

    private boolean[] gerarArrayMarcacao(int v) {
        double valor = v;
        int n_alt = questaoAtual.getAlternativas().length;
        boolean array[] = new boolean[n_alt];
        for (int i = n_alt - 1; i >= 0; i--) {
            double v_alt = Math.pow(2, i);
            if (valor >= v_alt) {
                array[i] = true;
                valor = valor - v_alt;
            } else {
                array[i] = false;
            }
        }
        return array;
    }

    private double corrigirQuestao() {

        int somaGabarito = questaoAtual.getSomaGabarito();
        int somaMarcada = somaAtual;

        boolean arrayGabarito[] = this.gerarArrayMarcacao(somaGabarito);
        boolean arrayMarcado[] = this.gerarArrayMarcacao(somaMarcada);

        int nAlternativasCertas = 0;
        for (int i = 0; i < arrayGabarito.length; i++) {
            if (arrayGabarito[i]) {
                nAlternativasCertas++;
            }
        }

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setForeground(Color.BLACK);
        }

        int nMarcadasCerto = 0;
        int nMarcadasErrado = 0;
        for (int i = 0; i < checkBoxes.length; i++) {

            if (arrayGabarito[i] == true && arrayMarcado[i] == true) {
                checkBoxes[i].setForeground(Color.BLUE);
                nMarcadasCerto++;
            }

            if (arrayGabarito[i] == false && arrayMarcado[i] == true) {
                checkBoxes[i].setForeground(Color.red);
                nMarcadasErrado++;
            }

        }

        if (nMarcadasErrado > 0) {
            return 0;
        } else {
            return 1.0 * this.pontuacao * nMarcadasCerto / nAlternativasCertas;
        }

    }

    public void atualizarQuestaoTela() {
        jLabelDisciplina.setText(
                questaoAtual.getDisciplina().getNome().toUpperCase());
        jLabelNumeroQuestao.setText("QUESTÃO " + questaoAtual.getNumero());
        jTextAreaTextoQuestao.setText(
                questaoAtual.getTextoIntrodutorio() + "\n"
                + questaoAtual.getEnunciado());

        jCheckBoxOp01.setText(getHTML("01) " + questaoAtual.getAlternativas()[0].getTexto()));
        jCheckBoxOp02.setText(getHTML("02) " + questaoAtual.getAlternativas()[1].getTexto()));
        jCheckBoxOp04.setText(getHTML("04) " + questaoAtual.getAlternativas()[2].getTexto()));
        jCheckBoxOp08.setText(getHTML("08) " + questaoAtual.getAlternativas()[3].getTexto()));
        if (questaoAtual.getFigura().equals("")) {
            jButtonImagemQuestao.setEnabled(false);
        } else {
            jButtonImagemQuestao.setEnabled(true);
        }
    }

    private String getHTML(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(input);
        sb.append("</html>");
        return sb.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelEscolhaQuestao = new javax.swing.JPanel();
        jComboBoxEscolhaQuestao = new javax.swing.JComboBox<>();
        jPanelComandos = new javax.swing.JPanel();
        jPanelComandosEsquerda = new javax.swing.JPanel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonProximo = new javax.swing.JButton();
        jPanelComandosDireita = new javax.swing.JPanel();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCorrigir = new javax.swing.JButton();
        jPanelElementosQuestao = new javax.swing.JPanel();
        jPanelElementosQuestaoEsquerda = new javax.swing.JPanel();
        jPanelElementosQuestaoEsquerdaDisciplina = new javax.swing.JPanel();
        jLabelDisciplina = new javax.swing.JLabel();
        jPanelElementosQuestaoEsquerdaEnunciado = new javax.swing.JPanel();
        jPanelElementosQuestaoEsquerdaCentral = new javax.swing.JPanel();
        jPanelElementosQuestaoEsquerdaCentralCabecalho = new javax.swing.JPanel();
        jPanelElementosQuestaoEsquerdaCabecalhoNumero = new javax.swing.JPanel();
        jLabelNumeroQuestao = new javax.swing.JLabel();
        jPanelElementosQuestaoEsquerdaCabecalhoImagem = new javax.swing.JPanel();
        jButtonImagemQuestao = new javax.swing.JButton();
        jPanelElementosQuestaoEsquerdaCentralTexto = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTextoQuestao = new javax.swing.JTextArea();
        jPanelElementosQuestaoDireita = new javax.swing.JPanel();
        jCheckBoxOp000 = new javax.swing.JCheckBox();
        jCheckBoxOp01 = new javax.swing.JCheckBox();
        jCheckBoxOp02 = new javax.swing.JCheckBox();
        jCheckBoxOp04 = new javax.swing.JCheckBox();
        jCheckBoxOp08 = new javax.swing.JCheckBox();
        jCheckBoxOp16 = new javax.swing.JCheckBox();
        jPanelElementosQuestaoDireitaSomatorio = new javax.swing.JPanel();
        jTextFieldSomatorio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelEscolhaQuestao.setLayout(new java.awt.GridLayout(1, 0));

        jComboBoxEscolhaQuestao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxEscolhaQuestao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanelEscolhaQuestao.add(jComboBoxEscolhaQuestao);

        getContentPane().add(jPanelEscolhaQuestao, java.awt.BorderLayout.PAGE_START);

        jPanelComandos.setLayout(new java.awt.GridLayout(1, 2));

        jPanelComandosEsquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonAnterior.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/seta-esquerda.png"))); // NOI18N
        jButtonAnterior.setToolTipText("Ir para a questão anterior");
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });
        jPanelComandosEsquerda.add(jButtonAnterior);

        jButtonProximo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/seta-direita.png"))); // NOI18N
        jButtonProximo.setToolTipText("Ir para a próxima questão.");
        jButtonProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximoActionPerformed(evt);
            }
        });
        jPanelComandosEsquerda.add(jButtonProximo);

        jPanelComandos.add(jPanelComandosEsquerda);

        jPanelComandosDireita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonLimpar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/limpar.png"))); // NOI18N
        jButtonLimpar.setText("Limpar");
        jButtonLimpar.setToolTipText("Limpar as respostas da questão.");
        jPanelComandosDireita.add(jButtonLimpar);

        jButtonCorrigir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonCorrigir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/verifica.png"))); // NOI18N
        jButtonCorrigir.setText("Corrigir");
        jButtonCorrigir.setToolTipText("Corrigir a questão");
        jButtonCorrigir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCorrigirActionPerformed(evt);
            }
        });
        jPanelComandosDireita.add(jButtonCorrigir);

        jPanelComandos.add(jPanelComandosDireita);

        getContentPane().add(jPanelComandos, java.awt.BorderLayout.PAGE_END);

        jPanelElementosQuestao.setLayout(new java.awt.GridLayout(1, 0));

        jPanelElementosQuestaoEsquerda.setLayout(new java.awt.BorderLayout());

        jLabelDisciplina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDisciplina.setText("DISCIPLINA");
        jPanelElementosQuestaoEsquerdaDisciplina.add(jLabelDisciplina);

        jPanelElementosQuestaoEsquerda.add(jPanelElementosQuestaoEsquerdaDisciplina, java.awt.BorderLayout.PAGE_START);

        jPanelElementosQuestaoEsquerdaEnunciado.setLayout(new java.awt.GridLayout(1, 0));
        jPanelElementosQuestaoEsquerda.add(jPanelElementosQuestaoEsquerdaEnunciado, java.awt.BorderLayout.PAGE_END);

        jPanelElementosQuestaoEsquerdaCentral.setLayout(new java.awt.BorderLayout());

        jPanelElementosQuestaoEsquerdaCentralCabecalho.setLayout(new java.awt.GridLayout(1, 0));

        jPanelElementosQuestaoEsquerdaCabecalhoNumero.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelNumeroQuestao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNumeroQuestao.setText("QUESTÃO 1");
        jPanelElementosQuestaoEsquerdaCabecalhoNumero.add(jLabelNumeroQuestao);

        jPanelElementosQuestaoEsquerdaCentralCabecalho.add(jPanelElementosQuestaoEsquerdaCabecalhoNumero);

        jPanelElementosQuestaoEsquerdaCabecalhoImagem.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImagemQuestao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonImagemQuestao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/galeria.png"))); // NOI18N
        jButtonImagemQuestao.setText("Ver imagem");
        jButtonImagemQuestao.setToolTipText("Ver imagens associadas à questão.");
        jButtonImagemQuestao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImagemQuestaoActionPerformed(evt);
            }
        });
        jPanelElementosQuestaoEsquerdaCabecalhoImagem.add(jButtonImagemQuestao);

        jPanelElementosQuestaoEsquerdaCentralCabecalho.add(jPanelElementosQuestaoEsquerdaCabecalhoImagem);

        jPanelElementosQuestaoEsquerdaCentral.add(jPanelElementosQuestaoEsquerdaCentralCabecalho, java.awt.BorderLayout.PAGE_START);

        jPanelElementosQuestaoEsquerdaCentralTexto.setLayout(new java.awt.GridLayout(1, 0));

        jTextAreaTextoQuestao.setEditable(false);
        jTextAreaTextoQuestao.setBackground(getBackground());
        jTextAreaTextoQuestao.setColumns(350);
        jTextAreaTextoQuestao.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jTextAreaTextoQuestao.setLineWrap(true);
        jTextAreaTextoQuestao.setRows(30);
        jTextAreaTextoQuestao.setText("Considerando o movimento do caule em direção à luz e a ação da auxina, observe a imagem abaixo e assinale o que for correto.");
        jTextAreaTextoQuestao.setDisabledTextColor(getBackground());
        jScrollPane1.setViewportView(jTextAreaTextoQuestao);

        jPanelElementosQuestaoEsquerdaCentralTexto.add(jScrollPane1);

        jPanelElementosQuestaoEsquerdaCentral.add(jPanelElementosQuestaoEsquerdaCentralTexto, java.awt.BorderLayout.CENTER);

        jPanelElementosQuestaoEsquerda.add(jPanelElementosQuestaoEsquerdaCentral, java.awt.BorderLayout.CENTER);

        jPanelElementosQuestao.add(jPanelElementosQuestaoEsquerda);

        jPanelElementosQuestaoDireita.setLayout(new java.awt.GridLayout(7, 1));

        jCheckBoxOp000.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp000.setText("...");
        jPanelElementosQuestaoDireita.add(jCheckBoxOp000);

        jCheckBoxOp01.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp01.setText("<html>01) O crescimento orientado da planta em resposta a um estímulo luminoso, tal como o observado na imagem acima, é chamado de fototropismo. </html>");
        jCheckBoxOp01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxOp01ActionPerformed(evt);
            }
        });
        jPanelElementosQuestaoDireita.add(jCheckBoxOp01);

        jCheckBoxOp02.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp02.setText("A imagem representa um caso de nastismo, em que a plântula não responde ao estímulo externo");
        jPanelElementosQuestaoDireita.add(jCheckBoxOp02);

        jCheckBoxOp04.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp04.setText("O fototropismo, diferente de outros tropismos, pode ser apenas positivo, sendo observado tanto no caule como na raiz.");
        jPanelElementosQuestaoDireita.add(jCheckBoxOp04);

        jCheckBoxOp08.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp08.setText("A luz faz com que a auxina seja translocada para o lado sombreado do ápice em crescimento, onde estimula o alongamento celular, promovendo a curvatura do caule em direção à fonte de luz.");
        jPanelElementosQuestaoDireita.add(jCheckBoxOp08);

        jCheckBoxOp16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jCheckBoxOp16.setText("...");
        jPanelElementosQuestaoDireita.add(jCheckBoxOp16);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanelElementosQuestaoDireitaSomatorio.setLayout(flowLayout1);

        jTextFieldSomatorio.setEditable(false);
        jTextFieldSomatorio.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jTextFieldSomatorio.setText("      ");
        jTextFieldSomatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSomatorioActionPerformed(evt);
            }
        });
        jPanelElementosQuestaoDireitaSomatorio.add(jTextFieldSomatorio);

        jPanelElementosQuestaoDireita.add(jPanelElementosQuestaoDireitaSomatorio);

        jPanelElementosQuestao.add(jPanelElementosQuestaoDireita);

        getContentPane().add(jPanelElementosQuestao, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImagemQuestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImagemQuestaoActionPerformed
        ImagemGUI imagemGUI
                = new ImagemGUI(questaoAtual.getFigura(),
                        "Imagem da Questão " + questaoAtual.getNumero());
        imagemGUI.setVisible(true);
    }//GEN-LAST:event_jButtonImagemQuestaoActionPerformed

    private void jButtonCorrigirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCorrigirActionPerformed
        double pontuacao = this.corrigirQuestao();
        JOptionPane.showMessageDialog(this, pontuacao);
    }//GEN-LAST:event_jButtonCorrigirActionPerformed

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        int index = prova.getQuestoes().indexOf(questaoAtual);
        if (index > 0) {
            questaoAtual = prova.getQuestoes().get(index - 1);
            atualizarQuestaoTela();
        } else {
            JOptionPane.showMessageDialog(this, "Você já está na primeira questão.");
        }
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximoActionPerformed
        int index = prova.getQuestoes().indexOf(questaoAtual);
        if (index < prova.getQuestoes().size() - 1) {
            questaoAtual = prova.getQuestoes().get(index + 1);
            atualizarQuestaoTela();
        } else {
            JOptionPane.showMessageDialog(this, "Você já está na última questão.");
        }
    }//GEN-LAST:event_jButtonProximoActionPerformed

    private class ComboBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            questaoAtual = (Questao) jComboBoxEscolhaQuestao.getSelectedItem();
            atualizarQuestaoTela();
        }
    }

    private class CheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            JCheckBox box = (JCheckBox) e.getSource();
            if (box == jCheckBoxOp01) {
                if (box.isSelected()) {
                    somaAtual += 1;
                } else {
                    somaAtual -= 1;
                }
            }
            if (box == jCheckBoxOp02) {
                if (box.isSelected()) {
                    somaAtual += 2;
                } else {
                    somaAtual -= 2;
                }
            }
            if (box == jCheckBoxOp04) {
                if (box.isSelected()) {
                    somaAtual += 4;
                } else {
                    somaAtual -= 4;
                }
            }
            if (box == jCheckBoxOp08) {
                if (box.isSelected()) {
                    somaAtual += 8;
                } else {
                    somaAtual -= 8;
                }
            }
            jTextFieldSomatorio.setText(String.valueOf(somaAtual));

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimuladoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimuladoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimuladoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimuladoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimuladoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonCorrigir;
    private javax.swing.JButton jButtonImagemQuestao;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonProximo;
    private javax.swing.JCheckBox jCheckBoxOp000;
    private javax.swing.JCheckBox jCheckBoxOp01;
    private javax.swing.JCheckBox jCheckBoxOp02;
    private javax.swing.JCheckBox jCheckBoxOp04;
    private javax.swing.JCheckBox jCheckBoxOp08;
    private javax.swing.JCheckBox jCheckBoxOp16;
    private javax.swing.JComboBox<String> jComboBoxEscolhaQuestao;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelNumeroQuestao;
    private javax.swing.JPanel jPanelComandos;
    private javax.swing.JPanel jPanelComandosDireita;
    private javax.swing.JPanel jPanelComandosEsquerda;
    private javax.swing.JPanel jPanelElementosQuestao;
    private javax.swing.JPanel jPanelElementosQuestaoDireita;
    private javax.swing.JPanel jPanelElementosQuestaoDireitaSomatorio;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerda;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaCabecalhoImagem;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaCabecalhoNumero;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaCentral;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaCentralCabecalho;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaCentralTexto;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaDisciplina;
    private javax.swing.JPanel jPanelElementosQuestaoEsquerdaEnunciado;
    private javax.swing.JPanel jPanelEscolhaQuestao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaTextoQuestao;
    private javax.swing.JTextField jTextFieldSomatorio;
    // End of variables declaration//GEN-END:variables
}
