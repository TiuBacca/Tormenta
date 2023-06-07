package com.baccarin.tormenta.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Transmissor {

	private final Util util;

	public void enviarEmail(String emailDestinatario, String mensagem, String titulo) throws Exception {
		if (util.getRemetentePadraoEnvioEmail().equals("")) {
			throw new Exception("E-mail de contato não configurado.");
		}
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", util.getSmtpHostEnvioEmail());
		props.put("mail.smtp.port", util.getPortaEnvioEmail());

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(util.getRemetentePadraoEnvioEmail(), util.getSenhaEnvioEmail());

			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailDestinatario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
			message.setSubject(titulo);
			message.setContent(mensagem, "text/plain; charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void enviarEmailComHTML(String emailDestinatario, String mensagem, String titulo) throws Exception {
		if (util.getRemetentePadraoEnvioEmail().equals("")) {
			throw new Exception("E-mail de contato não configurado.");
		}
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", util.getSmtpHostEnvioEmail());
		props.put("mail.smtp.port", util.getPortaEnvioEmail());

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(util.getRemetentePadraoEnvioEmail(), util.getSenhaEnvioEmail());

			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailDestinatario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
			message.setSubject(titulo);
			message.setContent(mensagem, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String getMensagemPadraoDeEnvioEmailUsuarioNovo() {

		StringBuilder sb = new StringBuilder();

		sb.append("Olá!\n" + "Estamos muito felizes em tê-lo como novo usuário em nosso sistema Controle de Horas.\n");
		sb.append("Para o primeiro acesso utilize a seguinte senha: seu e-mail cadastrado + "
				+ util.getCodificacaoSenhaPadrao() + ".\n");
		sb.append("Exemplo: teste@teste.com" + util.getCodificacaoSenhaPadrao() + ".\n");
		sb.append("No primeiro acesso, você poderá alterar a senha para uma de sua escolha.\n");
		sb.append(
				"Se tiver alguma dúvida ou precisar de ajuda para acessar o sistema, por favor entre em contato conosco.\n\n");
		sb.append("Atenciosamente,\n");
		sb.append("Sabium Sistemas.\n");
		return sb.toString();
	}

	public String gerarEmailOrcamentoDadosAnalise() {

		StringBuilder sb = new StringBuilder();
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//		sb.append("<!DOCTYPE html>\n" + "	<html>\n" + "<head>\n" + "	<title>Orçamento para Aprovação</title>\n"
//				+ "</head>\n" + "<body>\n" + "	<p>Prezado cliente, <br> </p>\n"
//				+ "	<p>Segue orçamento para aprovação, referente a estimativa para a execução de análise de requisito de negócio e sistema.</p>\n"
//				+ "	<p>Cliente: <b>" + request.getCliente() + "</b> <br>" 
//				+ "	Chave do Ticket: <b>" + request.getTicket()+ "</b><br>" 
//				+ "	Título: <b>" + request.getTitulo() + "</b><br>" 
//				+ "	Solicitante: <b>"+ request.getSolicitante() + "</b><br>" 
//				+ "	Tipo: <b>" + request.getTipoOS() + "</b><br>"
//				+ "	Tipo financeiro: <b>" + request.getTipoFinanceiro() + "</b><br>"
//				+ "	Tipo do orçamento: <b>" + request.getTipoOrcamento() + "</b><br>"
//				+ "	Versão planejada: <b>" + request.getVersaoPlanejada() + "</b><br>"
//				+ "	Prazo máximo para aprovação: <b>" + request.getDataPrazoMaximoAprovacao().format(formatador) + "</b><br>"
//				+ "	Data prevista para conclusão da análise: <b>" + request.getDataPrevistaConclusaoAnalise().format(formatador)
//				+ "</b></p>");
//
//		sb.append("  <br><table style=\"border-collapse: collapse; position: fixed\"> " + "  <tr>\n"
//				+ "    <th style=\"border: 1px solid black; text-align: center;\"><b>Setor</b></th>\n"
//				+ "    <th style=\"border: 1px solid black; text-align: center;\"><b> Tempo Previsto (Hs) </b></th>\n"
//				+ "    <th style=\"border: 1px solid black; text-align: center;\"><b> Valor Previsto (R$) </b></th>\n"
//				+ "  </tr>");
//		
//		NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
//		DecimalFormat df = (DecimalFormat) nf;
//		df.applyPattern("#,##0.00");
//
//		request.getEstimativas().forEach(estimativa -> {
//			sb.append("  <tr>\n" 
//					+ "    <td style=\"border: 1px solid black; text-align: center;\">" + estimativa.getDescricao() + "</td>\n"
//					+ "    <td style=\"border: 1px solid black; text-align: center;\">" + df.format(estimativa.getTempoPrevisto())  + "</td>\n" 
//					+ "    <td style=\"border: 1px solid black; text-align: center;\"> "+ df.format(estimativa.getValorPrevisto()) + "</td>\n" + "  </tr>");
//		});
//
//		Double totalTempo = request.getEstimativas().stream()
//				.mapToDouble(OrcamentoEstimativaAgrupadorResponse::getTempoPrevisto).sum();
//		Double totalValor = request.getEstimativas().stream()
//				.mapToDouble(OrcamentoEstimativaAgrupadorResponse::getValorPrevisto).sum();
//
//        
//		sb.append("  <tr>\n"
//				+ "    <td style=\"border: 1px solid black; text-align: center;\"><b>Total (Estimado para aprovação)</b></td>\n"
//				+ "    <td style=\"border: 1px solid black; text-align: center;\"><b>" + df.format(totalTempo) + "</b></td>\n"
//				+ "    <td style=\"border: 1px solid black; text-align: center;\"><b>" + df.format(totalValor) + "</b></td>\n"
//				+ "  </tr> </table> <br> ");
//
//		Double valorEstimadoPagamento = totalValor - (totalValor * Double.valueOf(request.getPercentualDesconto()) / 100)
//				- Double.valueOf(request.getValorBonificado());
//		
//		sb.append("<p>Valor da hora técnica: <b>Sujeito a alterações conforme contrato</b><br>"
//				+ "Valor estimado: <b>R$" + df.format(totalValor) + " mais impostos</b><br>" 
//				+ "Valor bonificado: <b>R$" + request.getValorBonificado() + "</b><br>" 
//				+ "Percentual de desconto: <b>"+ request.getPercentualDesconto() + "%</b><br>"
//				+ "Valor estimado para pagamento: <b>R$" + df.format(valorEstimadoPagamento) + " mais impostos</b><br>"
//				+ "Valor a ser cobrado:<b> Por apuração de horas trabalhadas </b><br>"
//				+ "Pagamento:<b> Após realização do serviço </b></p>");
//
//		sb.append("<br><p><strong>OBS 1:</strong> Este orçamento contempla o valor das horas técnicas (mão de obra), estimadas para a execução do serviço.<br>" + 
//				" Se para a execução do serviço acima, for necessário deslocamento de colaborador(es) Sabium, a APROVAÇÃO deste Orçamento,"
//				+ " AUTORIZA automaticamente a cobrança (reembolso) das despesas de viagens (hospedagens, refeições, taxi etc) "
//				+ "pagas pela Sabium, bem como as horas técnicas de deslocamento.<br> "
//				+ "Para aprovar o Orçamento, acessar a área do cliente \"extranet.sabium.com.br\", "
//				+ "pesquisar as OSs na situação \"Aprovar Orçamento (Análise)\" e clicar no botão \"Aprovar\".</p>");
//		
//		sb.append("<br><p><strong>OBS 2:</strong> A não aprovação do orçamento em 10 dias corridos, ou a desistência do serviço a qualquer tempo,"
//				+ " implicará na finalização automática da OS, para fins de cobrança dos serviços já executados.<br>"
//				+ " A data prevista de conclusão da análise é estimada e poderá sofrer alteração.</p>\n" );
//		
//		sb.append(" <br> <p>No caso de dúvidas, entrar em contato pelo fone (44) 3304-3800.</p>\n" + 
//				"    <p>Cordialmente,</p>\n" + 
//				"    <p>Equipe Sabium</p>");
//		
//		sb.append("</body>\n" + "</html>");

		return sb.toString();
	}

}