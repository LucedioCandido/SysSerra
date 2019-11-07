/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteRelatorios;

import Interfaces.Principal;
import static Interfaces.Principal.serCpfCliente;
import static Interfaces.Principal.servicoCpfCliente;
import PacoteConexaoBanco.ConexaoBanco;
import static PacoteConexaoBanco.ConexaoBanco.conectar;
import static PacoteConexaoBanco.ConexaoBanco.estado;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Mezenga
 */
public class RelatorioOS {

    // Ordem de serviço chamada quando clica na tabela e no botão imprimir
    public void relClienteF(String cpf, String nome, String rua, String n, String bairro, String cidade, String est, String telefone) {
        String resp = serCpfCliente;
        System.out.println("Resp " + resp);

        conectar();
        //Imprimindo Relatorio Itext
        String sql = " select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Cliente.Rua_Cliente, Cliente.Numero_Cliente, Cliente.Rua_Cliente,"
                + " Cliente.Cidade_Cliente, Cliente.Bairro_Cliente, Cliente.Estado_Cliente, Cliente.Tel_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,\n"
                + " Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.QuantidadeM2, Servico.Lucro, Usuario_do_Sistema.Nome_Usuario     \n"
                + " from Servico, Cliente, Usuario_do_Sistema\n"
                + " where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario\n"
                + " and Cliente.Cpf_Cliente ='" + cpf + "'";

        Document doc = new Document(PageSize.A4, 30, 20, 30, 20);

        // Criando o arquivo de saída.
        Date date = new Date(); //pega a data e hora do sistema

        //System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String data = form.format(date);

        String dat = data.substring(0, 10);
        //System.out.println("data formata " + Data_Inicio);
        String novaData = data.substring(11, 19);

        try {
            ResultSet resultado = estado.executeQuery(sql); // executa a consulta
            
            PdfWriter.getInstance(doc, new FileOutputStream("wd.pdf")); // PDF
            // Abrindo o documento para a edição
            doc.open();

            // Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
            Font ff = new Font(Font.FontFamily.COURIER, 14, Font.BOLD + Font.ITALIC + Font.UNDERLINE);
            Font ffo = new Font(Font.FontFamily.COURIER, 13, Font.BOLD + Font.ITALIC);
            Font fontTable = new Font(Font.FontFamily.HELVETICA, 7);
            Font fontTable2 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB())); // fonte da tabela

            Font fontTableTit = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));

            // Definindo uma fonte, com tamanho 12 e negrito
            Font font = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);

            // Definindo uma fonte, com tamanho 12 e negrito
            Font fonte = new Font(Font.FontFamily.HELVETICA, 11);
            //Font fonte = new Font(Font.FontFamily.COURIER, 12);

            Font fontinha = new Font(Font.FontFamily.COURIER, 12 + Font.UNDERLINE);

            //Image imagem = Image.getInstance("MeuLogo.png");
            //imagem.setIndentationLeft(20);
            //doc.add(imagem);
            Paragraph titulo = new Paragraph(" Serralharia Gabriel", ffo);
            // Setando o alinhamento p/ o centro
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            // Definindo espaço depois do parágrafo
            // titulo.setSpacingAfter(50);
            doc.add(titulo);

            Paragraph titulo2 = new Paragraph(" Tel: (84) 3248-2530 / (84)9 8850-2699", ffo);
            // Setando o alinhamento p/ o centro
            titulo2.setAlignment(Paragraph.ALIGN_CENTER);
            // Definindo espaço depois do parágrafo
            //titulo2.setSpacingAfter(50);
            doc.add(titulo2);

            Paragraph titulo3 = new Paragraph(" Av: Assis Chateaubriant  Nº5000", ffo);
            // Setando o alinhamento p/ o centro
            titulo3.setAlignment(Paragraph.ALIGN_CENTER);
            // Definindo espaço depois do parágrafo
            titulo3.setSpacingAfter(10);
            doc.add(titulo3);

            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            Paragraph p = new Paragraph("                                                             ", ff);
            // Setando o alinhamento p/ o centro
            p.setAlignment(Paragraph.ALIGN_CENTER);

            // Definindo espaço depois do parágrafo
            p.setSpacingAfter(30);
            doc.add(p);

            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            Paragraph dadosCliente = new Paragraph("Dados do cliente", ffo);
            // Setando o alinhamento p/ o centro
            dadosCliente.setAlignment(Paragraph.ALIGN_CENTER);
            // Definindo espaço depois do parágrafo
            dadosCliente.setSpacingAfter(10);
            doc.add(dadosCliente);

            Paragraph a = new Paragraph("       CPF: " + cpf + "", fonte);
            // Setando o alinhamento p/ o centro
            a.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            a.setSpacingAfter(1);
            doc.add(a);

            Paragraph c = new Paragraph("       Nome: " + nome + "", fonte);
            // Setando o alinhamento p/ o centro
            c.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            c.setSpacingAfter(1);
            doc.add(c);

            Paragraph x = new Paragraph("       Contato: " + telefone + "", fonte);
            // Setando o alinhamento p/ o centro
            c.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            c.setSpacingAfter(1);
            doc.add(x);

            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            Paragraph d = new Paragraph("       Endereço: " + "Rua " + rua + "    N°: " + n + "   ", fonte);
            // Setando o alinhamento p/ o centro
            d.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            d.setSpacingAfter(1);
            doc.add(d);

            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            Paragraph e = new Paragraph("       Bairro: " + bairro + "    Cidade: " + cidade + "", fonte);
            // Setando o alinhamento p/ o centro
            e.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            e.setSpacingAfter(1);
            doc.add(e);

            // Adicionando um parágrafo ao PDF, com a fonte definida acima
            Paragraph ed = new Paragraph("       Estado: " + est + "", fonte);
            // Setando o alinhamento p/ o centro
            ed.setAlignment(Paragraph.ALIGN_LEFT);
            // Definindo espaço depois do parágrafo
            ed.setSpacingAfter(30);
            doc.add(ed);

            // Tabela
            PdfPTable table = new PdfPTable(11);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
            table.setWidths(new int[]{4, 5, 6, 5, 5, 5, 5, 5, 5, 5, 5});

            // célula da tabela
            PdfPCell celulaTitulo = new PdfPCell(new Phrase("Ordem de Serviço", fontTableTit));
            celulaTitulo.setBorder(celulaTitulo.NO_BORDER);
            celulaTitulo.setHorizontalAlignment(celulaTitulo.ALIGN_CENTER);
            celulaTitulo.setVerticalAlignment(celulaTitulo.ALIGN_MIDDLE);
            celulaTitulo.setPaddingTop(5);
            celulaTitulo.setPaddingBottom(6);
            celulaTitulo.setBackgroundColor(new BaseColor(102, 102, 102));
            celulaTitulo.setColspan(11);
            table.addCell(celulaTitulo);

            // célula da tabela
            PdfPCell celulaCodigo = new PdfPCell(new Phrase("Código", fontTable2));
            celulaCodigo.setBorder(celulaCodigo.NO_BORDER);
            celulaCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaCodigo.setPaddingTop(4);
            celulaCodigo.setPaddingBottom(5);
            celulaCodigo.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaNome = new PdfPCell(new Phrase("Descrição", fontTable2));
            celulaNome.setBorder(celulaNome.NO_BORDER);
            celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaNome.setPaddingTop(4);
            celulaNome.setPaddingBottom(5);
            celulaNome.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaCliente = new PdfPCell(new Phrase("Cliente", fontTable2));
            celulaCliente.setBorder(celulaCliente.NO_BORDER);
            celulaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaCliente.setPaddingTop(4);
            celulaCliente.setPaddingBottom(5);
            celulaCliente.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaInicio = new PdfPCell(new Phrase("Início", fontTable2));
            celulaInicio.setBorder(celulaInicio.NO_BORDER);
            celulaInicio.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaInicio.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaInicio.setPaddingTop(4);
            celulaInicio.setPaddingBottom(5);
            celulaInicio.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaEntrega = new PdfPCell(new Phrase(" Entrega", fontTable2));
            celulaEntrega.setBorder(celulaEntrega.NO_BORDER);
            celulaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaEntrega.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaEntrega.setPaddingTop(4);
            celulaEntrega.setPaddingBottom(5);
            celulaEntrega.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaPagamento = new PdfPCell(new Phrase("Pagamento", fontTable2));
            celulaPagamento.setBorder(celulaPagamento.NO_BORDER);
            celulaPagamento.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaPagamento.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaPagamento.setPaddingTop(4);
            celulaPagamento.setPaddingBottom(5);
            celulaPagamento.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", fontTable2));
            celulaValor.setBorder(celulaValor.NO_BORDER);
            celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaValor.setPaddingTop(4);
            celulaValor.setPaddingBottom(5);
            celulaValor.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaStatus = new PdfPCell(new Phrase("Status", fontTable2));
            celulaStatus.setBorder(celulaStatus.NO_BORDER);
            celulaStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaStatus.setPaddingTop(4);
            celulaStatus.setPaddingBottom(5);
            celulaStatus.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaMao = new PdfPCell(new Phrase("Mão de Obra", fontTable2));
            celulaMao.setBorder(celulaMao.NO_BORDER);
            celulaMao.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaMao.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaMao.setPaddingTop(4);
            celulaMao.setPaddingBottom(5);
            celulaMao.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaLogin = new PdfPCell(new Phrase("Usuário", fontTable2));
            celulaLogin.setBorder(celulaLogin.NO_BORDER);
            celulaLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaLogin.setPaddingTop(4);
            celulaLogin.setPaddingBottom(5);
            celulaLogin.setBackgroundColor(new BaseColor(255, 176, 0));

            // célula da tabela
            PdfPCell celulaLucro = new PdfPCell(new Phrase("Lucro", fontTable2));
            celulaLucro.setBorder(celulaLucro.NO_BORDER);
            celulaLucro.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaLucro.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celulaLucro.setPaddingTop(4);
            celulaLucro.setPaddingBottom(5);
            celulaLucro.setBackgroundColor(new BaseColor(255, 176, 0));

            //PdfPCell celCodigo = new PdfPCell(new Phrase("Preço", fontTable));
            //celulaValor.setBackgroundColor(BaseColor.ORANGE);
            //celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
            // adionando as células da tabela
            table.addCell(celulaCodigo);
            table.addCell(celulaNome);
            table.addCell(celulaCliente);
            table.addCell(celulaInicio);
            table.addCell(celulaEntrega);
            table.addCell(celulaPagamento);
            table.addCell(celulaValor);
            table.addCell(celulaStatus);
            table.addCell(celulaMao);
            table.addCell(celulaLucro);
            table.addCell(celulaLogin);

            // Loop da consulta ao banco de dados
            while (resultado.next()) {

                // Adicionando célula com os dados do banco
                PdfPCell celCodigo = new PdfPCell(new Phrase(resultado.getString("Servico.Cod_Servico"), fontTable));
                celCodigo.setPadding(5);
                celCodigo.setBorder(celCodigo.NO_BORDER);
                celCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celDescricao = new PdfPCell(new Phrase(resultado.getString("Servico.Descricao_Servico"), fontTable));
                celDescricao.setPadding(5);
                celDescricao.setBorder(celDescricao.NO_BORDER);
                celDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
                celDescricao.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celCliente = new PdfPCell(new Phrase(resultado.getString("Cliente.Nome_Cliente"), fontTable));
                celCliente.setPadding(5);
                celCliente.setBorder(celCliente.NO_BORDER);
                celCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                celCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celQuantidade = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Inicio"), fontTable));
                celQuantidade.setPadding(5);
                celQuantidade.setBorder(celQuantidade.NO_BORDER);
                celQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                celQuantidade.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celTamanho = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Entrega"), fontTable));
                celTamanho.setPadding(5);
                celTamanho.setBorder(celTamanho.NO_BORDER);
                celTamanho.setHorizontalAlignment(Element.ALIGN_CENTER);
                celTamanho.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celPag = new PdfPCell(new Phrase(resultado.getString("Servico.Pagamento"), fontTable));
                celPag.setPadding(5);
                celPag.setBorder(celPag.NO_BORDER);
                celPag.setHorizontalAlignment(Element.ALIGN_CENTER);
                celPag.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celValor = new PdfPCell(new Phrase(resultado.getString("Servico.Valor"), fontTable));
                celValor.setPadding(5);
                celValor.setBorder(celValor.NO_BORDER);
                celValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                celValor.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celStatus = new PdfPCell(new Phrase(resultado.getString("Servico.Servico_Status"), fontTable));
                celStatus.setPadding(5);
                celStatus.setBorder(celStatus.NO_BORDER);
                celStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                celStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celMao = new PdfPCell(new Phrase(resultado.getString("Servico.Mao_Obra"), fontTable));
                celMao.setPadding(5);
                celMao.setBorder(celMao.NO_BORDER);
                celMao.setHorizontalAlignment(Element.ALIGN_CENTER);
                celMao.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celLogin = new PdfPCell(new Phrase(resultado.getString("Usuario_do_Sistema.Nome_Usuario"), fontTable));
                celLogin.setPadding(5);
                celLogin.setBorder(celLogin.NO_BORDER);
                celLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
                celLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // Adicionando célula com os dados do banco
                PdfPCell celLucro = celLucro = new PdfPCell(new Phrase(resultado.getString("Servico.Lucro"), fontTable));
                celLucro.setPadding(5);
                celLucro.setBorder(celLucro.NO_BORDER);
                celLucro.setHorizontalAlignment(Element.ALIGN_CENTER);
                celLucro.setVerticalAlignment(Element.ALIGN_MIDDLE);

                // adciona as células preenchidas com os dados do banco na tabelas
                table.addCell(celCodigo);
                table.addCell(celDescricao);
                table.addCell(celCliente);
                table.addCell(celQuantidade);
                table.addCell(celTamanho);
                table.addCell(celPag);
                table.addCell(celValor);
                table.addCell(celStatus);
                table.addCell(celMao);
                table.addCell(celLucro);
                table.addCell(celLogin);

            }

            doc.add(table); // adiciona a tabela no PDF

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            doc.close();
        }
        
        //abre a janela do PDF
        try {
            Desktop.getDesktop().open(new File("wd.pdf")); // abre o DOC
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    // Relatório atual de todas os serviços
    public void relatorioOS() {

        String resp = servicoCpfCliente;
        System.out.println("Resp " + resp);

        conectar();
        //Imprimindo Relatorio Itext
        String sql = "select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,\n"
                + " Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.Lucro, Usuario_do_Sistema.Nome_Usuario     \n"
                + " from Servico, Cliente, Usuario_do_Sistema\n"
                + " where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario";

        Document document = new Document();

        //String g = servicoCpfCliente;
        //System.out.println("Cpf do mizera " + g);
        Date data = new Date();
        //System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String Data_Inicio = form.format(data);
        //System.out.println("data formata " + Data_Inicio);

        try {
            try {

                FileOutputStream ou = new FileOutputStream("RelOS.pdf");
                PdfWriter.getInstance(document, ou);

                document.open(); // Abrindo o documento

                Font smallfont0 = new Font(Font.FontFamily.HELVETICA, 8);
                Paragraph pP = new Paragraph(Data_Inicio, smallfont0);
                //p1.setIndentationLeft(30);
                pP.setAlignment(Element.ALIGN_RIGHT);
                document.add(pP);

                //Adicionando a imagem
                //BaseFont bf = BaseFont.createFont(FontFactory.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);  
                Font smallfont = new Font(Font.FontFamily.HELVETICA, 13);
                Font smallfont2 = new Font(Font.FontFamily.HELVETICA, 11);
                Font fontTable = new Font(Font.FontFamily.HELVETICA, 7);
                Font fontTable2 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
                //Font fontTableTit = new Font(FontFamily.HELVETICA, 9);

                Font fontTableTit = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));

                Font ff = new Font(Font.FontFamily.COURIER, 14, Font.BOLD + Font.ITALIC + Font.UNDERLINE);
                Font ffo = new Font(Font.FontFamily.COURIER, 13, Font.BOLD + Font.ITALIC);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font font = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font fonte = new Font(Font.FontFamily.HELVETICA, 11);
                //Font fonte = new Font(Font.FontFamily.COURIER, 12);

                Font fontinha = new Font(Font.FontFamily.COURIER, 12 + Font.UNDERLINE);

                //Image imagem = Image.getInstance("MeuLogo.png");
                //imagem.setIndentationLeft(20);
                //doc.add(imagem);
                Paragraph titulo = new Paragraph(" Serralharia Gabriel", ffo);
                // Setando o alinhamento p/ o centro
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                // titulo.setSpacingAfter(50);
                document.add(titulo);

                Paragraph titulo2 = new Paragraph(" Tel: (84) 3248-2530 / (84)9 8850-2699", ffo);
                // Setando o alinhamento p/ o centro
                titulo2.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                //titulo2.setSpacingAfter(50);
                document.add(titulo2);

                Paragraph titulo3 = new Paragraph(" Av: Assis Chateaubriant  Nº5000", ffo);
                // Setando o alinhamento p/ o centro
                titulo3.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                titulo3.setSpacingAfter(10);
                document.add(titulo3);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph p = new Paragraph("                                                             ", ff);
                // Setando o alinhamento p/ o centro
                p.setAlignment(Paragraph.ALIGN_CENTER);

                // Definindo espaço depois do parágrafo
                p.setSpacingAfter(30);
                document.add(p);

                Paragraph p1 = new Paragraph("Relatório de Ordem de Serviços", smallfont);
                //p1.setIndentationLeft(30);
                p1.setAlignment(Element.ALIGN_CENTER);
                document.add(p1);
                Paragraph p10 = new Paragraph("                                            ");
                document.add(p10);

                PdfPTable table = new PdfPTable(11);
                table.setTotalWidth(500);
                table.setLockedWidth(true);
                table.setWidths(new int[]{4, 5, 6, 5, 5, 5, 5, 5, 5, 5, 5});

                // célula da tabela
                PdfPCell celulaTitulo = new PdfPCell(new Phrase("Ordem de Serviço", fontTableTit));
                celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTitulo.setBorder(celulaTitulo.NO_BORDER);
                celulaTitulo.setBackgroundColor(new BaseColor(102, 102, 102));
                celulaTitulo.setColspan(11);
                celulaTitulo.setPaddingTop(5);
                celulaTitulo.setPaddingBottom(6);
                table.addCell(celulaTitulo);

                // célula da tabela
                PdfPCell celulaCodigo = new PdfPCell(new Phrase("Código", fontTable2));
                celulaCodigo.setBorder(celulaCodigo.NO_BORDER);
                celulaCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaCodigo.setPaddingTop(4);
                celulaCodigo.setPaddingBottom(5);
                celulaCodigo.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaNome = new PdfPCell(new Phrase("Descrição", fontTable2));
                celulaNome.setBorder(celulaNome.NO_BORDER);
                celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaNome.setPaddingTop(4);
                celulaNome.setPaddingBottom(5);
                celulaNome.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaCliente = new PdfPCell(new Phrase("Cliente", fontTable2));
                celulaCliente.setBorder(celulaCliente.NO_BORDER);
                celulaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaCliente.setPaddingTop(4);
                celulaCliente.setPaddingBottom(5);
                celulaCliente.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaInicio = new PdfPCell(new Phrase("Início", fontTable2));
                celulaInicio.setBorder(celulaInicio.NO_BORDER);
                celulaInicio.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaInicio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaInicio.setPaddingTop(4);
                celulaInicio.setPaddingBottom(5);
                celulaInicio.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaEntrega = new PdfPCell(new Phrase(" Entrega", fontTable2));
                celulaEntrega.setBorder(celulaEntrega.NO_BORDER);
                celulaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaEntrega.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaEntrega.setPaddingTop(4);
                celulaEntrega.setPaddingBottom(5);
                celulaEntrega.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaPagamento = new PdfPCell(new Phrase("Pagamento", fontTable2));
                celulaPagamento.setBorder(celulaPagamento.NO_BORDER);
                celulaPagamento.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaPagamento.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaPagamento.setPaddingTop(4);
                celulaPagamento.setPaddingBottom(5);
                celulaPagamento.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", fontTable2));
                celulaValor.setBorder(celulaValor.NO_BORDER);
                celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaValor.setPaddingTop(4);
                celulaValor.setPaddingBottom(5);
                celulaValor.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaStatus = new PdfPCell(new Phrase("Status", fontTable2));
                celulaStatus.setBorder(celulaStatus.NO_BORDER);
                celulaStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaStatus.setPaddingTop(4);
                celulaStatus.setPaddingBottom(5);
                celulaStatus.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaMao = new PdfPCell(new Phrase("Mão de Obra", fontTable2));
                celulaMao.setBorder(celulaMao.NO_BORDER);
                celulaMao.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaMao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaMao.setPaddingTop(4);
                celulaMao.setPaddingBottom(5);
                celulaMao.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaLogin = new PdfPCell(new Phrase("Lucro", fontTable2));
                celulaLogin.setBorder(celulaLogin.NO_BORDER);
                celulaLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaLogin.setPaddingTop(4);
                celulaLogin.setPaddingBottom(5);
                celulaLogin.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula da tabela
                PdfPCell celulaL = new PdfPCell(new Phrase("Usuário", fontTable2));
                celulaL.setBorder(celulaL.NO_BORDER);
                celulaL.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaL.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaL.setPaddingTop(4);
                celulaL.setPaddingBottom(5);
                celulaL.setBackgroundColor(new BaseColor(255, 176, 0));

                //PdfPCell celCodigo = new PdfPCell(new Phrase("Preço", fontTable));
                //celulaValor.setBackgroundColor(BaseColor.ORANGE);
                //celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                // Adicionando as células (colunas) da tabela
                table.addCell(celulaCodigo);
                table.addCell(celulaNome);
                table.addCell(celulaCliente);
                table.addCell(celulaInicio);
                table.addCell(celulaEntrega);
                table.addCell(celulaPagamento);
                table.addCell(celulaValor);
                table.addCell(celulaStatus);
                table.addCell(celulaMao);
                table.addCell(celulaLogin);
                table.addCell(celulaL);

                ResultSet resultado = estado.executeQuery(sql); // Consulta ao banco de dados

                while (resultado.next()) {

                    // célula
                    PdfPCell celCodigo = new PdfPCell(new Phrase(resultado.getString("Servico.Cod_Servico"), fontTable));
                    celCodigo.setBorder(celCodigo.NO_BORDER);
                    celCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCodigo.setPadding(5);

                    // célula
                    PdfPCell celDescricao = new PdfPCell(new Phrase(resultado.getString("Servico.Descricao_Servico"), fontTable));
                    celDescricao.setBorder(celDescricao.NO_BORDER);
                    celDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celDescricao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celDescricao.setPadding(5);

                    // célula
                    PdfPCell celCliente = new PdfPCell(new Phrase(resultado.getString("Cliente.Nome_Cliente"), fontTable));
                    celCliente.setBorder(celCliente.NO_BORDER);
                    celCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCliente.setPadding(5);

                    // célula
                    PdfPCell celQuantidade = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Inicio"), fontTable));
                    celQuantidade.setBorder(celQuantidade.NO_BORDER);
                    celQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celQuantidade.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celQuantidade.setPadding(5);

                    // célula
                    PdfPCell celTamanho = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Entrega"), fontTable));
                    celTamanho.setBorder(celTamanho.NO_BORDER);
                    celTamanho.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celTamanho.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celTamanho.setPadding(5);

                    // célula
                    PdfPCell celPag = new PdfPCell(new Phrase(resultado.getString("Servico.Pagamento"), fontTable));
                    celPag.setBorder(celPag.NO_BORDER);
                    celPag.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celPag.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celPag.setPadding(5);

                    // célula
                    PdfPCell celValor = new PdfPCell(new Phrase(resultado.getString("Servico.Valor"), fontTable));
                    celValor.setBorder(celValor.NO_BORDER);
                    celValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celValor.setPadding(5);

                    // célula
                    PdfPCell celStatus = new PdfPCell(new Phrase(resultado.getString("Servico.Servico_Status"), fontTable));
                    celStatus.setBorder(celStatus.NO_BORDER);
                    celStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celStatus.setPadding(5);

                    // célula
                    PdfPCell celMao = new PdfPCell(new Phrase(resultado.getString("Servico.Mao_Obra"), fontTable));
                    celMao.setBorder(celMao.NO_BORDER);
                    celMao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celMao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celMao.setPadding(5);

                    // célula
                    PdfPCell celL = new PdfPCell(new Phrase(resultado.getString("Servico.Lucro"), fontTable));
                    celL.setBorder(celL.NO_BORDER);
                    celL.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celL.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celL.setPadding(5);

                    // célula
                    PdfPCell celLogin = new PdfPCell(new Phrase(resultado.getString("Usuario_do_Sistema.Nome_Usuario"), fontTable));
                    celLogin.setBorder(celLogin.NO_BORDER);
                    celLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celLogin.setPadding(5);

                    // adicionando as células com os dados do banco na tabela
                    table.addCell(celCodigo);
                    table.addCell(celDescricao);
                    table.addCell(celCliente);
                    table.addCell(celQuantidade);
                    table.addCell(celTamanho);
                    table.addCell(celPag);
                    table.addCell(celValor);
                    table.addCell(celStatus);
                    table.addCell(celMao);
                    table.addCell(celL);
                    table.addCell(celLogin);
                }

                document.add(table); // adiciona a tabela no PDF

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(RelatorioOS.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConexaoBanco.fecharConexao();
            }

            // document.add(table);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo já está aberto!");
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("RelOS.pdf")); // abre o documento PDF
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void relatorioProdutos() {
        conectar();
        String sql = "select Cod_Produto, Descricao, Quantidade, Tamanho, Medida, Preco_Revenda from Produto";

        Document document = new Document();

        Date data = new Date();
        System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String Data_Inicio = form.format(data);
        System.out.println("data formata " + Data_Inicio);

        try {
            try {

                FileOutputStream ou = new FileOutputStream("RelProdutos.pdf");
                PdfWriter.getInstance(document, ou);

                document.open(); // Abrindo o documento

                //Adicionando a imagem
                //BaseFont bf = BaseFont.createFont(FontFactory.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);  
                Font smallfont = new Font(Font.FontFamily.HELVETICA, 13);
                Font smallfont2 = new Font(Font.FontFamily.HELVETICA, 11);
                Font fontTable = new Font(Font.FontFamily.HELVETICA, 9);
                Font fontTable2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
                //Font fontTableTit = new Font(FontFamily.HELVETICA, 9);

                Font fontTableTit = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));

                Font ff = new Font(Font.FontFamily.COURIER, 14, Font.BOLD + Font.ITALIC + Font.UNDERLINE);
                Font ffo = new Font(Font.FontFamily.COURIER, 13, Font.BOLD + Font.ITALIC);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font font = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font fonte = new Font(Font.FontFamily.COURIER, 12);

                Font fontinha = new Font(Font.FontFamily.COURIER, 12 + Font.UNDERLINE);

                //Image imagem = Image.getInstance("MeuLogo.png");
                //imagem.setIndentationLeft(20);
                //doc.add(imagem);
                Paragraph titulo = new Paragraph(" Serralharia Gabriel", ffo);
                // Setando o alinhamento p/ o centro
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                // titulo.setSpacingAfter(50);
                document.add(titulo);

                Paragraph titulo2 = new Paragraph(" Tel: (84) 3248-2530 / (84)9 8850-2699", ffo);
                // Setando o alinhamento p/ o centro
                titulo2.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                //titulo2.setSpacingAfter(50);
                document.add(titulo2);

                Paragraph titulo3 = new Paragraph(" Av: Assis Chateaubriant  Nº5000", ffo);
                // Setando o alinhamento p/ o centro
                titulo3.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                titulo3.setSpacingAfter(10);
                document.add(titulo3);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph p = new Paragraph("                                                             ", ff);
                // Setando o alinhamento p/ o centro
                p.setAlignment(Paragraph.ALIGN_CENTER);

                // Definindo espaço depois do parágrafo
                p.setSpacingAfter(30);
                document.add(p);

                Paragraph p1 = new Paragraph("Relatório de Produtos", smallfont);
                //p1.setIndentationLeft(30);
                p1.setAlignment(Element.ALIGN_CENTER);
                document.add(p1);
                Paragraph p10 = new Paragraph("                                            ");
                document.add(p10);

                Paragraph p2 = new Paragraph("                                            ");
                document.add(p2);

                PdfPTable table = new PdfPTable(6); // instacia a tabela
                table.setTotalWidth(500); // seta o tamanho da tabela
                table.setLockedWidth(true); // seta o tamanho como verdadeiro
                table.setWidths(new int[]{5, 10, 7, 8, 8, 5}); // seta o valor de cada coluna

                PdfPCell celulaTitulo = new PdfPCell(new Phrase("Lista de Produtos", fontTableTit));
                celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTitulo.setBorder(celulaTitulo.NO_BORDER);
                celulaTitulo.setBackgroundColor(new BaseColor(102, 102, 102));
                celulaTitulo.setColspan(6);
                celulaTitulo.setPaddingTop(5);
                celulaTitulo.setPaddingBottom(6);

                table.addCell(celulaTitulo);

                PdfPCell celulaCodigo = new PdfPCell(new Phrase("Código", fontTable2));
                celulaCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaCodigo.setBorder(celulaCodigo.NO_BORDER);
                celulaCodigo.setPaddingBottom(5);
                celulaCodigo.setPaddingTop(4);
                celulaCodigo.setBackgroundColor(new BaseColor(255, 176, 0));
                PdfPCell celulaNome = new PdfPCell(new Phrase("Nome", fontTable2));
                celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaNome.setBorder(celulaNome.NO_BORDER);
                celulaNome.setBackgroundColor(new BaseColor(255, 176, 0));
                PdfPCell celulaQuant = new PdfPCell(new Phrase("Quantidade", fontTable2));
                celulaQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaQuant.setBorder(celulaQuant.NO_BORDER);
                celulaQuant.setBackgroundColor(new BaseColor(255, 176, 0));
                PdfPCell celulaTam = new PdfPCell(new Phrase("Tamanho", fontTable2));
                celulaTam.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTam.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTam.setBorder(celulaTam.NO_BORDER);
                celulaTam.setBackgroundColor(new BaseColor(255, 176, 0));
                PdfPCell celulaMed = new PdfPCell(new Phrase("Medida", fontTable2));
                celulaMed.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaMed.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaMed.setBorder(celulaMed.NO_BORDER);
                celulaMed.setBackgroundColor(new BaseColor(255, 176, 0));
                PdfPCell celulaValor = new PdfPCell(new Phrase("Preço", fontTable2));
                celulaValor.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaValor.setBorder(celulaValor.NO_BORDER);

                //PdfPCell celCodigo = new PdfPCell(new Phrase("Preço", fontTable));
                //celulaValor.setBackgroundColor(BaseColor.ORANGE);
                //celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celulaCodigo);
                table.addCell(celulaNome);
                table.addCell(celulaQuant);
                table.addCell(celulaTam);
                table.addCell(celulaMed);
                table.addCell(celulaValor);

                ResultSet resultado = estado.executeQuery(sql);
                while (resultado.next()) {
                    PdfPCell celCodigo = new PdfPCell(new Phrase(resultado.getString("Cod_Produto"), fontTable));
                    celCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCodigo.setPadding(5);
                    celCodigo.setBorder(celCodigo.NO_BORDER);
                    PdfPCell celDescricao = new PdfPCell(new Phrase(resultado.getString("Descricao"), fontTable));
                    celDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celDescricao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celDescricao.setPadding(5);
                    celDescricao.setBorder(celDescricao.NO_BORDER);
                    PdfPCell celQuantidade = new PdfPCell(new Phrase(resultado.getString("Quantidade"), fontTable));
                    celQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celQuantidade.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celQuantidade.setPadding(5);
                    celQuantidade.setBorder(celQuantidade.NO_BORDER);
                    PdfPCell celTamanho = new PdfPCell(new Phrase(resultado.getString("Tamanho"), fontTable));
                    celTamanho.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celTamanho.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celTamanho.setPadding(5);
                    celTamanho.setBorder(celTamanho.NO_BORDER);
                    PdfPCell celMedida = new PdfPCell(new Phrase(resultado.getString("Medida"), fontTable));
                    celMedida.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celMedida.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celMedida.setPadding(5);
                    celMedida.setBorder(celMedida.NO_BORDER);
                    PdfPCell celValor = new PdfPCell(new Phrase(resultado.getString("Preco_Revenda"), fontTable));
                    celValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celValor.setPadding(5);
                    celValor.setBorder(celValor.NO_BORDER);

                    table.addCell(celCodigo);
                    table.addCell(celDescricao);
                    table.addCell(celQuantidade);
                    table.addCell(celTamanho);
                    table.addCell(celMedida);
                    table.addCell(celValor);
                }

                document.add(table);

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo já está aberto!");
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("RelProdutos.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }

    }

    // Relatório de todos os clientes
    public void relatorioClientes() {
        conectar();

        String sql = "select * from Cliente order by Nome_Cliente";

        Document document = new Document();

        Date data = new Date();
        System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String Data_Inicio = form.format(data);
        System.out.println("data formata " + Data_Inicio);

        try {
            try {

                FileOutputStream ou = new FileOutputStream("RelClientes.pdf");
                PdfWriter.getInstance(document, ou);
                document.open(); // Abrindo o documento 

                Font smallfont0 = new Font(Font.FontFamily.HELVETICA, 8);

                //Adicionando a imagem
                //BaseFont bf = BaseFont.createFont(FontFactory.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);  
                Font smallfont = new Font(Font.FontFamily.HELVETICA, 13);
                Font smallfont2 = new Font(Font.FontFamily.HELVETICA, 11);
                Font fontTable = new Font(Font.FontFamily.HELVETICA, 9);
                Font fontTable2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
                //Font fontTableTit = new Font(FontFamily.HELVETICA, 9);

                Font fontTableTit = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));

                Font ff = new Font(Font.FontFamily.COURIER, 14, Font.BOLD + Font.ITALIC + Font.UNDERLINE);
                Font ffo = new Font(Font.FontFamily.COURIER, 13, Font.BOLD + Font.ITALIC);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font font = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font fonte = new Font(Font.FontFamily.COURIER, 12);

                Font fontinha = new Font(Font.FontFamily.COURIER, 12 + Font.UNDERLINE);

                //Image imagem = Image.getInstance("MeuLogo.png");
                //imagem.setIndentationLeft(20);
                //doc.add(imagem);
                Paragraph titulo = new Paragraph(" Serralharia Gabriel", ffo);
                // Setando o alinhamento p/ o centro
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                // titulo.setSpacingAfter(50);
                document.add(titulo);

                Paragraph titulo2 = new Paragraph(" Tel: (84) 3248-2530 / (84)9 8850-2699", ffo);
                // Setando o alinhamento p/ o centro
                titulo2.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                //titulo2.setSpacingAfter(50);
                document.add(titulo2);

                Paragraph titulo3 = new Paragraph(" Av: Assis Chateaubriant  Nº5000", ffo);
                // Setando o alinhamento p/ o centro
                titulo3.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                titulo3.setSpacingAfter(10);
                document.add(titulo3);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph p = new Paragraph("                                                             ", ff);
                // Setando o alinhamento p/ o centro
                p.setAlignment(Paragraph.ALIGN_CENTER);

                // Definindo espaço depois do parágrafo
                p.setSpacingAfter(30);
                document.add(p);

                Paragraph p1 = new Paragraph("Relatório de dados dos clientes", smallfont);
                //p1.setIndentationLeft(30);
                p1.setAlignment(Element.ALIGN_CENTER);
                document.add(p1);
                Paragraph p10 = new Paragraph("                                            ");
                document.add(p10);

                /*Paragraph p8 = new Paragraph("Relatório com a relação de clientes da Serralheria Gabriel.", smallfont2);

                //p8.setIndentationLeft(30);
                p8.setAlignment(Element.ALIGN_CENTER);
                document.add(p8);*/
                Paragraph p2 = new Paragraph("                                            ");
                document.add(p2);
                //Paragraph p3 = new Paragraph("                                            ");
                //document.add(p3);

                // Tabela
                PdfPTable tables = new PdfPTable(7);
                tables.setTotalWidth(500);
                tables.setLockedWidth(true);
                tables.setWidths(new int[]{10, 8, 5, 8, 8, 5, 12});
                //tables.setTotalWidth(80);

                // Células da tabela
                PdfPCell celulaTitulo = new PdfPCell(new Phrase("Dados dos Clientes", fontTableTit));
                celulaTitulo.setColspan(7);
                celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTitulo.setBorder(celulaTitulo.NO_BORDER);
                celulaTitulo.setBackgroundColor(new BaseColor(102, 102, 102));
                celulaTitulo.setPaddingTop(5);
                celulaTitulo.setPaddingBottom(6);
                tables.addCell(celulaTitulo);

                // Células da tabela
                PdfPCell celulaNome = new PdfPCell(new Phrase("Nome", fontTable2));
                celulaNome.setBorder(celulaNome.NO_BORDER);
                celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaNome.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaNome.setPaddingBottom(5);
                celulaNome.setPaddingTop(4);

                // Células da tabela
                PdfPCell celulaRua = new PdfPCell(new Phrase("Rua", fontTable2));
                celulaRua.setBorder(celulaRua.NO_BORDER);
                celulaRua.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaRua.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaRua.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaRua.setPaddingTop(4);
                celulaRua.setPaddingBottom(5);

                // Células da tabela
                PdfPCell celulaQuant = new PdfPCell(new Phrase("Nº", fontTable2));
                celulaQuant.setBorder(celulaQuant.NO_BORDER);
                celulaQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaQuant.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaQuant.setPaddingTop(4);
                celulaQuant.setPaddingBottom(5);

                // Células da tabela
                PdfPCell celulaTam = new PdfPCell(new Phrase("Bairro", fontTable2));
                celulaTam.setBorder(celulaTam.NO_BORDER);
                celulaTam.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTam.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTam.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaTam.setPaddingTop(4);
                celulaTam.setPaddingBottom(5);

                // Células da tabela
                PdfPCell celulaMed = new PdfPCell(new Phrase("Cidade", fontTable2));
                celulaMed.setBorder(celulaMed.NO_BORDER);
                celulaMed.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaMed.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaMed.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaMed.setPaddingTop(4);
                celulaMed.setPaddingBottom(5);

                // Células da tabela
                PdfPCell celulaValor = new PdfPCell(new Phrase("Estado", fontTable2));
                celulaValor.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaValor.setBorder(celulaValor.NO_BORDER);
                celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaValor.setPaddingTop(4);
                celulaValor.setPaddingBottom(5);

                // Células da tabela
                PdfPCell celulaTelefone = new PdfPCell(new Phrase("Telefone", fontTable2));
                celulaTelefone.setBackgroundColor(new BaseColor(255, 176, 0));
                celulaTelefone.setBorder(celulaTelefone.NO_BORDER);
                celulaTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTelefone.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTelefone.setPaddingTop(4);
                celulaTelefone.setPaddingBottom(5);
                //PdfPCell celCodigo = new PdfPCell(new Phrase("Preço", fontTable));
                //celulaValor.setBackgroundColor(BaseColor.ORANGE);
                //celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);

                // Adiciona as células na tabela
                tables.addCell(celulaNome);
                tables.addCell(celulaRua);
                tables.addCell(celulaQuant);
                tables.addCell(celulaTam);
                tables.addCell(celulaMed);
                tables.addCell(celulaValor);
                tables.addCell(celulaTelefone);

                ResultSet resultado = estado.executeQuery(sql); // Consulta ao banco

                while (resultado.next()) {
                    PdfPCell celNome = new PdfPCell(new Phrase(resultado.getString("Nome_Cliente"), fontTable));
                    celNome.setBorder(celNome.NO_BORDER);
                    celNome.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celNome.setPadding(5);

                    PdfPCell celRua = new PdfPCell(new Phrase(resultado.getString("Rua_Cliente"), fontTable));
                    celRua.setBorder(celRua.NO_BORDER);
                    celRua.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celRua.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celRua.setPadding(5);

                    PdfPCell celNum = new PdfPCell(new Phrase(resultado.getString("Numero_Cliente"), fontTable));
                    celNum.setBorder(celNum.NO_BORDER);
                    celNum.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celNum.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celNum.setPadding(6);

                    PdfPCell celBairro = new PdfPCell(new Phrase(resultado.getString("Bairro_Cliente"), fontTable));
                    celBairro.setBorder(celBairro.NO_BORDER);
                    celBairro.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celBairro.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celBairro.setPadding(6);

                    PdfPCell celCid = new PdfPCell(new Phrase(resultado.getString("Cidade_Cliente"), fontTable));
                    celCid.setBorder(celCid.NO_BORDER);
                    celCid.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCid.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCid.setPadding(6);

                    PdfPCell celEst = new PdfPCell(new Phrase(resultado.getString("Estado_Cliente"), fontTable));
                    celEst.setBorder(celEst.NO_BORDER);
                    celEst.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celEst.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celEst.setPadding(6);

                    PdfPCell celTelefone = new PdfPCell(new Phrase(resultado.getString("Tel_Cliente"), fontTable));
                    celTelefone.setBorder(celTelefone.NO_BORDER);
                    celTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celTelefone.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celTelefone.setPadding(6);

                    // Adiconando as células preenchidas com dados do banco
                    tables.addCell(celNome);
                    tables.addCell(celRua);
                    tables.addCell(celNum);
                    tables.addCell(celBairro);
                    tables.addCell(celCid);
                    tables.addCell(celEst);
                    tables.addCell(celTelefone);
                }

                document.add(tables); // Adicona a tabela ao PDF (Documento)

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(RelatorioOS.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo já está aberto!");
        } finally {
            document.close(); // Fecha  documento 
        }

        try {
            Desktop.getDesktop().open(new File("RelClientes.pdf")); // pega a referência do PDF na pasta raiz para abri-lo.
        } catch (IOException ex) {
            System.out.println("Erro ao abrir PDF"); // caso o documento esteja vazio ou não exista
        }
    }
    
     // NOVO RELATÓRIO COM PRODUTOS DO SERVIÇO
    public void reatoriolOsClienteComProdutos(String cpf, String nome, String rua, String n, String bairro, String cidade, String est, String telefone, int codOS) {

        String resp = serCpfCliente;
        System.out.println("Resp " + resp);

        conectar();
        //Imprimindo Relatorio Itext
        String sql = " select Servico.Cod_Servico, Servico.Descricao_Servico,  Cliente.Nome_Cliente, Servico.Data_Inicio, Servico.Data_Entrega,\n"
                + " Servico.Pagamento, Servico.Valor, Servico.Servico_Status, Servico.mao_Obra, Servico.QuantidadeM2, Servico.Lucro, Usuario_do_Sistema.Nome_Usuario     \n"
                + " from Servico, Cliente, Usuario_do_Sistema\n"
                + " where Servico.Cpf_Cliente = Cliente.Cpf_Cliente and Servico.Login_Usuario = Usuario_do_Sistema.Login_Usuario\n"
                + " and Servico.Cod_Servico = " + codOS;

        Document document = new Document();

        //String g = servicoCpfCliente;
        //System.out.println("Cpf do mizera " + g);
        Date data = new Date();
        //System.out.println("Data aaa: " + data);
        //Instancia o objeto de simplificação de formatação
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        //Método que recebe a data e formata apenas o parâmetro de Simple
        String Data_Inicio = form.format(data);
        //System.out.println("data formata " + Data_Inicio);

        try {
            try {

                ResultSet resultado = estado.executeQuery(sql);

                FileOutputStream ou = new FileOutputStream("RelOS.pdf");
                PdfWriter.getInstance(document, ou);

                document.open(); // Abrindo o documento

                Font smallfont0 = new Font(Font.FontFamily.HELVETICA, 8);
                Paragraph pP = new Paragraph(Data_Inicio, smallfont0);
                //p1.setIndentationLeft(30);
                pP.setAlignment(Element.ALIGN_RIGHT);
                document.add(pP);

                //Adicionando a imagem
                //BaseFont bf = BaseFont.createFont(FontFactory.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);  
                Font smallfont = new Font(Font.FontFamily.HELVETICA, 13);
                Font smallfont2 = new Font(Font.FontFamily.HELVETICA, 11);
                Font smallfont3 = new Font(Font.FontFamily.HELVETICA, 9);
                Font fontTable = new Font(Font.FontFamily.HELVETICA, 7);
                Font fontTable2 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
                //Font fontTableTit = new Font(FontFamily.HELVETICA, 9);

                Font fontTableTit = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));

                // Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
                Font ff = new Font(Font.FontFamily.COURIER, 14, Font.BOLD + Font.ITALIC + Font.UNDERLINE);
                Font ffo = new Font(Font.FontFamily.COURIER, 13, Font.BOLD + Font.ITALIC);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font font = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);

                // Definindo uma fonte, com tamanho 12 e negrito
                Font fonte = new Font(Font.FontFamily.HELVETICA, 11);
                //Font fonte = new Font(Font.FontFamily.COURIER, 12);

                Font fontinha = new Font(Font.FontFamily.COURIER, 12 + Font.UNDERLINE);

                //Image imagem = Image.getInstance("MeuLogo.png");
                //imagem.setIndentationLeft(20);
                //doc.add(imagem);
                Paragraph titulo = new Paragraph(" Serralharia Gabriel", ffo);
                // Setando o alinhamento p/ o centro
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                // titulo.setSpacingAfter(50);
                document.add(titulo);

                Paragraph titulo2 = new Paragraph(" Tel: (84) 3248-2530 / (84)9 8850-2699", ffo);
                // Setando o alinhamento p/ o centro
                titulo2.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                //titulo2.setSpacingAfter(50);
                document.add(titulo2);

                Paragraph titulo3 = new Paragraph(" Av: Assis Chateaubriant  Nº5000", ffo);
                // Setando o alinhamento p/ o centro
                titulo3.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                titulo3.setSpacingAfter(10);
                document.add(titulo3);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph p = new Paragraph("                                                             ", ff);
                // Setando o alinhamento p/ o centro
                p.setAlignment(Paragraph.ALIGN_CENTER);

                // Definindo espaço depois do parágrafo
                p.setSpacingAfter(30);
                document.add(p);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph dadosCliente = new Paragraph("Dados do cliente", ffo);
                // Setando o alinhamento p/ o centro
                dadosCliente.setAlignment(Paragraph.ALIGN_CENTER);
                // Definindo espaço depois do parágrafo
                dadosCliente.setSpacingAfter(10);
                document.add(dadosCliente);

                Paragraph a = new Paragraph("       CPF: " + cpf + "", fonte);
                // Setando o alinhamento p/ o centro
                a.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                a.setSpacingAfter(1);
                document.add(a);

                Paragraph c = new Paragraph("       Nome: " + nome + "", fonte);
                // Setando o alinhamento p/ o centro
                c.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                c.setSpacingAfter(1);
                document.add(c);

                Paragraph x = new Paragraph("       Contato: " + telefone + "", fonte);
                // Setando o alinhamento p/ o centro
                c.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                c.setSpacingAfter(1);
                document.add(x);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph d = new Paragraph("       Endereço: " + "Rua " + rua + "    N°: " + n + "   ", fonte);
                // Setando o alinhamento p/ o centro
                d.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                d.setSpacingAfter(1);
                document.add(d);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph e = new Paragraph("       Bairro: " + bairro + "    Cidade: " + cidade + "", fonte);
                // Setando o alinhamento p/ o centro
                e.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                e.setSpacingAfter(1);
                document.add(e);

                // Adicionando um parágrafo ao PDF, com a fonte definida acima
                Paragraph ed = new Paragraph("       Estado: " + est + "", fonte);
                // Setando o alinhamento p/ o centro
                ed.setAlignment(Paragraph.ALIGN_LEFT);
                // Definindo espaço depois do parágrafo
                ed.setSpacingAfter(30);
                document.add(ed);

                PdfPTable table = new PdfPTable(11);
                table.setTotalWidth(500);
                table.setLockedWidth(true);
                table.setWidths(new int[]{3, 5, 5, 5, 5, 5, 5, 5, 5, 3, 5});

                // célula
                PdfPCell celulaTitulo = new PdfPCell(new Phrase("Ordem de Serviço", fontTableTit));
                celulaTitulo.setBorder(celulaTitulo.NO_BORDER);
                celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaTitulo.setBackgroundColor(new BaseColor(102, 102, 102));
                celulaTitulo.setColspan(11);
                celulaTitulo.setPaddingTop(5);
                celulaTitulo.setPaddingBottom(6);
                table.addCell(celulaTitulo);

                // célula
                PdfPCell celulaCodigo = new PdfPCell(new Phrase("Código", fontTable2));
                celulaCodigo.setBorder(celulaCodigo.NO_BORDER);
                celulaCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaCodigo.setPaddingTop(4);
                celulaCodigo.setPaddingBottom(5);
                celulaCodigo.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaNome = new PdfPCell(new Phrase("Descrição", fontTable2));
                celulaNome.setBorder(celulaNome.NO_BORDER);
                celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaNome.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaNome.setPaddingTop(4);
                celulaNome.setPaddingBottom(5);
                celulaNome.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaCliente = new PdfPCell(new Phrase("Cliente", fontTable2));
                celulaCliente.setBorder(celulaCliente.NO_BORDER);
                celulaCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaCliente.setPaddingTop(4);
                celulaCliente.setPaddingBottom(5);
                celulaCliente.setBackgroundColor(new BaseColor(255, 176, 0));

                //célula
                PdfPCell celulaInicio = new PdfPCell(new Phrase("Início", fontTable2));
                celulaInicio.setBorder(celulaInicio.NO_BORDER);
                celulaInicio.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaInicio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaInicio.setPaddingTop(4);
                celulaInicio.setPaddingBottom(5);
                celulaInicio.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaEntrega = new PdfPCell(new Phrase(" Entrega", fontTable2));
                celulaEntrega.setBorder(celulaEntrega.NO_BORDER);
                celulaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaEntrega.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaEntrega.setPaddingTop(4);
                celulaEntrega.setPaddingBottom(5);
                celulaEntrega.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaPagamento = new PdfPCell(new Phrase("Pagamento", fontTable2));
                celulaPagamento.setBorder(celulaPagamento.NO_BORDER);
                celulaPagamento.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaPagamento.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaPagamento.setPaddingTop(4);
                celulaPagamento.setPaddingBottom(5);
                celulaPagamento.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaValor = new PdfPCell(new Phrase("Valor", fontTable2));
                celulaValor.setBorder(celulaValor.NO_BORDER);
                celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaValor.setPaddingTop(4);
                celulaValor.setPaddingBottom(5);
                celulaValor.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaStatus = new PdfPCell(new Phrase("Status", fontTable2));
                celulaStatus.setBorder(celulaStatus.NO_BORDER);
                celulaStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaStatus.setPaddingTop(4);
                celulaStatus.setPaddingBottom(5);
                celulaStatus.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaMao = new PdfPCell(new Phrase("Mão de Obra", fontTable2));
                celulaMao.setBorder(celulaMao.NO_BORDER);
                celulaMao.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaMao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaMao.setPaddingTop(4);
                celulaMao.setPaddingBottom(5);
                celulaMao.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaLucro = new PdfPCell(new Phrase("Lucro", fontTable2));
                celulaLucro.setBorder(celulaLucro.NO_BORDER);
                celulaLucro.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaLucro.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaLucro.setPaddingTop(4);
                celulaLucro.setPaddingBottom(5);
                celulaLucro.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell celulaLogin = new PdfPCell(new Phrase("Usuário", fontTable2));
                celulaLogin.setBorder(celulaLogin.NO_BORDER);
                celulaLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celulaLogin.setPaddingTop(4);
                celulaLogin.setPaddingBottom(5);
                celulaLogin.setBackgroundColor(new BaseColor(255, 176, 0));

                //PdfPCell celCodigo = new PdfPCell(new Phrase("Preço", fontTable));
                //celulaValor.setBackgroundColor(BaseColor.ORANGE);
                //celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                // adiconando células
                table.addCell(celulaCodigo);
                table.addCell(celulaNome);
                table.addCell(celulaCliente);
                table.addCell(celulaInicio);
                table.addCell(celulaEntrega);
                table.addCell(celulaPagamento);
                table.addCell(celulaValor);
                table.addCell(celulaStatus);
                table.addCell(celulaMao);
                table.addCell(celulaLucro);
                table.addCell(celulaLogin);

                while (resultado.next()) {

                    // célula
                    PdfPCell celCodigo = new PdfPCell(new Phrase(resultado.getString("Servico.Cod_Servico"), fontTable));
                    celCodigo.setBorder(celCodigo.NO_BORDER);
                    celCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCodigo.setPadding(5);

                    // célula
                    PdfPCell celDescricao = new PdfPCell(new Phrase(resultado.getString("Servico.Descricao_Servico"), fontTable));
                    celDescricao.setBorder(celDescricao.NO_BORDER);
                    celDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celDescricao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celDescricao.setPadding(5);

                    // célula
                    PdfPCell celCliente = new PdfPCell(new Phrase(resultado.getString("Cliente.Nome_Cliente"), fontTable));
                    celCliente.setBorder(celCliente.NO_BORDER);
                    celCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCliente.setPadding(5);

                    // célula
                    PdfPCell celQuantidade = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Inicio"), fontTable));
                    celQuantidade.setBorder(celQuantidade.NO_BORDER);
                    celQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celQuantidade.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celQuantidade.setPadding(5);

                    // célula
                    PdfPCell celTamanho = new PdfPCell(new Phrase(resultado.getString("Servico.Data_Entrega"), fontTable));
                    celTamanho.setBorder(celTamanho.NO_BORDER);
                    celTamanho.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celTamanho.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celTamanho.setPadding(5);

                    // célula
                    PdfPCell celPag = new PdfPCell(new Phrase(resultado.getString("Servico.Pagamento"), fontTable));
                    celPag.setBorder(celPag.NO_BORDER);
                    celPag.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celPag.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celPag.setPadding(5);

                    // célula
                    PdfPCell celValor = new PdfPCell(new Phrase(resultado.getString("Servico.Valor"), fontTable));
                    celValor.setBorder(celValor.NO_BORDER);
                    celValor.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celValor.setPadding(5);

                    // célula
                    PdfPCell celStatus = new PdfPCell(new Phrase(resultado.getString("Servico.Servico_Status"), fontTable));
                    celStatus.setBorder(celStatus.NO_BORDER);
                    celStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celStatus.setPadding(5);

                    // célula
                    PdfPCell celMao = new PdfPCell(new Phrase(resultado.getString("Servico.Mao_Obra"), fontTable));
                    celMao.setBorder(celMao.NO_BORDER);
                    celMao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celMao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celMao.setPadding(5);

                    // célula
                    PdfPCell celLucro = new PdfPCell(new Phrase(resultado.getString("Servico.Lucro"), fontTable));
                    celLucro.setBorder(celLucro.NO_BORDER);
                    celLucro.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celLucro.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celLucro.setPadding(5);

                    // célula
                    PdfPCell celLogin = new PdfPCell(new Phrase(resultado.getString("Usuario_do_Sistema.Nome_Usuario"), fontTable));
                    celLogin.setBorder(celLogin.NO_BORDER);
                    celLogin.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celLogin.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celLogin.setPadding(5);

                    // adicionando as células com os dados do banco 
                    table.addCell(celCodigo);
                    table.addCell(celDescricao);
                    table.addCell(celCliente);
                    table.addCell(celQuantidade);
                    table.addCell(celTamanho);
                    table.addCell(celPag);
                    table.addCell(celValor);
                    table.addCell(celStatus);
                    table.addCell(celMao);
                    table.addCell(celLucro);
                    table.addCell(celLogin);

                }

                document.add(table); // adiciona o tabela com os dados no PDF

                // tabela produtos
                PdfPTable taProdutos = new PdfPTable(6);
                taProdutos.setTotalWidth(500);
                taProdutos.setLockedWidth(true);
                taProdutos.setWidths(new int[]{3, 8, 6, 5, 5, 5});

                // célula
                PdfPCell tit = new PdfPCell(new Phrase("Lista de Produtos", fontTableTit));
                tit.setBorder(tit.NO_BORDER);
                tit.setHorizontalAlignment(Element.ALIGN_CENTER);
                tit.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tit.setBackgroundColor(new BaseColor(102, 102, 102));
                tit.setColspan(6);
                tit.setPaddingTop(5);
                tit.setPaddingBottom(6);
                taProdutos.addCell(tit);

                // célula
                PdfPCell cPro = new PdfPCell(new Phrase("Código", fontTable2));
                cPro.setBorder(cPro.NO_BORDER);
                cPro.setHorizontalAlignment(Element.ALIGN_CENTER);
                cPro.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cPro.setPaddingTop(4);
                cPro.setPaddingBottom(5);
                cPro.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell cProN = new PdfPCell(new Phrase("Nome", fontTable2));
                cProN.setBorder(cProN.NO_BORDER);
                cProN.setHorizontalAlignment(Element.ALIGN_CENTER);
                cProN.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cProN.setPaddingTop(4);
                cProN.setPaddingBottom(5);
                cProN.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell cProQuant = new PdfPCell(new Phrase("Quantidade", fontTable2));
                cProQuant.setBorder(cProQuant.NO_BORDER);
                cProQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
                cProQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cProQuant.setPaddingTop(4);
                cProQuant.setPaddingBottom(5);
                cProQuant.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell cProTam = new PdfPCell(new Phrase("Tamanho/Peso", fontTable2));
                cProTam.setBorder(cProTam.NO_BORDER);
                cProTam.setHorizontalAlignment(Element.ALIGN_CENTER);
                cProTam.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cProTam.setPaddingTop(4);
                cProTam.setPaddingBottom(5);
                cProTam.setBackgroundColor(new BaseColor(255, 176, 0));

                // célula
                PdfPCell cProMes = new PdfPCell(new Phrase("Medida", fontTable2));
                cProMes.setBorder(cProMes.NO_BORDER);
                cProMes.setHorizontalAlignment(Element.ALIGN_CENTER);
                cProMes.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cProMes.setPaddingTop(4);
                cProMes.setPaddingBottom(5);
                cProMes.setBackgroundColor(new BaseColor(255, 176, 0));
                
                // célula
                PdfPCell cProMed = new PdfPCell(new Phrase("Valor", fontTable2));
                cProMed.setBorder(cProMed.NO_BORDER);
                cProMed.setHorizontalAlignment(Element.ALIGN_CENTER);
                cProMed.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cProMed.setPaddingTop(4);
                cProMed.setPaddingBottom(5);
                cProMed.setBackgroundColor(new BaseColor(255, 176, 0));

                // adiciona as colunas da tabela
                taProdutos.addCell(cPro);
                taProdutos.addCell(cProN);
                taProdutos.addCell(cProQuant);
                taProdutos.addCell(cProTam);
                taProdutos.addCell(cProMes);
                taProdutos.addCell(cProMed);

                int zika = 0;

                String sqlProdutos = " select Servico_Has_Produto.Cod_Produto, Servico_Has_Produto.Descricao, Servico_Has_Produto.Quantidade,\n"
                        + " Servico_Has_Produto.Tamanho, Servico_Has_Produto.Medida, Servico_Has_Produto.Preco_Revenda\n"
                        + " from Servico_Has_Produto, Servico\n"
                        + " where Servico_Has_Produto.Cod_Servico = Servico.Cod_Servico \n"
                        + " and Servico.Cod_Servico = " + codOS;

                ResultSet res = estado.executeQuery(sqlProdutos); // consulta ao banco de dados
                
                while (res.next()) {

                    zika = 1;
                    // célula
                    PdfPCell celCodigo = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Cod_Produto"), fontTable));
                    celCodigo.setBorder(celCodigo.NO_BORDER);
                    celCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celCodigo.setPadding(5);
                    // célula
                    PdfPCell celDescricao = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Descricao"), fontTable));
                    celDescricao.setBorder(celDescricao.NO_BORDER);
                    celDescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celDescricao.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celDescricao.setPadding(5);

                    // célula
                    PdfPCell celQuan = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Quantidade"), fontTable));
                    celQuan.setBorder(celQuan.NO_BORDER);
                    celQuan.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celQuan.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celQuan.setPadding(5);

                    // célula
                    PdfPCell celT = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Tamanho"), fontTable));
                    celT.setBorder(celT.NO_BORDER);
                    celT.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celT.setPadding(5);

                    // célula
                    PdfPCell celJ = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Medida"), fontTable));
                    celJ.setBorder(celJ.NO_BORDER);
                    celJ.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celJ.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celJ.setPadding(5);

                    // célula
                    PdfPCell celM = new PdfPCell(new Phrase(res.getString("Servico_Has_Produto.Preco_Revenda"), fontTable));
                    celM.setBorder(celM.NO_BORDER);
                    celM.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celM.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    celM.setPadding(5);

                    
                    // adiona as tuplas com os dados do banco
                    taProdutos.addCell(celCodigo);
                    taProdutos.addCell(celDescricao);
                    taProdutos.addCell(celQuan);
                    taProdutos.addCell(celT);
                    taProdutos.addCell(celJ);
                    taProdutos.addCell(celM);
                }

                if (zika == 1) {
                    document.add(taProdutos);
                }
                //document.add(taProdutos);

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConexaoBanco.fecharConexao();
            }

            // document.add(table);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo já está aberto!");
        } catch (DocumentException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("RelOS.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
